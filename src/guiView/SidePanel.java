package guiView;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    SidePanel() {
        setBackground(Color.pink);
        setLayout(new GridBagLayout());

        GridBagConstraints panelGCB = new GridBagConstraints();
        panelGCB.fill = GridBagConstraints.BOTH;
        panelGCB.weightx = 1.0;

        panelGCB.gridy = 0;
        add(fileButton, panelGCB);

        panelGCB.gridy = 1;
        add(fileSearcher, panelGCB);

        panelGCB.gridy = 2;
        panelGCB.weighty = 0.8;
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileListView, textListView);
        splitPane.setResizeWeight(0.7);
        add(splitPane, panelGCB);
    }

    class FileButton extends JPanel {
        public FileButton() {
            setLayout(new FlowLayout());
            this.add(new JButton("delete"));
            this.add(new JButton("make"));
        }
    }

    class FileSearcher extends JPanel {
        public FileSearcher() {
            setLayout(new FlowLayout());
            this.add(new JTextField("searching"));
        }

    }

    private final FileButton fileButton = new FileButton();
    private final FileSearcher fileSearcher = new FileSearcher();
    private final FileView fileListView = new FileView();
    private final TextListView textListView = new TextListView();
}
