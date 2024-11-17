package guiModel;

import java.io.*;
import java.util.Properties;

/**
 * 프로퍼티 유틸 제공
 */
public class PropertiesUtil {
    private PropertiesUtil() {
        
    }

    /**
     * 프로퍼티 파일을 프로퍼티로 가져오기
     * @param properties
     * @param propertiesFile
     */
    public static void loadProperties(Properties properties, File propertiesFile) {
        try (FileInputStream fileStream = new FileInputStream(propertiesFile)) {
            properties.load(fileStream);
        } catch (FileNotFoundException e) {
            System.err.println("설정 파일을 찾을 수 없습니다: " + propertiesFile.getPath());
        } catch (IOException e) {
            System.err.println("설정 파일 읽기 오류: " + propertiesFile.getPath());
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
        try (FileOutputStream fileStream = new FileOutputStream(propertiesFile)) {
            properties.store(fileStream, comment);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("설정 파일을 찾을 수 없습니다: " + propertiesFile.getPath());
        } catch (IOException e) {
            System.err.println("설정 파일 저장 오류: " + propertiesFile.getPath());
        }
        return false;
    }

}
