package guiModel;

import java.awt.*;
import java.io.*;
import java.util.Properties;


/**
 * IDE 초기 설정을 담당하는 클래스, 반복되는 스트링 -> 열거나 상수로 만들어서 사용해야함
 */
public class Installer {
    public final static String idePropFolder = System.getProperty("user.home") + "\\Downloads\\Java_2024_2_Proj3_setting";
    public final static String idePropName = "\\Project3.properties";
    public final static String projectListFileName = "\\ProjectList.properties";
    public final static String JDKListFileName = "\\JDKList.properties";

    /**
     * 파일이 존재하는지 확인, 유효성 검사 필요
     * @return 전역설정, 프로젝트 리스트, jdk 리스트 전부 존재하면 true
     */
    public static boolean isInstalled() {
        if(new File(idePropFolder).exists()) {
            File ideProp = new File(idePropFolder, idePropName);
            File projectList = new File(idePropFolder, projectListFileName);
            File jdkList = new File(idePropFolder, JDKListFileName);
            return ideProp.exists() && projectList.exists() && jdkList.exists()&&isIDEPropValid(ideProp);
        } else {
            return false;
        }
    }

    /**
     * 키값 손상 검사
     * @param propFile ide 프로퍼티 파일 대상
     * @return 유효하면 true
     */
    private static boolean isIDEPropValid(File propFile) {
        Properties prop = new Properties();
        PropertiesUtil.loadProperties(prop, propFile);
        return prop.containsKey("basicjdk")&&prop.containsKey("out")&&prop.containsKey("fontsize")
                &&prop.containsKey("font")&&prop.containsKey("theme");
    }

    /**
     * 프로젝트 생성 없이 설치, ide 전역 세팅파일 초기화, jdklist에 기본 jdk 등록, projectlist는 파일만 생성.
     * @param basicJDK 기본 jdk, 프로젝트 생성시 따로 설정하지 않으면 프로젝트 jdk
     * @param basicOutput 기본 out폴더, 폴더가 존재하지 않으면 생성
     */
    public static void installBasic(String basicJDK, String basicOutput) {
        File ideFolder = new File(idePropFolder);
        File outFolder = new File(basicOutput);
        ideFolder.mkdirs();
        outFolder.mkdirs();
        File idePropFile = new File(idePropFolder + idePropName);
        File projectListFile = new File(idePropFolder + projectListFileName);
        File jdkListFile = new File(idePropFolder + JDKListFileName);

        try{
            idePropFile.createNewFile();
            projectListFile.createNewFile();
            jdkListFile.createNewFile();
        } catch(IOException e){
                return;
        }

        Properties ideProp = new Properties();
        Properties jdkList = new Properties();
        ideProp.setProperty("basicjdk", basicJDK);
        ideProp.setProperty("out", basicOutput);
        ideProp.setProperty("font", Font.DIALOG);
        ideProp.setProperty("fontsize", "14");
        ideProp.setProperty("theme", "default");
        ideProp.setProperty("lastproject", "");
        ideProp.setProperty("width", "1280");
        ideProp.setProperty("height", "720");
        PropertiesUtil.saveProperties(ideProp, idePropFile, null);

        jdkList.setProperty(new File(basicJDK).getName(), basicJDK);
        PropertiesUtil.saveProperties(jdkList, jdkListFile, null);
    }

}
