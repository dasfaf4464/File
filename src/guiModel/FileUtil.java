package guiModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import java.io.File;
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
    public void openNewFile(File file) {
        this.activatedFile = file;
        edttingFile.add(activatedFile);
    }

    /**
     * 파일이 열려있을 때 새로운 파일이나 리스트에 있는 파일과 교환
     * @param file 텍스트 에디터에 여는 파일
     */
    public void swapFile(File file) {
        this.activatedFile = file;

        if(!edttingFile.contains(file)) {
            edttingFile.add(file);
        }
    }

    /**
     * 현재 파일의 내용을 스트링으로 저장
     * @return
     */
    public String getFileContent() {
        String content;
        try {
            BufferedReader contentBufferReader = new BufferedReader(new FileReader(activatedFile));
            while(true) {
                //contentBufferReader.
            }
        } catch (FileNotFoundException e) {
            //현재 파일을 찾을수 없음
        }
        return null;
    }

    private File activatedFile;
    private static ArrayList<File> edttingFile = new ArrayList<File>();
}
