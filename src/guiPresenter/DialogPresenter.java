package guiPresenter;

import GUIInterface.DialogInterface;
import guiView.Dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPresenter implements DialogInterface.DialogPresenterInterface {

    private final Dialog dialog;

    public DialogPresenter(Dialog Dialog) {
        this.dialog = Dialog;
        installerListener dialogButtonListener = new installerListener();
        dialog.setEventListener(dialogButtonListener, null);
    }

    @Override
    public void installerSaveButtonClicked() {
        String basicJDK = dialog.getBasicJDKField();
        String basicOutput = dialog.getBasicOutputField();
        dialog.dispose();
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
