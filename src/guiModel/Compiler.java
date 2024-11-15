package guiModel;

import java.util.Arrays;
import java.util.List;
import java.io.*;

/**
 * 컴파일러는 컴파일할 파일을 가지고 컴파일 실행
 * 에러파일과 클래스파일 반환
 */
public class Compiler {
    public static int errorFlag = 0;
    private File sourceFile;
    private String javacFile;

    private static File lastCompiledFile;
    private static String lastErrorContent;
    private String massage;

    public Compiler(File file, String javac){
        sourceFile = file;
        javacFile = javac;
    }

    public Compiler(String file, String javac){
        sourceFile = new File(file);
        javacFile = javac;
    }

    /**
     * 컴파일러에 입력된 소스파일, 자바컴파일러 경로를 바탕으로 컴파일
     * 컴파일된 파일을 lastCompiled, 에러파일을 lastErrorContent
     * @return 컴파일 성공시 true 반환
     */
    public boolean compile(String outputPath) {
        int exitCode;
        
        List<String> compileCommand = Arrays.asList( //컴파일커맨드 구성(한 개만 컴파일)
                javacFile, //자바 컴파일러 절대경로
                "-d",
                outputPath, //저장할 폴더 절대경로
                sourceFile.getAbsolutePath()//컴파일할 소스코드 절대경로
        );


        ProcessBuilder compileBuilder = new ProcessBuilder(compileCommand);
        compileBuilder.redirectErrorStream(true);
        Process compileProcess; //프로세스 설정

        try {
            compileProcess = compileBuilder.start();
            exitCode = compileProcess.waitFor();
        }
        catch (InterruptedException | IOException e) {
            errorFlag = 1;
            massage = "Failed to start compiler process: " + e.getMessage();
            return false;
        }

        if(exitCode != 0) {

            try {
                InputStream errorStream = compileProcess.getInputStream();
                lastErrorContent = new String(errorStream.readAllBytes());
            }
            catch (IOException e) {
                massage = "Failed to read error output: " + e.getMessage();
                errorFlag = 1;
                return false;
            }

            errorFlag = 1;
            return false;
        } else {
            lastCompiledFile = new File(outputPath + sourceFile.getName().replaceFirst(".", ".class"));
            massage = "Compilation completed successfully";

            return true;
        }
    }

    public String getMassage() {
        return massage;
    }

    public File getLastCompiledFile() {
        return lastCompiledFile;
    }

    public String getLastErrorContent() {
        return lastErrorContent;
    }
    /* 일단 보류
    public String compile(File file, File javac, File java) {
        File currentDir = new File(".").getAbsoluteFile();
        String filePath = file.getAbsolutePath();
        String outPath = new File(currentDir.getParentFile(), "default").getPath();
        String javacPath = javac.getAbsolutePath();
        String javaPath = java.getAbsolutePath();
        String classPath = file.getName().replaceFirst("[.][^.]+$", "");//초기 인수들

        int exitCode;
        String compileError;
        String runOutput; //결과들

        File defaultDir = new File(outPath);
        if (defaultDir.exists() && defaultDir.isDirectory()) {
            File[] files = defaultDir.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.delete()) {
                        return "Failed to empty default file";
                    }
                }
            }
        } //default 폴더 비우기

        List<String> compileCommand = Arrays.asList( //컴파일커맨드 구성
                javacPath,
                "-d",
                outPath,
                filePath
        );
        List<String> runCommand = Arrays.asList( //런커맨드구성
                javaPath,
                "-cp",
                outPath,
                classPath
        );

        ProcessBuilder compileBuilder = new ProcessBuilder(compileCommand);
        compileBuilder.redirectErrorStream(true);
        Process compileProcess; //프로세스 설정

        try {
            compileProcess = compileBuilder.start();
            exitCode = compileProcess.waitFor();
        }
        catch (InterruptedException | IOException e) {
            errorFlag = 1;
            return "Failed to start compiler process: " + e.getMessage();
        }//프로세스 실행

        if(exitCode != 0) {
            compileError = new Error().errorRead(compileProcess);
            errorFlag = 1;
            return compileError;
        }
        else {
            runOutput = new Run().runClass(runCommand);
            return runOutput;
        }
    }
     */
}