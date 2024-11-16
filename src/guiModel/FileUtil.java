package guiModel;

import java.io.*;
import java.util.ArrayList;

/**
 *  ide에서 파일에 대한 기능들을 제공
 *  편집중인 파일들을 추적
 */
public class FileUtil {
    public FileUtil() {

    }

    /**
     * 새로운 파일을 열고 파일리스트에 추가한다.
     * @param file 텍스트 에디터에 여는 파일
     */
    public void openFile(File file) {
        focusedFile = file;
        if(!editingFile.contains(focusedFile)) {
            editingFile.add(focusedFile);
        }
    }

    public  File getFocusedFile() {
        return focusedFile;
    }

    public static ArrayList<File> getEditingFile() {
        return editingFile;
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public void deleteActivatedFile() {
        editingFile.remove(focusedFile);
        focusedFile.delete();
    }

    /**
     * @return activatedFile의 내용을 하나의 스트링으로 반환
     */
    public String getActivatedFileContent() {
        String contentFull = "";
        String contentLine;
        try {
            BufferedReader contentBufferReader = new BufferedReader(new FileReader(focusedFile));
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

    public void updateSourceListFile() {

    }

    /**
     * 현재 보고있는 파일
     */
    private File focusedFile;

    /**
     * 프로젝트 소스 목록 텍스트 파일;
     */
    private File sourceListFile;

    /**
     * 편집중인 파일 목록
     */
    private static final ArrayList<File> editingFile = new ArrayList<>();//변화시 옵저버 굳독자한테 알림
}
