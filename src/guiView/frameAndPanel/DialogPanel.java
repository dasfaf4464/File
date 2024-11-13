package guiView.frameAndPanel;

import javax.swing.*;
import java.awt.*;

public class DialogPanel extends JPanel {
    private JButton dialogButton;
    private JFrame parentFrame;

    public DialogPanel(JFrame parent) {
        this.parentFrame = parent;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        dialogButton = new JButton("Dialog Button");
        add(dialogButton);

        // 다이얼로그 버튼 클릭 시 MainDialog 표시
        dialogButton.addActionListener(e -> {
            MainDialog dialog = new MainDialog(parentFrame);
            dialog.setVisible(true);
        });
    }
}
