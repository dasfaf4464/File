package guiPresenter.dialogPresenter;

import GUIInterface.dialog.InstallingInterface;
import guiModel.Installer;
import guiModel.Project;
import guiView.dialogPanel.InstallingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstallingPresenter implements InstallingInterface.Presenter {
    private final InstallingView installingView;

    public InstallingPresenter(InstallingView installingView) {
        this.installingView = installingView;
        InstallingViewListener installingViewListener = new InstallingViewListener();
        this.installingView.setInstallingViewListener(installingViewListener);
    }

    /**
     * 프로젝트 경로나 이름 없이 생성하면 기본으로 생성
     * 프로젝트 경로와 이름 입력하면 프로젝트 생성
     * 이후 다이얼로그 종료 후 메인 프레임 생성
     */
    @Override
    public void saveButtonPressed() {
        String basicOut = installingView.getBasicOutputField();
        String basicJDK = installingView.getBasicJDKField();
        String projectFolder = installingView.getFirstProjectField();
        String projectName = installingView.getProjectNameField();

        Installer.installBasic(basicJDK, basicOut);
        if(!projectFolder.isBlank() && !projectName.isBlank()) {
            Project.makeProject(projectFolder, projectName);
        }
        installingView.parentDialog.dispose();
    }

    public class InstallingViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            switch (button.getText()) {
                case "Save": saveButtonPressed(); break;
            }

        }

    }
}
