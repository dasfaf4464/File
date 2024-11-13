package guiModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * ide 전역 설정
 */
public class Settings {

    public String programPath = System.getProperty("user.dir");
    private Properties settingProperty;
    private List<File> jdkList;

    int LastWidth;
    int LastHeight;
    int textEditorFontSize;

    /**
     * 프로젝트 설정
     */
    public class Project {
        public Project() {

        }

        File projectFilePath;
        File projectJDK;
        List<String> arguments = new ArrayList<String>();
    }
}