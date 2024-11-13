package guiView.featureDialog;

import javax.swing.*;
import java.awt.*;

public class InstallerDialog extends JDialog {
    public InstallerDialog() {
        setTitle("Installer");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(false);
        setVisible(false);
    }

    public void showInstallerDialog() {
        setVisible(true);
        setModal(true);

    }

    private Container contentPane = getContentPane();
}