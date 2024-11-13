package guiView.featureFrame;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class FileView extends JPanel {
    private JTextField searchField;
    private JTree fileTree;

    public FileView() {
        setLayout(new BorderLayout());

        // 파일 찾기 필드와 파일 기능 라벨
        JPanel filePanel = new JPanel(new GridLayout(2, 1));
        searchField = new JTextField("파일 찾기");
        JLabel fileFunctionLabel = new JLabel("파일 기능 (아이콘)");

        filePanel.add(fileFunctionLabel);
        filePanel.add(searchField);

        // 파일 트리 구성
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("File Folder");
        root.add(new DefaultMutableTreeNode("자바1.java"));
        root.add(new DefaultMutableTreeNode("자바2.java"));
        root.add(new DefaultMutableTreeNode("자바3.java"));
        fileTree = new JTree(root);

        // JScrollPane을 사용하여 fileTree에 스크롤 추가
        JScrollPane fileTreeScrollPane = new JScrollPane(fileTree);
        fileTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        fileTreeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // 구성 요소 추가
        add(filePanel, BorderLayout.NORTH);
        add(fileTreeScrollPane, BorderLayout.CENTER); // 스크롤 가능한 파일 트리 추가
    }

    // 파일 찾기 필드에 접근하기 위한 Getter
    public JTextField getSearchField() {
        return searchField;
    }

    // 파일 트리에 접근하기 위한 Getter
    public JTree getFileTree() {
        return fileTree;
    }
}
