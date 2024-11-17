package guiPresenter.framePresenter;

import GUIInterface.TextEditorInterface;
import guiView.framePanel.TextEditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditorPresenter implements TextEditorInterface.Presenter {
    private final TextEditorView textEditorView;

    public TextEditorPresenter(TextEditorView textEditorView) {
        this.textEditorView = textEditorView;
        TextEditorViewListener textEditorViewListener = new TextEditorViewListener();
        this.textEditorView.setTextEditorViewListener(textEditorViewListener);
    }

    public class TextEditorViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
