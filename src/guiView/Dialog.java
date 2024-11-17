package guiView;

import guiView.MenubarPanel.SettingMenuBar;
import guiView.dialogPanel.CheckingView;
import guiView.dialogPanel.InstallingView;
import guiView.dialogPanel.SettingView;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog {
    public final InstallingView installingView;
    private final SettingView settingView;
    private final CheckingView checkingView;

    private JMenuBar dialogMenuBar;
    private final Frame frame;

    public Dialog(Frame frame) {
        super(frame);
        this.frame = frame;
        setLayout(new BorderLayout());

        installingView = new InstallingView(this);
        settingView = new SettingView();
        checkingView = new CheckingView();

        dialogMenuBar = new SettingMenuBar();

        setJMenuBar(dialogMenuBar);
    }

    public void setDialog(JPanel panel, JMenuBar menuBar) {
        this.dialogMenuBar = menuBar;
        this.setContentPane(panel);
        this.repaint();
    }
}
