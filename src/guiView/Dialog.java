package guiView;

import guiView.MenubarPanel.SettingMenuBar;
import guiView.dialogPanel.CheckingView;
import guiView.dialogPanel.InstallingView;
import guiView.dialogPanel.SettingView;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    private final InstallingView installingView;
    private final SettingView settingView;
    private final CheckingView checkingView;

    private final JMenuBar dialogMenuBar;

    public Dialog(Frame frame) {
        super(frame);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        installingView = new InstallingView(this);
        settingView = new SettingView();
        checkingView = new CheckingView();

        dialogMenuBar = new SettingMenuBar();

        setJMenuBar(dialogMenuBar);
    }

    public void setDialog(JPanel panel) {
        this.removeAll();
        this.add(panel, BorderLayout.CENTER);
        this.pack();
        this.repaint();
    }

    public JPanel getInstallingPanel() {
        return installingView;
    }
}
