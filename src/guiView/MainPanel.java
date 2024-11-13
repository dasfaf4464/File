package guiView;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private TextEditorView textEditorView;
    private ResultView resultView;
    private JPanel buttonPanel;
    private JButton openButton, saveButton, compileButton, deleteButton, clearButton;

    private boolean isResultVisible = true; // ResultView 가시성 상태
    private JSplitPane splitPane; // TextEditorView와 ResultView를 분할하는 SplitPane

    public MainPanel() {
        setLayout(new BorderLayout());

        // 파일 작업 버튼 패널
        buttonPanel = new JPanel(new FlowLayout());
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        compileButton = new JButton("Compile");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(compileButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // TextEditorView와 ResultView 초기화
        textEditorView = new TextEditorView();
        resultView = new ResultView();

        // ResultView 토글 버튼에 대한 리스너 설정
        resultView.getToggleButton().addActionListener(e -> toggleResultView());

        // JSplitPane을 사용하여 TextEditorView와 ResultView를 분할
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textEditorView, resultView);
        splitPane.setDividerLocation(400); // 초기 분할 위치 설정
        splitPane.setResizeWeight(1.0); // 위쪽 영역이 전체를 차지하게 설정

        // 패널 구성
        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    // ResultView의 가시성을 토글하는 메서드
    private void toggleResultView() {
        isResultVisible = !isResultVisible; // 상태를 반전시킴
        if (isResultVisible) {
            splitPane.setBottomComponent(resultView); // ResultView 표시
            resultView.getToggleButton().setText("▼"); // 버튼 텍스트를 ▼로 변경
        } else {
            splitPane.setBottomComponent(null); // ResultView를 제거하여 숨김
            resultView.getToggleButton().setText("▲"); // 버튼 텍스트를 ▲로 변경
        }
        revalidate();
        repaint();
    }

    // Button getter methods for adding action listeners in Presenter
    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCompileButton() {
        return compileButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    // Getters for text editor and result view
    public TextEditorView getTextEditorView() {
        return textEditorView;
    }

    public ResultView getResultView() {
        return resultView;
    }
}
