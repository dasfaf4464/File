package guiModel;

import java.io.*;
import java.util.ArrayList;

public class IdeFiles {
    private Project openedProject;
    private final ArrayList<File> openedFiles = new ArrayList<>();

    public IdeFiles() {
        openedProject = null;
    }

    public IdeFiles(Project openedProject) {
        this.openedProject = openedProject;
    }

    /**
     * 파일을 리스트에 추가하고 파일 내용 가져오기
     * @param file 오픈할 파일
     * @return 파일 내용
     */
    public String openFile(File file) {
        openedFiles.add(file);
        return readFile(file);
    }

    /**
     * 파일 내용 읽기
     * @param file 읽을 파일
     * @return 파일 내용 반환
     */
    public String readFile(File file) {
        String fileContent = null;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                fileContent += line + "\n";
            }
        } catch (IOException e) {

        }
        return fileContent;
    }

    /**
     * 파일 내용 저장
     * @param fileContent 저장할 내용
     * @param file 저장할 파일
     */
    public void saveFile(String fileContent, File file) {
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))){
            fileWriter.write(fileContent);
        } catch (IOException e) {

        }
    }


}
