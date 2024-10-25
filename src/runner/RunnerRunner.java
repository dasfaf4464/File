package runner;

import manager.ManagerCompo;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunnerRunner {
    public boolean runJava(File file){
        String JDK = ManagerCompo.basicJavaJDK + "\\bin\\java";
        String executable = ManagerCompo.basicCompileFolder + "\\Java";

        String fileName = file.getName();
        StringTokenizer st = new StringTokenizer(fileName, ".");
        fileName = st.nextToken();
        System.out.println(JDK);
        System.out.println(executable);
        System.out.println(fileName);
        ProcessBuilder javaBuilder = new ProcessBuilder();
        javaBuilder.directory(new File(executable));
        javaBuilder.command("cmd.exe", "/k", JDK, fileName);

        int exitCode;

        try{
            Process runProcess = javaBuilder.start();

            String line;
            BufferedReader stdOut   = new BufferedReader(new
                    InputStreamReader(runProcess.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(runProcess.getErrorStream()));

            while((line = stdOut.readLine()) != null){
                System.out.println(line);
            }

            exitCode = runProcess.waitFor();
            if(exitCode == 0){
                return true;
            } else {
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}