package guiPresenter.framePresenter;

import guiView.framePanel.FileTreeView;

import java.util.EventListener;

public class FileTreePresenter {
    private final FileTreeView fileTreeView;

    public FileTreePresenter(FileTreeView fileTreeView) {
        this.fileTreeView = fileTreeView;
        FileTreeViewListener fileTreeViewListener = new FileTreeViewListener();
        this.fileTreeView.setFileTreeViewListener(fileTreeViewListener);
    }

    public class FileTreeViewListener implements EventListener {

    }
}
