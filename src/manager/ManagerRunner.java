package manager;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

//인스톨러를 새로운 클래스로 쪼개야하나?
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
     * it called only once.
     * set key of properties. all values are empty. excepts file searching dir.
     * make output folders.
     * @param settingFilePath is absolute path.
     * @return absolute path - "setting.properties"
     */
    public Properties installIDE(String settingFilePath) {
        Scanner scanner = new Scanner(System.in);
        Properties settings = new Properties();
        String line;
        StringTokenizer tokenizer;

        try {
            settings.load(new FileInputStream(makeSettingFile(settingFilePath)));

            System.out.println("##############################");
            System.out.println("put Property and value\nproperty file is saved at " + settingFilePath);

            for(Keys key: Keys.values()) {
                System.out.print(key.getKeyString() + ": ");
                settings.setProperty(key.getKeyString(), scanner.nextLine());
            }

            System.out.println("if you want to add another version of compiler then put [name=path], if you want to stop, then put enter key");
            do{
                line = scanner.nextLine();
                if(line.isEmpty()) {
                    break;
                }
                tokenizer = new StringTokenizer(line, "=");
                settings.setProperty(tokenizer.nextToken(), tokenizer.nextToken());
            } while(line.isEmpty());

            makeOutputFolder(settings.getProperty(Keys.OUTPUT.getKeyString()));
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
        File settingFile = new File(settingFilePath + "\\settings.properties");

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

    private void makeOutputFolder(String outputFilePath) {
        new File(outputFilePath + "\\output").mkdirs();
        new File(outputFilePath + "\\output\\Compiled").mkdir();
        new File(outputFilePath + "\\output\\Error").mkdir();
        new File(outputFilePath + "\\output\\Compiled\\Java").mkdir();
        new File(outputFilePath + "\\output\\Compiled\\C").mkdir();
        new File(outputFilePath + "\\output\\Error\\Java").mkdir();
        new File(outputFilePath + "\\output\\Error\\C").mkdir();
    }

    public void setBasic(Keys basicKey, Properties settingFile, String Keys) {
        String basicValue = settingFile.getProperty(Keys);
        settingFile.replace(basicKey.getKeyString(), basicValue);
    }

    public boolean addJava(String version, String abPath, Properties settings) {
        if(!settings.containsKey("Java" + version) && new File(abPath + " \\bin\\java.exe").exists()) {
            settings.setProperty("Java" + version, abPath);
            return true;
        }
        return false;
    }

    public boolean addC(String version, String abPath, Properties settings) {
        if(!settings.containsKey("GCC" + version) && new File(abPath + " \\bin\\gcc.exe").exists()) {
            settings.setProperty("GCC" + version, abPath);
            return true;
        }
        return false;
    }
}