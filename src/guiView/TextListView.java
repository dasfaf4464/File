package guiView;

import javax.swing.*;
import java.awt.*;

public class TextListView extends JPanel {
    private JList<String> textList;

    public TextListView() {
        setLayout(new BorderLayout());

        // 텍스트 리스트 구성
        textList = new JList<>(new String[]{"자바1.java [x]", "자바2.java [x]"});
        JScrollPane scrollPane = new JScrollPane(textList);

        add(scrollPane, BorderLayout.CENTER);
    }

    // 텍스트 리스트에 접근하기 위한 Getter
    public JList<String> getTextList() {
        return textList;
    }
}
