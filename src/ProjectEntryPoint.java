import guiView.Frame;

import javax.swing.*;

public class ProjectEntryPoint {
    public static void main(String[] args) {

/*
        if(!new SettingManager().isInstalled()){
            SwingUtilities.invokeLater(MainFrame :: new);
        }
        else {
            SwingUtilities.invokeLater(OldDialog :: new);
        }

 */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        new Frame();

    }
}
