package guiView;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    MainPanel() {
        setBackground(Color.CYAN);
        setVisible(true);
        add(new JButton(new ImageIcon("test")));
    }
}
