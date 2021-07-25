package com.esgi.al2.projet.annuel.levelUp.response.controller;

import com.esgi.al2.projet.annuel.levelUp.exception.DockerBuildException;
import com.esgi.al2.projet.annuel.levelUp.exercise.model.Exercise;
import com.esgi.al2.projet.annuel.levelUp.response.model.Response;
import com.esgi.al2.projet.annuel.levelUp.response.service.ResponseService;
import com.esgi.al2.projet.annuel.levelUp.exercise.service.ExerciseService;
import com.esgi.al2.projet.annuel.levelUp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.esgi.al2.projet.annuel.levelUp.utility.EntryPointFile.*;

enum Languages {
    Python,
    C,
    Cpp,
    Java
}

@RestController
@RequestMapping("/api/responses")
@AllArgsConstructor
public class ResponseController {
    private final ResponseService responseService;
    private final UserService userService;
    private final ExerciseService exerciseService;

    @PostMapping("/send-python")
    public ResponseEntity<Response> sendPythonResponse(@RequestBody Response response) throws Exception {
        return compiler(response, Languages.Python);
    }

    @PostMapping("/send-c")
    public ResponseEntity<Response> sendCResponse(@RequestBody Response response) throws Exception {
        return compiler(response, Languages.C);
    }

    @PostMapping("/send-cpp")
    public ResponseEntity<Response> sendCppResponse(@RequestBody Response response) throws Exception {
        return compiler(response, Languages.Cpp);
    }

    @PostMapping("/send-java")
    public ResponseEntity<Response> sendJavaResponse(@RequestBody Response response) throws Exception {
        return compiler(response, Languages.Java);
    }

    @GetMapping("/user/{user_id}")
    public List<Response> getAllUserResponses(@PathVariable Integer user_id) {
        //Gerer l'exception
        return responseService.findAllByUser(user_id);
    }

    @GetMapping("/exercise/{exercise_id}")
    public List<Response> getAllExerciseResponses(@PathVariable Integer exercise_id) {
        return responseService.findAllByExercise(exercise_id);
    }

    @GetMapping("/{id}")
    public Optional<Response> getResponse(@PathVariable Integer id){
        return responseService.findById(id);
    }

    @GetMapping
    public Optional<Response> getUserResponse(@RequestParam Integer user_id, @RequestParam Integer exercise_id){
        return responseService.findByUserAndExercise(user_id, exercise_id);
    }

    private ResponseEntity<Response> compiler(Response response, Languages language) throws Exception{

        String folder = "utility";
        String file = "main";
        if(language == Languages.C) {
            folder += "_c";
            file += ".c";
        } else if(language == Languages.Java) {
            file += ".java";
        } else if(language == Languages.Cpp) {
            folder += "_cpp";
            file += ".cpp";
        } else {
            folder += "_py";
            file += ".py";
        }

        LocalDateTime date = LocalDateTime.now();

        response.setDate(date);

        createEntrypointFile(language);

        saveUpOutput(response, folder + "/" + file);

        String imageName = "compile" + new Date().getTime();

        try {
          response = runCode(folder, imageName, response);
        } catch(DockerBuildException exception) {
            deleteFile(folder, file);
            throw exception;
        }

        deleteFile(folder, file);

        responseService.create(response);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    private void createEntrypointFile(Languages languages) {
        if(languages == Languages.Java) {
            createJavaEntrypointFile();
        } else if(languages == Languages.C) {
            createCEntrypointFile();
        } else if(languages == Languages.Cpp) {
            createCppEntrypointFile();
        } else {
            createPythonEntrypointFile();
        }
    }

    private void saveUpOutput(Response response, String file) throws IOException {
        if (response.getCodeSent().isEmpty())
            return;

        FileWriter myWriter = new FileWriter(file);
        myWriter.write(response.getCodeSent());
        myWriter.close();
    }

    private Response runCode(String folder, String imageName, Response response) throws InterruptedException, IOException {

        int status = buildImage(folder, imageName);

        if(status != 0) {
            throw new DockerBuildException("Error while building image");
        }

        String[] dockerCommand = new String[] {"docker", "run", "--rm", imageName};
        ProcessBuilder processbuilder = new ProcessBuilder(dockerCommand);
        Process process = processbuilder.start();
        status = process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder builder = new StringBuilder();

        boolean answer = compareResult(response, reader, builder);

        response.setStatus(statusResponse(status, answer));

        return response;
    }

    private boolean compareResult(Response response, BufferedReader reader, StringBuilder builder) throws IOException {
        String line = null;

        while ( (line = reader.readLine()) != null ) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        if(line != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        while ( (line = reader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }

        Optional<Exercise> optExercise = exerciseService.findById(response.getExerciseid());
        Exercise exercise = optExercise.get();

        System.out.println(builder.toString());

        return exercise.getExpectedOutput().equals(builder);
    }

    private int buildImage(String folder, String imageName) throws InterruptedException, IOException {
        String[] dockerCommand = new String[] {"docker", "image", "build", folder, "-t", imageName};
        ProcessBuilder processbuilder = new ProcessBuilder(dockerCommand);
        Process process = processbuilder.start();
        return process.waitFor();
    }

    private String statusResponse(int status, boolean answer) {

        String statusResponse;
        if(status == 0){
            if(answer)
                statusResponse = "Accepted";
            else
                statusResponse = "Wrong Answer";
        } else if(status == 2)
            statusResponse = "Compilation Error";
        else if(status == 1)
            statusResponse = "Runtime Error";
        else if(status == 139)
            statusResponse = "Out Of Memory";
        else
            statusResponse = "Time Limit Exceeded";
        return statusResponse;
    }

    private boolean deleteFile(String folder, String file) {
        if(folder != null && file != null) {
            String fileName = folder + "/" + file;
            new File(fileName).delete();
            return true;
        }
        return false;
    }
}
