package guiView;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    MainPanel() {
        setBackground(Color.CYAN);
        setLayout(new GridBagLayout());
        GridBagConstraints panelGCB = new GridBagConstraints();
        panelGCB.fill = GridBagConstraints.BOTH;
        panelGCB.weightx = 1.0;

        panelGCB.gridy = 0;
        add(mainButton, panelGCB);

        panelGCB.gridy = 1;
        add(mainFileOpener, panelGCB);

        panelGCB.gridy = 2;
        panelGCB.weighty = 0.7;
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textEditorView, resultView);
        splitPane.setResizeWeight(0.7);
        add(splitPane, panelGCB);
    }

    class MainFileOpener extends JPanel {
        public MainFileOpener() {
            setLayout(new GridBagLayout());
            setBackground(Color.CYAN);
            GridBagConstraints fileGCB = new GridBagConstraints();
            fileGCB.fill = GridBagConstraints.BOTH;
            fileGCB.ipadx = 10;

            fileGCB.gridx = 0;
            fileGCB.gridy = 0;
            add(new JTextField("open"), fileGCB);

            fileGCB.gridy = 1;
            add(new JTextField("save"), fileGCB);

            fileGCB.gridx = 1;
            fileGCB.gridy = 0;
            add(new JButton("Open"), fileGCB);

            fileGCB.gridy = 1;
            add(new JButton("Save"), fileGCB);
        }
    }

    class MainButton extends JPanel {
        public MainButton() {
            setBackground(Color.yellow);
            setPreferredSize(new Dimension(300, 50));
            setMaximumSize(new Dimension(300, 50));
            setLayout(new FlowLayout());
            this.add(new JButton("save"));
            this.add(new JButton("compile"));
            this.add(new JButton("delete"));
            this.add(new JButton("clear"));
        }
    }

    private final MainButton mainButton = new MainButton();
    private final MainFileOpener mainFileOpener = new MainFileOpener();
    private final TextEditorView textEditorView = new TextEditorView();
    private final ResultView resultView = new ResultView();
}
