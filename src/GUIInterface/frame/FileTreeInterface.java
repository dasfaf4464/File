package GUIInterface.frame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.EventListener;

public interface FileTreeInterface {

    interface View {
        void setFileTreeViewListener(EventListener listener);

        String getSearchingField();
        void showSearchingList(JList<String> list);

        void setTreeModel(DefaultTreeModel treeModel);
    }

    interface Presenter {

    }
}