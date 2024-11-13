package guiModel;

import java.io.File;

public class Installer {
    public boolean isInstalled() {
        File settingFile = new File(settings.programPath+"settings.properties");
        if(!settingFile.exists()){
            return false;
        }
        else {
            //프로퍼티 유효성 검사
            return true;
        }
    }


    private Settings settings = new Settings();
}