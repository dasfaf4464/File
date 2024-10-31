package manager;

import ide.IDE;
import ide.IDEComponent;
import ide.Mode;
import java.util.Properties;

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
                if(managerRunner.settingFileExits(System.getProperty("user.dir"))){
                    setMode(Mode.managerHAVE);
                } else {
                    setMode(Mode.managerINSTALLER);
                } break;
            }
            case Mode.managerHAVE: settings = managerRunner.getSettingFile(System.getProperty("user.dir")); break;
            case Mode.managerINSTALLER: settings = managerRunner.installIDE(System.getProperty("user.dir")); break;
            case Mode.managerSETPATH: {
                break;
            }
        }
    }

    @Override
    public void showComponent() {
        switch (mode) {
            case managerCHECK: managerViewer.showChecking(); break;
            case managerLIST: managerViewer.showPropertyList(settings); break;
        }
    }

    @Override
    public void setMode(Mode m){
        int modeValue = m.getValue();
        if(0x50 < modeValue && modeValue <= 0x51) {
            indexMode = m;
        } else if(0x51 < modeValue && modeValue <= 0x55) {
            runableMode = m;
        } else if(0x5C < modeValue && modeValue <= 0x5F) {
            viewingMode = m;
        }
        mode = m;
    }

    @Override
    public void interpretCommand(String command, String Option) {
        if(mode.equals(Mode.managerLIST)) {
            switch (command) {
                case "1", "change": setMode(Mode.managerSETPATH); break;
                case "2", "exit": IDE.compoCaller.returnComponent(); break;
            }
        } if(mode.equals(Mode.managerSETPATH)) {
            switch (command) {

            }
        }
    }

    public static String getPropertyValue(String PropertyName) {
        return settings.getProperty(PropertyName);
    }

    private static Properties settings;

    private final ManagerViewer managerViewer = new ManagerViewer();
    private final ManagerRunner managerRunner = new ManagerRunner();
}