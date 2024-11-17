package guiModel;

import java.io.*;
import java.util.Properties;

/**
 * 프로퍼티 유틸 제공
 */
public class PropertiesUtil {
    public PropertiesUtil() {
        
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

    /**
     * 프로퍼티를 파일에 저장
     * @param properties
     * @param propertiesFilePath
     * @param comment
     * @return
     */
    public static boolean saveProperties(Properties properties, String propertiesFilePath, String comment) {
        return saveProperties(properties, new File(propertiesFilePath), comment);
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

}
