package guiModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 프로그램 전역 정보를 담고있는 프로퍼티 생성 및 받아오기
 * 사용자 소스파일에 프로퍼티 생성
 */
public class SettingManager {
    public SettingManager() {

    }

    /**
     * 파일이 존재하는지 확인하고 유효성 검사
     * @return 설치되었으면 true
     */
    public boolean isInstalled() {
        File propertiesFolder = new File(globalPropertiesFolder);
        if(!propertiesFolder.exists()) {
            return false;
        }

        File projectFile = new File(globalPropertiesFolder + globalProjectName);
        File jdkFile = new File(globalPropertiesFolder + globalJDKName);
        File guiFile = new File(globalPropertiesFolder + globalGuiName);
        if(projectFile.exists() && jdkFile.exists() && guiFile.exists()) {
            this.globalProjectFile = projectFile;
            this.globalJDKFile = jdkFile;
            this.globalGuiFile = guiFile;
            return true;
        }
        return false;
    }

    public void setProperty(Properties properties, String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * 설정 파일의 내용이 유효한지 검사.
     * jdk리스트 파일, ui 파일, 프로젝트 리스트 파일
     * @return 모든 파일이 유효하면 true
     */
    private boolean isValid() {
        return true;
    }

    /**
     * 세팅 파일에서 IDE로 값을 가져온다.
     * @return 설정 값이 담긴 Properties
     */
    private Properties getGlobalProjectProperties() {
        File globalProjectFile = new File(globalPropertiesFolder + this.globalProjectName);

        try {
            FileInputStream projectListFileStream = new FileInputStream(globalProjectFile);
            Properties properties = new Properties();
            properties.load(projectListFileStream);
            projectListFileStream.close();
            return properties;
        } catch (FileNotFoundException e) {
            System.out.println("global setting file not found");
            return null;
        } catch (IOException e) {
            System.out.println("global setting file read error");
            return null;
        }
    }

    private String globalPropertiesFolder = System.getProperty("user.home") + "\\Downloads\\Java_2024_2_Proj2";
    private final String globalProjectName = "//ProjectList.properties";
    private final String globalJDKName = "//JDKList.properties";
    private final String globalGuiName = "//GUI.properties";

    private File globalProjectFile;
    private File globalJDKFile;
    private File globalGuiFile;

    public Properties globalProjectProperties;
    public Properties globalJDKProperties;
    public Properties globalGuiProperties;
}