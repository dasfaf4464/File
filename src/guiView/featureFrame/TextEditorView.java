package guiView.featureFrame;

import javax.swing.*;
import java.awt.*;

public class TextEditorView extends JPanel {
    private JTextArea textArea;

    public TextEditorView() {
        setLayout(new BorderLayout());

        // JTextArea 초기화
        textArea = new JTextArea();
        textArea.setLineWrap(true); // 자동 줄바꿈 설정 (필요에 따라 조정)
        textArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈
        
        //-> scrollpane으로 부탁쓰
        
        // JScrollPane을 사용하여 스크롤 가능하게 설정
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 가로 및 세로 스크롤 정책 설정
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 스크롤 패널을 중앙에 배치
        add(scrollPane, BorderLayout.CENTER);
    }

    // JTextArea에 입력된 텍스트 가져오기
    public String getText() {
        return textArea.getText();
    }

    // JTextArea에 텍스트 설정하기
    public void setText(String text) {
        textArea.setText(text);
    }

    // JTextArea 접근자 (필요 시 사용)
    public JTextArea getTextArea() {
        return textArea;
    }
}
