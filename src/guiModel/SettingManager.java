package guiModel;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * 프로그램 전역 정보를 담고있는 프로퍼티 생성 및 받아오기
 * 사용자 소스파일에 프로퍼티 생성
 * 프로퍼티 유틸 제공
 */
public class SettingManager {
    private String globalPropertiesFolder = System.getProperty("user.home") + "\\Downloads\\Java_2024_2_Proj3_201812478";//기존 프로그램 파일하고 안겹치게 학번첨부
    private final static String globalPropertiesName = "\\Project3.properties";
    private final static String projectListFileName = "\\ProjectList.properties";
    private final static String JDKListFileName = "\\JDKList.properties";

    private File ProjectListFile;
    private File JDKListFile;
    private File globalPropertiesFile;

    /**
     * 생성된 프로젝트
     */
    public Properties projectListProperties;
    /**
     * JDK리스트 절대경로
     */
    public Properties JDKListProperties;
    /**
     * 기본 jdk
     * 기본 output
     * 공용 글씨 크기
     * 공용 폰트
     * 공용 테마
     */
    public Properties globalProperties;

    /**
     * IDE 공통 프로퍼티 기져오기
     */
    public SettingManager() {
        ProjectListFile = new File(globalPropertiesFolder + File.separator + projectListFileName);
        JDKListFile = new File(globalPropertiesFolder + File.separator + JDKListFileName);
        globalPropertiesFile = new File(globalPropertiesFolder + File.separator + globalPropertiesName);
    }

    /**
     * 프로퍼티 파일이 존재하는지 확인하고 유효성 검사
     * @return 설치되었으면 true
     */
    public boolean isInstalled() {
        if(!(new File(globalPropertiesFolder).exists())) {
            return false;
        }
        else if(!ProjectListFile.exists()) {
            return false;
        } else if (!JDKListFile.exists()) {
            return false;
        } else if (!globalPropertiesFile.exists()) {
            return false;
        } else {
            return true;
        }
    }

    public void installWithProject(String basicJDK, String basicOutput, String newProject) {
        installBasic(basicJDK, basicOutput);
        addProperties(projectListProperties, new File(newProject).getName(), newProject);
        saveProperties(projectListProperties, ProjectListFile, null);
        //Project

    }

    public void installBasic(String basicJDK, String basicOutput) {
        if(!this.isInstalled()) {
            File ideFolder = new File(globalPropertiesFolder);
            ideFolder.mkdirs();
            ideFolder.delete();
            ideFolder.mkdirs();
            globalPropertiesFile.mkdirs();
            JDKListFile.mkdirs();
            ProjectListFile.mkdirs();

            loadProperties(globalProperties, globalPropertiesFile);
            loadProperties(JDKListProperties, JDKListFile);
            loadProperties(projectListProperties, ProjectListFile);

            addProperties(globalProperties, GlobalProperties.BASICJDK, basicJDK);
            addProperties(globalProperties, GlobalProperties.BASICOUTPUT, basicOutput);
            addProperties(globalProperties, GlobalProperties.FONT, Font.DIALOG);
            addProperties(globalProperties, GlobalProperties.FONTSIZE, "12");
            addProperties(globalProperties, GlobalProperties.LASTPROJECT, "");
            saveProperties(globalProperties, globalPropertiesFile, null);
            saveProperties(projectListProperties, ProjectListFile, null);

            addProperties(JDKListProperties, new File(basicJDK).getName(), basicJDK);
            saveProperties(JDKListProperties, JDKListFile, null);
        }
    }

    /**
     * 프로퍼티 파일을 프로퍼티로 가져오기
     * @param properties
     * @param propertiesFile
     */
    public static void loadProperties(Properties properties, File propertiesFile) {
        try {
            FileInputStream settingFileStream = new FileInputStream(propertiesFile);
            properties.load(settingFileStream);
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
        } catch (IOException e) {
            System.out.println("setting file read error");
        }
    }

    public static boolean saveProperties(Properties properties, String propertiesFilePath, String comment) {
        try {
            FileOutputStream propertiesFileStream = new FileOutputStream(propertiesFilePath);
            properties.store(propertiesFileStream, comment);
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static boolean saveProperties(Properties properties, File propertiesFile, String comment) {
        try {
            FileOutputStream propertiesFileStream = new FileOutputStream(propertiesFile);
            properties.store(propertiesFileStream, comment);
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    public static void addProperties(Properties properties, String Key, String comment) {
        properties.setProperty(Key, comment);
    }

    public static void removeProperties(Properties properties, String Key) {
        if(properties.containsKey(Key)) {
            properties.remove(Key);
        }
    }

    public static void changeProperties(Properties properties, String Key, String comment) {
        if(properties.containsKey(Key)&&properties.containsKey(comment)) {
            properties.setProperty(Key, comment);
        }
    }

    /**
     * 프로퍼티에 키, 밸류 저장
     * @param properties
     * @param key
     * @param value
     */
    public static void setProperty(Properties properties, String key, String value) {
        properties.setProperty(key, value);
    }

    public static class GlobalProperties {
        public static final String FONT= "font";//값으로 Font 클래스 이용
        public static final String FONTSIZE= "fontsize";
        public static final String BASICJDK= "jdk";
        public static final String BASICOUTPUT = "out";
        public static final String LASTPROJECT = "lastproject";
    }
}
