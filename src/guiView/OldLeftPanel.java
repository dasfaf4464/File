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

public class OldLeftPanel extends JPanel implements SideInterface.SideViewInterface {

    private final JPanel sideButtonPanel;//버튼 세 개
    private final FileView fileView;//파일 트리 뷰
    private final TextListView textListView;//액티브 파일 리스트

    private TreeSelectionListener fileTreeListener;
    private ListSelectionListener textListListener;
    private ActionListener sideButtonListener;

    public OldLeftPanel() {
        new SidePresenter(this);

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
    public void showFileTree() {

    }

    @Override
    public void fileSearch() {

    }

    @Override
    public void showTextList(DefaultListModel<File> fileListModel) {
        textListView.fileList.setModel(fileListModel);
    }

    @Override
    public void showFocusedTextField(String text) {
        textListView.focusedFile.setText(text);
    }

    @Override
    public void showProjectNameTextField(String text) {
        textListView.projectNameField.setText(text);
    }

    public static class FileView extends JPanel {
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
        private final JTextField projectNameField;
        private final JTextField focusedFile;
        private JList<File> fileList;

        public TextListView() {
            setLayout(new BorderLayout());

            focusedFile = new JTextField();
            focusedFile.setEditable(false);
            projectNameField = new JTextField();
            projectNameField.setEditable(false);

            JPanel textFieldPanel = new JPanel();
            textFieldPanel.setLayout(new GridLayout(2, 1));
            textFieldPanel.add(projectNameField);
            textFieldPanel.add(focusedFile);
            add(textFieldPanel, BorderLayout.NORTH);

            fileList = new JList<>();
            fileList.addListSelectionListener(textListListener);
            fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(fileList);
            add(scrollPane, BorderLayout.CENTER);
        }

    }

    public void setEventListner(TreeSelectionListener fileTreeListener, ListSelectionListener textListListener, ActionListener buttonListener) {
        this.fileTreeListener = fileTreeListener;
        this.textListListener = textListListener;
        this.sideButtonListener = buttonListener;
    }
}
