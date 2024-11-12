package guiView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("#201812478_Jo#202111493_Min#202212979_Kim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container framePane = getContentPane();

        framePane.setLayout(new GridBagLayout());
        GridBagConstraints frameGBC = new GridBagConstraints();
        frameGBC.fill = GridBagConstraints.BOTH;
        frameGBC.weighty = 1.0;
        frameGBC.weightx = 1.0;
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, mainPanel);
        splitPane.setResizeWeight(0.5);

        framePane.add(splitPane, frameGBC);

        setSize(1000,600);
        setVisible(true);
    }

    private final MainPanel mainPanel = new MainPanel();
    private final SidePanel sidePanel = new SidePanel();
}