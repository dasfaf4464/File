package guiView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Java Code Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        SidePanel sidePanel = new SidePanel();
        MainPanel mainPanel = new MainPanel();

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        mainSplitPane.setDividerLocation(300);
        mainSplitPane.setContinuousLayout(true);
        mainSplitPane.setDividerSize(3);

        add(mainSplitPane, BorderLayout.CENTER);

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
