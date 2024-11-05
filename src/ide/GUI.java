package ide;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("GUI");
        setSize(1360,768);
        setResizable(false);

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        setLocationRelativeTo(null);
        Container pane = getContentPane();
        Menu menu = new Menu();
        pane.setBackground(Color.LIGHT_GRAY);

        JPanel mainPanel = new JPanel();
        JPanel subPanel = new JPanel();

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.pink);
        subPanel.setLayout(null);
        subPanel.setBackground(Color.CYAN);

        JLabel j1 = new JLabel("hello");
        JLabel j2 = new JLabel("world");

        subPanel.add(j1);
        subPanel.add(j2);
        mainPanel.add(j1);
        mainPanel.add(j2);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.3;
        c.weighty = 1.0;
        pane.add(subPanel, c);
        c.weightx = 0.7;
        c.weighty = 1.0;
        pane.add(mainPanel, c);


        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
