package guiView;

import guiView.MenubarPanel.SettingMenuBar;
import guiView.dialogPanel.CheckingView;
import guiView.dialogPanel.InstallingView;
import guiView.dialogPanel.SettingView;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    private InstallingView installerView;
    private SettingView settingView;
    private CheckingView checkingView;

    private JPanel contentPane;
    private JMenuBar dialogMenuBar;

    public Dialog(JFrame frame) {
        setLayout(new BorderLayout());
        dialogMenuBar = new SettingMenuBar();
        contentPane = new JPanel();
        setJMenuBar(dialogMenuBar);
        setContentPane(contentPane);
    }

    public void setDialogPanel(JPanel panel, JMenuBar menuBar) {
        this.dialogMenuBar = menuBar;
        this.contentPane = panel;
        this.repaint();
    }
}
