package GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.util.EventListener;

public interface HistoryInterface {
    interface View {
        void setHistoryViewListener(EventListener textEditorViewListener);

        String getProjectNameField();
        void setProjectNameField(String projectName);
        void setHistoryModel(DefaultListModel<String> historyModel);

        void setProjectColor(Color color);
        void setHistoryColor(Color color);
    }

    interface Presenter {

    }
}
