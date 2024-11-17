package GUIInterface;

import java.awt.*;
import java.awt.event.ActionListener;

public interface ResultInterface {
    interface View {
        void setResultViewListener(ActionListener resultViewListener);

        String getResultTextArea();
        void setResultTextArea(String resultTextArea);

        void setFont(Font font);
        void setColor(Color c);
    }

    interface  Presenter {

    }
}
