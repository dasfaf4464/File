package GUIInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.EventListener;

public interface HistoryInterface {
    interface View {
        void setHistoryViewListener(EventListener textEditorViewListener);

        String getProjectNameField();
        void setProjectNameField(String projectName);
        void setHistoryModel(ArrayList<String> historyModel);

        void setProjectColor(Color color);
        void setHistoryColor(Color color);
    }

    interface Presenter {

    }
}
