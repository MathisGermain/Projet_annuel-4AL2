package com.esgi.al2.projet.annuel.levelUp.controller;

import com.esgi.al2.projet.annuel.levelUp.exception.DockerBuildException;
import com.esgi.al2.projet.annuel.levelUp.model.Response;
import com.esgi.al2.projet.annuel.levelUp.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;


import static com.esgi.al2.projet.annuel.levelUp.utility.EntryPointFile.*;


enum Languages {
    Python,
    C,
    Cpp,
    Java
}


@RestController
@RequestMapping("/api/results")
public class ResultController {

    @RequestMapping(
            value = "python",
            method = RequestMethod.POST
    )
    @ApiOperation(
            value = "Python compiler",
            notes = "Provide outputFile, inputFile (not required), source code, time limit and memory limit",
            response = Response.class
    )
    public ResponseEntity<Object> compile_python(
            @ApiParam(value = "The expected output") @RequestPart(value = "outputFile", required = true) MultipartFile outputFile,
            @ApiParam(value = "Your source code") @RequestPart(value = "sourceCode", required = true) MultipartFile sourceCode,
            @ApiParam(value = "This one is not required, it's just the inputs") @RequestParam(value = "inputFile", required = false) MultipartFile inputFile,
            @ApiParam(value = "The time limit that the execution must not exceed") @RequestParam(value = "timeLimit", required = true) int timeLimit,
            @ApiParam(value = "The memory limit that the running program must not exceed") @RequestParam(value = "memoryLimit", required = true) int memoryLimit,
            @ApiParam() @RequestParam(value = "response", required = true) Response response
    ) throws Exception {
        return compiler(outputFile, sourceCode, inputFile, timeLimit, memoryLimit, Languages.Python, response);
    }

    private ResponseEntity<Object> compiler(
            MultipartFile outputFile,
            MultipartFile sourceCode,
            MultipartFile inputFile,
            int timeLimit,
            int memoryLimit,
            Languages languages,
            Response response
    ) throws Exception{
        String folder = "utility";
        String file = "main";
        if(languages == Languages.C) {
            folder += "_c";
            file += ".c";
        } else if(languages == Languages.Java) {
            file += ".java";
        } else if(languages == Languages.Cpp) {
            folder += "_cpp";
            file += ".cpp";
        } else {
            folder += "_py";
            file += ".py";
        }

        if(memoryLimit < 0 || memoryLimit > 1000)
            return ResponseEntity
                    .badRequest()
                    .body("Error memoryLimit must be between 0Mb and 1000Mb");

        if(timeLimit < 0 || timeLimit > 15)
            return ResponseEntity
                    .badRequest()
                    .body("Error timeLimit must be between 0 Sec and 15 Sec");


        LocalDateTime date = LocalDateTime.now();

        createEntrypointFile(sourceCode, inputFile, timeLimit, memoryLimit, languages);

        saveUploadedFiles(sourceCode, folder + "/" + file);
        saveUploadedFiles(outputFile, folder + "/" + outputFile.getOriginalFilename());

        if(inputFile != null)
            saveUploadedFiles(inputFile, folder + "/" + inputFile.getOriginalFilename());

        String imageName = "compile" + new Date().getTime();

        Result result;

        try {
            result = runCode(response, folder, imageName, outputFile);
        } catch(DockerBuildException exception) {

            // delete files
            deleteFile(folder, file);
            deleteFile(folder,outputFile.getOriginalFilename());
            if(inputFile != null)
                deleteFile(folder,inputFile.getOriginalFilename());

            throw exception;
        }

        String statusResponse = result.getVerdict();

        deleteFile(folder, file);
        deleteFile(folder,outputFile.getOriginalFilename());
        if(inputFile != null)
            deleteFile(folder,inputFile.getOriginalFilename());

        response.setResult(result);



        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    private Result runCode(Response response, String folder, String imageName, MultipartFile outputFile) throws InterruptedException, IOException {
        int status = buildImage(folder, imageName);

        if(status != 0) throw new DockerBuildException("Error while building image");

        String[] dockerCommand = new String[] {"docker", "run", "--rm", imageName};
        ProcessBuilder processbuilder = new ProcessBuilder(dockerCommand);
        Process process = processbuilder.start();
        status = process.waitFor();

        BufferedReader outputReader = new BufferedReader(new InputStreamReader(outputFile.getInputStream()));
        StringBuilder outputBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder builder = new StringBuilder();

        boolean result = compareResult(outputReader, outputBuilder, reader, builder);
        String statusResponse = statusResponse(status, result);

        return new Result(statusResponse, builder.toString(), outputBuilder.toString(), response);

    }

    private boolean deleteFile(String folder, String file) {
        if(folder != null && file != null) {
            String fileName = folder + "/" + file;
            new File(fileName).delete();
            return true;
        }
        return false;
    }

    private String statusResponse(int status, boolean ans) {

        String statusResponse;
        if(status == 0) {
            if(ans)
                statusResponse = "Accepted";
            else
                statusResponse = "Wrong Answer";
        }
        else if(status == 2)
            statusResponse = "Compilation Error";
        else if(status == 1)
            statusResponse = "Runtime Error";
        else if(status == 139)
            statusResponse = "Out Of Memory";
        else
            statusResponse = "Time Limit Exceeded";
        return statusResponse;
    }

    private boolean compareResult(BufferedReader outputReader, StringBuilder outputBuilder, BufferedReader reader, StringBuilder builder) throws IOException {
        String line = null;
        String outputLine = null;
        boolean ans = true;

        while ( (line = reader.readLine()) != null && (outputLine = outputReader.readLine()) != null) {
            if(!line.equals(outputLine))
                ans = false;
            builder.append(line);
            builder.append(System.getProperty("line.separator"));

            outputBuilder.append(outputLine);
            outputBuilder.append(System.getProperty("line.separator"));
        }

        if(line != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        if(outputLine != null) {
            outputBuilder.append(outputLine);
            outputBuilder.append(System.getProperty("line.separator"));
        }

        while ( (line = reader.readLine()) != null) {
            ans = false;
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        while ( (outputLine = outputReader.readLine()) != null) {
            ans = false;
            outputBuilder.append(outputLine);
            outputBuilder.append(System.getProperty("line.separator"));
        }
        return ans;
    }

    private int buildImage(String folder, String imageName) throws InterruptedException, IOException {
        String[] dockerCommand = new String[] {"docker", "image", "build", folder, "-t", imageName};
        ProcessBuilder processbuilder = new ProcessBuilder(dockerCommand);
        Process process = processbuilder.start();
        return process.waitFor();
    }

    private void saveUploadedFiles(MultipartFile file, String name) throws IOException {
        if (file.isEmpty())
            return;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(name);
        Files.write(path, bytes);
    }

    private void createEntrypointFile(MultipartFile sourceCode, MultipartFile inputFile, int timeLimit, int memoryLimit, Languages languages) {
        if(languages == Languages.Java) {
            createJavaEntrypointFile(sourceCode.getOriginalFilename(), timeLimit, memoryLimit, inputFile);
        } else if(languages == Languages.C) {
            createCEntrypointFile(sourceCode.getOriginalFilename(), timeLimit, memoryLimit, inputFile);
        } else if(languages == Languages.Cpp) {
            createCppEntrypointFile(sourceCode.getOriginalFilename(), timeLimit, memoryLimit, inputFile);
        } else {
            createPythonEntrypointFile(sourceCode.getOriginalFilename(), timeLimit, memoryLimit, inputFile);
        }
    }
}
