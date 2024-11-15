package guiModel;

import java.io.*;
import java.util.ArrayList;

/**
 *  ide에서 파일에 대한 기능들을 제공
 *  열린 파일들을 추적
 */
public class FileUtil {
    public FileUtil() {

    }

    /**
     * 새로운 파일을 열고 파일리스트에 추가한다.
     * @param file 텍스트 에디터에 여는 파일
     */
    public void openFile(File file) {
        activatedFile = file;
        if(!editingFile.contains(activatedFile)) {
            editingFile.add(activatedFile);
        }
    }

    public static File getActivatedFile() {
        return activatedFile;
    }

    public static ArrayList<File> getEditingFile() {
        return editingFile;
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public static void deleteActivatedFile() {
        editingFile.remove(activatedFile);
        activatedFile.delete();
    }

    /**
     * @return activatedFile의 내용을 하나의 스트링으로 반환
     */
    public String getActivatedFileContent() {
        String contentFull = "";
        String contentLine;
        try {
            BufferedReader contentBufferReader = new BufferedReader(new FileReader(activatedFile));
            while((contentLine = contentBufferReader.readLine()) != null) {
                contentFull += contentLine + "\n";
            }
            contentBufferReader.close();
            return contentFull;
        } catch (FileNotFoundException e) {
            //현재 파일을 찾을수 없음
            return null;
        } catch (IOException e) {
            //파일 읽는데 오류
            return e.getMessage();
        }
    }

    public static String getFileContent(File file) {
        String contentFull = "";
        String contentLine;
        try {
            BufferedReader contentBufferReader = new BufferedReader(new FileReader(file));
            while((contentLine = contentBufferReader.readLine()) != null) {
                contentFull += contentLine + "\n";
            }
            contentBufferReader.close();
            return contentFull;
        } catch (FileNotFoundException e) {
            //현재 파일을 찾을수 없음
            return null;
        } catch (IOException e) {
            //파일 읽는데 오류
            return e.getMessage();
        }
    }

    public static boolean saveContent(String content, File file) {
        try {
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter contentBufferWriter = new BufferedWriter(new FileWriter(file));

            contentBufferWriter.write(content);
            contentBufferWriter.close();
            return true;
        } catch (IOException e) {
            //파일 쓰는데 오류
            return false;
        }
    }

    private static File activatedFile;
    private static final ArrayList<File> editingFile = new ArrayList<>();//변화시 옵저버 굳독자한테 알림
}
