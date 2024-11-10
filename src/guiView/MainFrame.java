package guiView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("#201812478_Jo#202111493_Min#202212979_Kim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container framePanel = getContentPane();
        framePanel.setLayout(new GridBagLayout());
        framePanel.setBackground(Color.GRAY);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 75;
        c.weighty = 100;
        c.gridx = 1;
        c.gridy = 0;
        framePanel.add(mainPanel, c);
        c.weightx = 25;
        c.weighty = 100;
        c.gridx = 0;
        c.gridy = 0;
        framePanel.add(sidePanel, c);

        setSize(1280,720);
        setVisible(true);
    }

    MainPanel mainPanel = new MainPanel();
    SidePanel sidePanel = new SidePanel();
}