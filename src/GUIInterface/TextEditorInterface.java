package GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public interface TextEditorInterface {
    interface View {
        void setTextEditorViewListener(EventListener resultViewListener);

        String getEditorTextArea();
        void setEditorTextArea(String editorTextArea);

        ArrayList<JTextArea> getTabbedList();
        void setTabbedList(ArrayList<JTextArea> tabList);

        String activatedFileName();
        boolean isFocused();

        void setFont(Font font);
        void setColor(Color color);

    }

    interface Presenter {

    }
}
