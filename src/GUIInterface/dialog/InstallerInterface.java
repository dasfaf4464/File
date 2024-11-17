package GUIInterface.dialog;

import javax.swing.*;

public interface InstallerInterface {

    interface View {
        void setPanel(JPanel panel);

        String getBasicJDKField();

        String getBasicOutputField();

        String getFirstProjectField();

        String getProjectNameField();
    }

    interface Presenter {

    }
}
