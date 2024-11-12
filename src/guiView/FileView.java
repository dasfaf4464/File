package guiView;

import guiPresenter.FilePresenter;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 조해천
 */
public class FileView extends JPanel {
    public FileView() {
        setLayout(new FlowLayout());
        //fileTree = new JTree();
    }

    JTree fileTree;
    FilePresenter presenter;
}
