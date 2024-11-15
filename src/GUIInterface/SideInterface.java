package GUIInterface;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.io.File;

public interface SideInterface {

    interface SideViewInterface {
        void showFileTree();

        void fileSearch();

        void showTextList(DefaultListModel<File> fileListModel);
    }

    interface SidePresenterInterface {

        void treeNodeClicked(TreeNode node);

        void listElementClicked();

        void makingFileButtonClicked();

        void deleteFileButtonClicked();

        void settingButtonClicked();

        void fileSearchingFieldEntered();
    }
}
