package guiView;

import javax.swing.*;
import java.awt.*;

public class ResultView extends JPanel {
    private JTextArea resultArea;
    private JButton toggleButton;
    private JScrollPane scrollPane;
    private boolean isResultVisible = true; // 결과 영역 가시성 상태

    public ResultView() {
        setLayout(new BorderLayout());

        // 상단 패널에 '숨기기' 버튼 추가
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toggleButton = new JButton("▼");  // ▼ 아이콘으로 숨기기 버튼
        toggleButton.addActionListener(e -> toggleResultVisibility()); // 버튼 클릭 시 가시성 토글
        headerPanel.add(toggleButton);
        add(headerPanel, BorderLayout.NORTH);

        // 결과 출력 영역
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(false); // 자동 줄바꿈 해제하여 가로 스크롤 가능
        resultArea.setWrapStyleWord(true);

        // JScrollPane을 사용하여 스크롤 가능하게 설정
        scrollPane = new JScrollPane(resultArea);

        // 가로 및 세로 스크롤바 항상 표시
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    // 결과 텍스트 설정
    public void setResultText(String text) {
        resultArea.setText(text);
    }

    // 결과 텍스트 가져오기
    public String getResultText() {
        return resultArea.getText();
    }

    // 숨기기/보이기 버튼 가져오기
    public JButton getToggleButton() {
        return toggleButton;
    }

    // 결과 영역 가시성을 토글하는 메서드
    private void toggleResultVisibility() {
        isResultVisible = !isResultVisible;
        scrollPane.setVisible(isResultVisible); // 스크롤 패널의 가시성 설정
        toggleButton.setText(isResultVisible ? "▼" : "▲"); // 버튼 텍스트 업데이트
        revalidate(); // 레이아웃 재조정
        repaint();
    }
}
