package guiView;

import javax.swing.*;
import java.awt.*;

public class MainDialog extends JDialog {

    public MainDialog(JFrame parent) {
        super(parent, "Main Dialog", true); // true는 모달 다이얼로그로 설정하는 옵션입니다
        setSize(300, 150);
        setLocationRelativeTo(parent);

        // 라벨 추가
        JLabel label = new JLabel("Dialog Content Here", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // 닫기 버튼 추가
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose()); // 다이얼로그 창 닫기

        // 닫기 버튼을 하단에 배치
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
