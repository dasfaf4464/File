package guiModel;

import java.io.BufferedReader;
import java.io.*;

/**
 * 에러파일 받아서 에러파일 내용 전달
 */
public class Error {
    public String errorRead(Process process) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            StringBuilder errorBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                errorBuilder.append(line).append("\n");
            }

            return  errorBuilder.toString();
        }
        catch (IOException e) {
            Compiler.errorFlag = 1;
            return "Failed to read Error:\n" + e.getMessage();
        }

    }

}