package manager;

import ide.IDEComponent;
import ide.Mode;
import java.util.LinkedList;


/**
 *
 */
public class ManagerCompo extends IDEComponent {

    public ManagerCompo(Mode mode) {
        setMode(mode);
    }

    @Override
    public void executeComponent() {
        switch(mode) {
            case Mode.managerCHECK: {
                managerRunner.checkSettingFile(settingFilePath); break;
            }
            case Mode.managerHAVE: {
                managerRunner.getSettingFile(settingFilePath); break;
            }
            case Mode.managerSETPATH: {
                break;
            }
        }
    }

    @Override
    public void showComponent() {
        switch (mode) {
            case managerCHECK: {
                managerViewer.showChecking(); break;
            }
            case managerHAVE: {
                managerViewer.showPropertyList(); break;
            }
        }
    }

    @Override
    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x50 < modeValue && modeValue <= 0x51) {
            indexMode = m;
        } else if(0x51 < modeValue && modeValue <= 0x54) {
            runableMode = m;
        } else if(0x5C < modeValue && modeValue <= 0x5F) {
            viewingMode = m;
        }
        mode = m;
    }

    //임시 설정
    public static LinkedList<String> settingFileLines;
    public static String basicGCC = "C:\\Custom\\Compiler\\MinGW";
    public static String basicJavaJDK = "C:\\Custom\\Library\\JAVA\\jdk-21.0.3";
    public static String basicErrorFolderPath = "C:\\Custom\\Data\\oop\\TP\\File\\insert\\Error";
    public static String basicCompileFolder = "C:\\Custom\\Data\\oop\\TP\\File\\insert\\Compile";
    public static LinkedList<String> javaJDK = new LinkedList<>();
    public static LinkedList<String> CCompiler  = new LinkedList<>();
    public static String settingFilePath = System.getProperty("user.dir");
    public static String basicFileStarting = System.getProperty("user.dir");

    public static ManagerViewer managerViewer = new ManagerViewer();
    public static ManagerRunner managerRunner = new ManagerRunner();
}