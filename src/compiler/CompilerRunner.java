package compiler;

import file.FileCompo;
import manager.ManagerCompo;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class CompilerRunner {
    public boolean CompileC(File file) {
        try {
            Process process = new ProcessBuilder(ManagerCompo.basicGCC + "\\bin\\gcc", "-cp", file.getAbsolutePath()).start();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean CompileJava (File file) {
        String errorFile = ManagerCompo.basicErrorFolderPath + "\\CompileTime\\Java\\" + file.getName() +".error";

        ProcessBuilder compilerBuilder = new ProcessBuilder(ManagerCompo.basicJavaJDK + "//bin//javac", "-d", ManagerCompo.basicCompileFolder + "\\Java", file.getAbsolutePath());
        compilerBuilder.redirectError(new File(errorFile));

        int exitCode;

        try{
            Process compilerProcess = compilerBuilder.start();
            exitCode = compilerProcess.waitFor();
            if(exitCode == 0){
                File error = new File(errorFile);
                error.delete();

                String tmp = file.getName();
                StringTokenizer st = new StringTokenizer(tmp, ".");
                tmp = st.nextToken();
                CompilerCompo.successFile = new File(ManagerCompo.basicCompileFolder + "\\Java\\" + tmp + ".class");
                System.out.println(CompilerCompo.successFile.getAbsolutePath());
                return true;
            } else {
                CompilerCompo.failedFile = new File(errorFile);
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }
}