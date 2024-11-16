package GUIInterface;

import javax.swing.*;

public interface DialogInterface {

    interface DialogViewInterface {

        void setPanel(JPanel panel);

        String getBasicJDKField();

        String getBasicOutputField();

        String getFirstProjectField();
    }

    interface DialogPresenterInterface {
        void installerSaveButtonClicked();
    }
}
