package com.esgi.al2.projet.annuel.levelUp.utility;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EntryPointFile {

    private EntryPointFile() {}

    private static final String PYTHON_COMMAND = "python3";
    private static final String CPP_COMMAND = "g++";
    private static final String C_COMMAND = "gcc";
    private static final String JAVA_COMMAND = "javac";

    @SneakyThrows
    public static void createPythonEntrypointFile(String fileName, int timeLimit, int memoryLimit, MultipartFile inputFile) {
        String executionCommand = inputFile == null
                ? "timeout --signal=SIGTERM " + timeLimit + " " + PYTHON_COMMAND + " main.py" + "\n"
                : "timeout --signal=SIGTERM " + timeLimit + " " + PYTHON_COMMAND + " main.py" + " < " + inputFile.getOriginalFilename() + "\n";
        String content = "#!/usr/bin/env bash\n" +
                "ulimit -s " + memoryLimit + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("utility_py/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createJavaEntrypointFile(String fileName, int timeLimit, int memoryLimit, MultipartFile inputFile) {
        String executionCommand = inputFile == null
                ? "timeout --signal=SIGTERM " + timeLimit + " java " + fileName.substring(0,fileName.length() - 5) + "\n"
                : "timeout --signal=SIGTERM " + timeLimit + " java " + fileName.substring(0,fileName.length() - 5) + " < " + inputFile.getOriginalFilename() + "\n";
        String content = "#!/usr/bin/env bash\n" +
                "mv main.java " + fileName+ "\n" +
                JAVA_COMMAND + " " + fileName + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + memoryLimit + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("utility/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createCEntrypointFile(String fileName, int timeLimit, int memoryLimit, MultipartFile inputFile) {
        String executionCommand = inputFile == null
                ? "timeout --signal=SIGTERM " + timeLimit + " ./exec " + "\n"
                : "timeout --signal=SIGTERM " + timeLimit + " ./exec " + " < " + inputFile.getOriginalFilename() + "\n";
        String content = "#!/usr/bin/env bash\n" +
                C_COMMAND + " main.c" + " -o exec" + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + memoryLimit + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("utility_c/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createCppEntrypointFile(String fileName, int timeLimit, int memoryLimit, MultipartFile inputFile) {
        String executionCommand = inputFile == null
                ? "timeout --signal=SIGTERM " + timeLimit + " ./exec " + "\n"
                : "timeout --signal=SIGTERM " + timeLimit + " ./exec " + " < " + inputFile.getOriginalFilename() + "\n";
        String content = "#!/usr/bin/env bash\n" +
                CPP_COMMAND + " main.cpp" + " -o exec" + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + memoryLimit + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("utility_cpp/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }
}
