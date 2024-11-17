package GUIInterface.dialog;

import guiView.dialogPanel.InstallingView;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface InstallingInterface {

    interface View {
        void setInstallingViewListener(ActionListener installingViewListener);

        String getBasicJDKField();
        String getBasicOutputField();
        String getFirstProjectField();
        String getProjectNameField();
    }

    interface Presenter {
        void saveButtonPressed();
    }
}
