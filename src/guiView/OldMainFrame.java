package guiView;

import javax.swing.*;
import java.awt.*;

public class OldMainFrame extends JFrame {

    public OldMainFrame() {
        setTitle("Java Code Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setJMenuBar(new OldMenu());
        OldLeftPanel oldLeftPanel = new OldLeftPanel();
        OldRightPanel oldRightPanel = new OldRightPanel();
        OldDialog oldDialog = new OldDialog();
        OldDialog.FileChooserDialog fileChooserDialog = new OldDialog.FileChooserDialog(oldDialog);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, oldLeftPanel, oldRightPanel);
        mainSplitPane.setDividerLocation(300);
        mainSplitPane.setContinuousLayout(true);
        mainSplitPane.setDividerSize(3);

        add(mainSplitPane, BorderLayout.CENTER);

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
