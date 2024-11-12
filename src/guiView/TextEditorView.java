package guiView;

import guiPresenter.TextEditorPresenter;
import javax.swing.*;
import java.awt.*;

/**
 * @author 조해천
 */
public class TextEditorView extends JPanel {
    public TextEditorView() {
        setLayout(new GridLayout(1,1));

        textArea = new JTextArea("asdasdasdasdasdasdasdasd", 20, 50);
        textArea.setEditable(true);
        textArea.setLineWrap(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textScrollPane = new JScrollPane(textArea);
        add(textScrollPane);
    }

    private final TextEditorPresenter presenter = new TextEditorPresenter();
    private final JScrollPane textScrollPane;
    private final JTextArea textArea;
}