package guiView;

import GUIInterface.MainInterface;
import guiPresenter.MainPresenter;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JTextField openPathField; // Open 파일 경로 입력 필드
    private JTextField savePathField; // Save 파일 경로 입력 필드
    private JButton openButton, saveButton, compileButton, saveErrorsButton, deleteButton, clearButton;
    private TextEditorView textEditorView;
    private ResultView resultView;
    private JSplitPane splitPane;

    private MainPresenter mainPresenter;

    public MainPanel() {
        setLayout(new BorderLayout());

        // 상단 패널 (파일 경로 입력 및 Open/Save 버튼)
        JPanel topPanel = new JPanel(new BorderLayout());

        // 텍스트 필드 패널
        JPanel filePathPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        openPathField = new JTextField("c:\\temp\\OpenFile.java");
        savePathField = new JTextField("c:\\temp\\SaveFile.java");
        filePathPanel.add(openPathField);
        filePathPanel.add(savePathField);

        // 버튼 패널 (Open/Save 버튼)
        JPanel fileButtonPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 두 개의 버튼 세로 배치
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        Dimension buttonSize = new Dimension(100, 30); // 버튼 크기 설정
        openButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
        fileButtonPanel.add(openButton);
        fileButtonPanel.add(saveButton);

        // 상단 패널 구성
        topPanel.add(filePathPanel, BorderLayout.CENTER);
        topPanel.add(fileButtonPanel, BorderLayout.EAST);

        // 중간 버튼 패널 (Compile, Save Errors, Delete, Clear 버튼)
        JPanel midButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        compileButton = new JButton("Compile");
        saveErrorsButton = new JButton("Save Errors");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        midButtonPanel.add(compileButton);
        midButtonPanel.add(saveErrorsButton);
        midButtonPanel.add(deleteButton);
        midButtonPanel.add(clearButton);

        // 텍스트 편집기와 결과 창을 포함한 중앙 패널 구성
        textEditorView = new TextEditorView();
        resultView = new ResultView();

        // 텍스트 편집기, 버튼 패널, 결과 창을 중간에 배치
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(textEditorView, BorderLayout.CENTER);
        centerPanel.add(midButtonPanel, BorderLayout.SOUTH);

        // 전체 레이아웃 구성
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, centerPanel, resultView);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.7);
        splitPane.setOneTouchExpandable(true); // 확장/축소 버튼 활성화

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    // Getters for accessing components
    public JTextField getOpenPathField() {
        return openPathField;
    }

    public JTextField getSavePathField() {
        return savePathField;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCompileButton() {
        return compileButton;
    }

    public JButton getSaveErrorsButton() {
        return saveErrorsButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    // TextEditorView 이너 클래스
    public class TextEditorView extends JPanel {
        private JTextArea textArea;

        public TextEditorView() {
            setLayout(new BorderLayout());
            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
        }

        public String getText() {
            return textArea.getText();
        }

        public void setText(String text) {
            textArea.setText(text);
        }
    }

    // ResultView 이너 클래스
    public class ResultView extends JPanel {
        private JTextArea resultArea;

        public ResultView() {
            setLayout(new BorderLayout());
            resultArea = new JTextArea();
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane, BorderLayout.CENTER);
        }

        public void setResultText(String text) {
            resultArea.setText(text);
        }

        public String getResultText() {
            return resultArea.getText();
        }
    }
}
