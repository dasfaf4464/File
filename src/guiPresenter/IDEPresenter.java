package guiPresenter;

import guiModel.Project;
import guiModel.SettingManager;
import guiView.Dialog;
import guiView.MainFrame;

import javax.swing.*;
import java.util.ArrayList;

public class IDEPresenter {
    private SettingManager settingManager;
    private ArrayList<Project> ideProjectList;

    public IDEPresenter() {
        settingManager = new SettingManager();
        ideProjectList = new ArrayList<>();

        initializeIDE();
    }

    private void initializeIDE() {
        if(settingManager.isInstalled()) {
            SwingUtilities.invokeLater(MainFrame::new);
        } else {
            SwingUtilities.invokeLater(Dialog::new);
        }
    }

}
