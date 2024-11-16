package GUIInterface;

import javax.swing.*;

public interface DialogInterface {

    interface DialogViewInterface {

        void setPanel(JPanel panel);

        String getBasicJDKField();

        String getBasicOutputField();

        String getFirstProjectField();

        String getProjectNameField();
    }

    interface DialogPresenterInterface {
        void installerSaveButtonClicked();
    }
}
