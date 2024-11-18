package guiView.MenubarPanel;

import guiPresenter.menuBarPresenter.FrameMenuBarPresenter;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FrameMenubar extends JMenuBar {
    private JFileChooser fileChooser;

    private MouseInputListener menuListener;
    private ActionListener menuItemListener;

    public FrameMenubar() {
        new FrameMenuBarPresenter(this);

        fileChooser = new JFileChooser(System.getProperty("user.home")+"\\Downloads");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        JMenu fileMenu = new JMenu("File");
        JMenu runMenu = new JMenu("Run");
        JMenu projectMenu = new JMenu("Project");
        JMenu settingsMenu = new JMenu("Settings");
        settingsMenu.setActionCommand("Settings");
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setActionCommand("Help");

        ArrayList<JMenuItem> fileMenuItems = new ArrayList<>();
        ArrayList<JMenuItem> runMenuItems = new ArrayList<>();
        ArrayList<JMenuItem> projectMenuItems = new ArrayList<>();

        fileMenuItems.add(new JMenuItem("Open"));
        fileMenuItems.add(new JMenuItem("Close"));
        fileMenuItems.add(new JMenuItem("Save"));
        fileMenuItems.add(new JMenuItem("Save as"));
        fileMenuItems.add(new JMenuItem("Quit"));

        runMenuItems.add(new JMenuItem("Compile"));
        runMenuItems.add(new JMenuItem("CompileAndRun"));
        runMenuItems.add(new JMenuItem("Run"));

        for(JMenuItem menuItem : fileMenuItems) {
            fileMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuItemListener);
        }

        for(JMenuItem menuItem : runMenuItems) {
            runMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuItemListener);
        }

        for(JMenuItem menuItem : projectMenuItems) {
            projectMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuItemListener);
        }

        add(fileMenu);
        add(runMenu);
        add(projectMenu);
        add(settingsMenu);
        add(helpMenu);
        settingsMenu.addMouseListener(menuListener);
        helpMenu.addMouseListener(menuListener);
    }

    public void showJFileChooser() {
        this.fileChooser.showOpenDialog(FrameMenubar.this);
    }

    public File getChooseFile() {
        return fileChooser.getSelectedFile();
    }

    public void setMenuBarListener(MouseInputListener menuListener, ActionListener menuItemListener) {
        this.menuListener = menuListener;
        this.menuItemListener = menuItemListener;
    }
}
