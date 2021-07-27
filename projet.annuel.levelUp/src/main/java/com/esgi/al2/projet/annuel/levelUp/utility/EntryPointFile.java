package com.esgi.al2.projet.annuel.levelUp.utility;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class EntryPointFile     {

    private EntryPointFile() {}

    private static final String PYTHON_COMMAND = "python3";
    private static final String CPP_COMMAND = "g++";
    private static final String C_COMMAND = "gcc";
    private static final String JAVA_COMMAND = "javac";

    @SneakyThrows
    public static void createPythonEntrypointFile() {
        String executionCommand = "timeout --signal=SIGTERM " + 10 + " " + PYTHON_COMMAND + " main.py" + "\n";
        String content = "#!/usr/bin/env bash\n" +
                "ulimit -s " + 128 + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("projet.annuel.levelUp/utility_py/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createJavaEntrypointFile() {
        String executionCommand = "timeout --signal=SIGTERM " + 10 + " java " + "\n";
        String content = "#!/usr/bin/env bash\n" +
                "mv main.java " + "\n" +
                JAVA_COMMAND + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + 128 + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("projet.annuel.levelUp/utility_py/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createCEntrypointFile() {
        String executionCommand = "timeout --signal=SIGTERM " + 10 + " ./exec " + "\n";
        String content = "#!/usr/bin/env bash\n" +
                C_COMMAND + " main.c" + " -o exec" + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + 128 + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("projet.annuel.levelUp/utility_py/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }

    @SneakyThrows
    public static void createCppEntrypointFile() {
        String executionCommand = "timeout --signal=SIGTERM " + 10 + " ./exec " + "\n";
        String content = "#!/usr/bin/env bash\n" +
                CPP_COMMAND + " main.cpp" + " -o exec" + "\n" +
                "ret=$?\n" +
                "if [ $ret -ne 0 ]\n" +
                "then\n" +
                "  exit 2\n" +
                "fi\n" +
                "ulimit -s " + 128 + "\n" +
                executionCommand +
                "exit $?\n";
        OutputStream os = null;
        os = new FileOutputStream(new File("projet.annuel.levelUp/utility_py/entrypoint.sh"));
        os.write(content.getBytes(), 0, content.length());
        os.close();
    }
}
