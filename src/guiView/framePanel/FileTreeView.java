package guiView.framePanel;

import GUIInterface.FileTreeInterface;
import guiPresenter.framePresenter.FileTreePresenter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.EventListener;


public class FileTreeView extends JPanel implements FileTreeInterface.View {
    private final JTextField searchTextField;
    private final JPopupMenu searchPopupList;
    private final DefaultTreeModel treeModel;
    private Color searchingColor;
    private Color treeColor;

    private EventListener fileTreeListener;

    public FileTreeView() {
        new FileTreePresenter(this);

        setLayout(new BorderLayout());

        searchTextField = new JTextField("솔직히 개무리");
        searchPopupList = new JPopupMenu();
        treeModel = new DefaultTreeModel(null);

        //############## test
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root1", true);
        root.add(new DefaultMutableTreeNode("root1child"));
        DefaultMutableTreeNode root2 = new DefaultMutableTreeNode("root2");
        root.add(root2);
        DefaultMutableTreeNode root3 = new DefaultMutableTreeNode("root3");
        root.add(root3);
        DefaultMutableTreeNode root4 = new DefaultMutableTreeNode("root4");
        root2.add(root4);
        treeModel.setRoot(root);
        treeModel.setRoot(root4.getParent());
        //############## test

        JTree fileTree = new JTree(treeModel);

        //searchTextField.addActionListener();
        //fileTree.addTreeSelectionListener();

        add(searchTextField, BorderLayout.NORTH);
        add(fileTree, BorderLayout.CENTER);
    }

    @Override
    public void setFileTreeViewListener(EventListener listener) {
        this.fileTreeListener = listener;
    }

    @Override
    public String getSearchingField() {
        return searchTextField.getText();
    }

    @Override
    public void showSearchingList(JList<String> list) {

    }

    @Override
    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel.setRoot((DefaultMutableTreeNode) treeModel.getRoot());
        this.treeModel.reload();
    }

}
