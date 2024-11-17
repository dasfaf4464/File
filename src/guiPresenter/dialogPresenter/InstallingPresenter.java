package guiPresenter.dialogPresenter;

import GUIInterface.dialog.InstallingInterface;
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

    @Override
    public void saveButtonPressed() {
        installingView.getBasicOutputField();

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
