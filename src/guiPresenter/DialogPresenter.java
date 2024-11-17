package guiPresenter;

import GUIInterface.DialogInterface;
import guiModel.Project;
import guiView.OldDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPresenter implements DialogInterface.DialogPresenterInterface {

    private final OldDialog oldDialog;

    public DialogPresenter(OldDialog OldDialog) {
        this.oldDialog = OldDialog;
        installerListener dialogButtonListener = new installerListener();
        oldDialog.setEventListener(dialogButtonListener, null);
    }

    @Override
    public void installerSaveButtonClicked() {
        String basicJDK = oldDialog.getBasicJDKField();
        String basicOutput = oldDialog.getBasicOutputField();
        String newProjectPath = oldDialog.getFirstProjectField();
        String projectName = oldDialog.getProjectNameField();

        if(!newProjectPath.isBlank() && !projectName.isBlank()) {
            Project.makeNewProjectFolder(newProjectPath, projectName);
        }

        oldDialog.dispose();
    }

    public class installerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            switch (button.getText()) {
                case "Save": installerSaveButtonClicked(); break;
            }
        }
    }

    public class settingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
