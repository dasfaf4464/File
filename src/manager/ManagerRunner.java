package manager;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 */
public class ManagerRunner {

    /**
     * check having "settings.properties" in program folder.
     * @param settingFilePath is basically the folder where IDE is installed at.
     * @return true if a setting file exits.
     */
    public boolean settingFileExits(String settingFilePath) {
        File settingFile = new File(settingFilePath + "\\settings.properties");
        return settingFile.exists();
    }

    /**
     * @param settingFilePath is the folder where IDE is installed at.
     * @return property is IDE system Property.
     */
    public Properties getSettingFile(String settingFilePath) {
        try {
            FileInputStream settingFileStream = new FileInputStream(settingFilePath + "\\settings.properties");
            Properties properties = new Properties();
            properties.load(settingFileStream);

            return properties;
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
            return null;
        } catch (IOException e) {
            System.out.println("setting file read error");
            return null;
        }
    }

    /**
     *
     * @param settingFilePath is location that "settings.properties" is saved at.
     * @param settingFile is IDE setting instance. it might be changed.
     * @return save success?
     */
    public boolean saveSettingFile(String settingFilePath, Properties settingFile) {
        try {
            FileOutputStream settingFileStream = new FileOutputStream(settingFilePath + "\\settings.properties");
            settingFile.store(settingFileStream, null);
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * set key of properties. all values are empty. excepts file searching dir.
     * @param settingFilePath is absolute path.
     * @return absolute path - "setting.properties"
     */
    public Properties installIDE(String settingFilePath) {
        Scanner scanner = new Scanner(System.in);
        File settingFile = makeSettingFile(settingFilePath);
        FileInputStream settingFileStream;
        Properties settings = new Properties();

        try {
            settingFileStream = new FileInputStream(settingFile);
            settings.load(settingFileStream);

            System.out.println("##############################");
            System.out.println("put dir to use in IDE. this values are saved in IDE folder : " + settingFile);
            for(Keys keyValue : Keys.values()) {
                System.out.print(keyValue.getKeyString() + ":");
                settings.setProperty(keyValue.getKeyString(), scanner.nextLine());
            }

            changeProperties("file", System.getProperty("user.dir"), settings);

            saveSettingFile(settingFilePath, settings);
        } catch (FileNotFoundException e) {
            System.out.println("setting file not found");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("setting file read error");
            System.exit(1);
        }
        return settings;
    }

    private File makeSettingFile(String settingFilePath) {
        File settingFile = new File(settingFilePath + "settings.properties");

        if(!settingFile.exists()) {
            try {
                settingFile.createNewFile();
            } catch (IOException e) {
                System.out.println("setting file creation error");
                System.exit(1);
            }
        }

        return settingFile;
    }

    private boolean addProperties(String propertyKey, String propertyValue, Properties settings) {
        return settings.setProperty(propertyKey, propertyValue) != null;
    }

    private boolean deleteProperties(String propertyKey, Properties settings) {
        return settings.remove(propertyKey) != null;
    }

    private String getValue(String propertyKey, Properties settings) {
        return settings.getProperty(propertyKey);
    }

    private boolean changeProperties(String propertyKey, String newValues, Properties settings) {
        return settings.replace(propertyKey, newValues) != null;
    }
}