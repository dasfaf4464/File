package guiView.framePanel;

import GUIInterface.TextEditorInterface;
import guiPresenter.framePresenter.TextEditorPresenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public class TextEditorView extends JPanel implements TextEditorInterface.View {
    private final JTabbedPane tabbedPane;
    private final ArrayList<JTextArea> editorTextAreas;
    private Font font;
    private Color color;

    private EventListener textEditorViewListener;

    public TextEditorView() {
        new TextEditorPresenter(this);

        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        editorTextAreas = new ArrayList<>();

        font = new Font("Arial", Font.PLAIN, 12);
        color = Color.white;

        tabbedPane.setBackground(Color.cyan);
        tabbedPane.setFocusable(true);

        for(JTextArea textArea : editorTextAreas) {
            textArea.setFont(font);
            textArea.setBackground(color);
            textArea.setEditable(true);
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            tabbedPane.addTab(textArea.getName(), scrollPane);
        }

        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void setTextEditorViewListener(EventListener textEditorViewListener) {
        this.textEditorViewListener = this.textEditorViewListener;
    }

    @Override
    public String getEditorTextArea() {
        return editorTextAreas.get(tabbedPane.getSelectedIndex()).getText();
    }

    @Override
    public void setEditorTextArea(String editorTextArea) {
        editorTextAreas.get(tabbedPane.getSelectedIndex()).setText(editorTextArea);
    }

    @Override
    public boolean isFocused() {
        return tabbedPane.hasFocus();
    }

    @Override
    public ArrayList<JTextArea> getTabbedList() {
        return editorTextAreas;
    }

    @Override
    public void setTabbedList(ArrayList<JTextArea> tabList) {
        editorTextAreas.clear();
        editorTextAreas.addAll(tabList);
    }

    @Override
    public String activatedFileName() {
        return editorTextAreas.get(tabbedPane.getSelectedIndex()).getName();
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
