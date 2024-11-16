package guiPresenter;

import guiModel.Project;
import guiModel.SettingManager;
import guiView.Dialog;
import guiView.MainFrame;

import java.util.ArrayList;

public class IDEPresenter {
    private SettingManager settingManager;
    private ArrayList<Project> ideProjectList;
    private final MainFrame mainFrame;
    private final Dialog dialog;

    public IDEPresenter() {
        settingManager = new SettingManager();
        ideProjectList = new ArrayList<>();
        mainFrame = new MainFrame();
        dialog = new Dialog();

        initializeIDE();
    }

    private void initializeIDE() {
        if(settingManager.isInstalled()) {

        } else {

        }
    }

}
