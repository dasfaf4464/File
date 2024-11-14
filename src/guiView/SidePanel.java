package guiView;

import GUIInterface.SideInterface;
import guiPresenter.SidePresenter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class SidePanel extends JPanel implements SideInterface.SideViewInterface {

    private FileView fileView;//파일 트리 뷰
    private TextListView textListView;//액티브 파일 리스트
    private JPanel sideButtonPanel;//버튼 세 개

    private TreeSelectionListener fileTreeListener;
    private ListSelectionListener textListListener;
    private ActionListener sideButtonListener;

    public SidePanel() {
        SidePresenter sidePresenter = new SidePresenter(this);

        setLayout(new BorderLayout());

        sideButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fileView = new FileView();
        textListView = new TextListView();

        //버튼 추가할 때 프레젠터에도 이벤트 추가
        ArrayList<JButton> buttonArrayList = new ArrayList<>();
        Dimension buttonSize = new Dimension(70, 30);
        buttonArrayList.add(new JButton("Setting"));
        buttonArrayList.add(new JButton("Make"));
        buttonArrayList.add(new JButton("Delete"));

        for(JButton button : buttonArrayList) {
            button.setPreferredSize(buttonSize);
            sideButtonPanel.add(button);
            button.addActionListener(sideButtonListener);
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileView, textListView);
        splitPane.setDividerLocation(300);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(8);

        add(sideButtonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void showFileTree() {//수정 필요

    }

    @Override
    public void fileSearch() {

    }

    @Override
    public void showTextList(String[] fileName) {

    }
    
    public class FileView extends JPanel {
        private JTextField searchField;
        private JTree fileTree;

        public FileView() {
            setLayout(new BorderLayout());
            
            searchField = new JTextField("파일 찾기");

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("File Folder");
            root.add(new DefaultMutableTreeNode("자바1.java"));
            root.add(new DefaultMutableTreeNode("자바2.java"));
            root.add(new DefaultMutableTreeNode("자바3.java"));
            root.add(new DefaultMutableTreeNode(new File(System.getProperty("user.dir")).getName()));
            fileTree = new JTree(root);

            JScrollPane fileTreeScrollPane = new JScrollPane(fileTree);
            fileTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            fileTreeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            add(searchField, BorderLayout.NORTH);
            add(fileTreeScrollPane, BorderLayout.CENTER);
        }

    }

    public class TextListView extends JPanel {
        private JList<String> textList;
        public TextListView() {
            setLayout(new BorderLayout());

            textList = new JList<>(new String[]{"자바1.java [x]", "자바2.java [x]"});
            JScrollPane scrollPane = new JScrollPane(textList);

            add(scrollPane, BorderLayout.CENTER);
        }

    }

    public void setEventListner(TreeSelectionListener fileTreeListener, ListSelectionListener textListListener, ActionListener buttonListener) {
        this.fileTreeListener = fileTreeListener;
        this.textListListener = textListListener;
        this.sideButtonListener = buttonListener;
    }
}
