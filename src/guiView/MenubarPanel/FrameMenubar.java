package guiView.MenubarPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameMenubar extends JMenuBar {
    private ActionListener menuListener;

    public FrameMenubar() {
        JMenu fileMenu = new JMenu("File");
        JMenu runMenu = new JMenu("Run");
        JMenu projectMenu = new JMenu("Project");
        JMenu settingsMenu = new JMenu("Settings");

        ArrayList<JMenuItem> fileMenuItems = new ArrayList<>();
        ArrayList<JMenuItem> runMenuItems = new ArrayList<>();
        ArrayList<JMenuItem> projectMenuItems = new ArrayList<>();

        fileMenuItems.add(new JMenu("Open"));
        fileMenuItems.add(new JMenu("Close"));
        fileMenuItems.add(new JMenu("Save"));
        fileMenuItems.add(new JMenu("Save as"));
        fileMenuItems.add(new JMenu("Quit"));

        runMenuItems.add(new JMenu("Compile"));
        runMenuItems.add(new JMenu("CompileAndRun"));
        runMenuItems.add(new JMenu("Run"));

        for(JMenuItem menuItem : fileMenuItems) {
            fileMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuListener);
        }

        for(JMenuItem menuItem : runMenuItems) {
            runMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuListener);
        }

        for(JMenuItem menuItem : projectMenuItems) {
            projectMenu.add(menuItem);
            menuItem.setActionCommand(menuItem.getText());
            menuItem.addActionListener(menuListener);
        }

        add(fileMenu);
        add(runMenu);
        add(projectMenu);
        add(settingsMenu);
    }
}
