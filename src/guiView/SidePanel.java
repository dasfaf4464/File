package guiView;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private FileView fileView;
    private TextListView textListView;

    public SidePanel() {
        setLayout(new BorderLayout());

        // FileView와 TextListView 초기화
        fileView = new FileView();
        textListView = new TextListView();

        // JSplitPane으로 FileView와 TextListView를 분할
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fileView, textListView);
        splitPane.setDividerLocation(300); // 초기 분할 위치 설정
        splitPane.setContinuousLayout(true); // 크기 조절 중 실시간 업데이트
        splitPane.setOneTouchExpandable(true); // 분할 조절 버튼 표시
        splitPane.setDividerSize(8); // 분할선 두께 설정

        add(splitPane, BorderLayout.CENTER);
    }

    // FileView와 TextListView에 접근하기 위한 Getters
    public FileView getFileView() {
        return fileView;
    }

    public TextListView getTextListView() {
        return textListView;
    }
}
