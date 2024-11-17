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
    private JFrame frame;

    public Dialog(JFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        installerView = new InstallingView(this);
        //settingView = new SettingView(this);
       //checkingView = new CheckingView(this);

        dialogMenuBar = new SettingMenuBar();
        contentPane = new JPanel();

        setJMenuBar(dialogMenuBar);
        setContentPane(contentPane);
    }

    public void setDialog(JPanel panel, JMenuBar menuBar) {
        this.dialogMenuBar = menuBar;
        this.contentPane = panel;
        this.repaint();
    }
}
