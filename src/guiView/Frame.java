package guiView;

import guiView.MenubarPanel.FrameMenubar;
import guiView.framePanel.HistoryView;
import guiView.framePanel.FileTreeView;
import guiView.framePanel.ResultView;
import guiView.framePanel.TextEditorView;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final RightPanel rightPanel;
    private final LeftPanel leftPanel;
    private final FrameMenubar frameMenubar;
    private final JDialog dialog;

    public Frame(int width, int height, boolean visible) {
        rightPanel = new RightPanel();
        leftPanel = new LeftPanel();
        frameMenubar = new FrameMenubar();
        dialog = new guiView.Dialog(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JSplitPane horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        horizontalSplitPane.setResizeWeight(0.25);
        horizontalSplitPane.setContinuousLayout(true);
        horizontalSplitPane.setDividerSize(3);

        this.setJMenuBar(frameMenubar);
        add(horizontalSplitPane, BorderLayout.CENTER);

        //######### 임시 지정
        width = 1280;
        height = 720;
        //#########
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(visible);
    }

    public static class RightPanel extends JPanel {
        private final TextEditorView textEditorView;
        private final ResultView resultView;

        public RightPanel() {
            textEditorView = new TextEditorView();
            resultView = new ResultView();

            setLayout(new BorderLayout());

            JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textEditorView, resultView);
            verticalSplitPane.setResizeWeight(0.7);
            verticalSplitPane.setOneTouchExpandable(true);
            verticalSplitPane.setDividerSize(8);

            add(verticalSplitPane, BorderLayout.CENTER);
        }
    }

    public static class LeftPanel extends JPanel {
        private final FileTreeView fileTreeView;
        private final HistoryView historyView;

        public LeftPanel() {
            fileTreeView = new FileTreeView();
            historyView = new HistoryView();

            setLayout(new BorderLayout());

            JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileTreeView, historyView);
            verticalSplitPane.setResizeWeight(0.5);
            verticalSplitPane.setOneTouchExpandable(true);
            verticalSplitPane.setDividerSize(8);

            add(verticalSplitPane, BorderLayout.CENTER);
        }
    }
}
