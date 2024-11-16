import guiModel.SettingManager;
import guiView.MainFrame;
import guiView.Dialog;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        if(!new SettingManager().isInstalled()){
            SwingUtilities.invokeLater(MainFrame :: new);
        }
        else {
            SwingUtilities.invokeLater(Dialog :: new);
        }
    }
}
