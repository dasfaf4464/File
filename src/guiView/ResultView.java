package guiView;

import javax.swing.*;
import java.awt.*;

public class ResultView extends JPanel {
    public ResultView() {
        setLayout(new FlowLayout());
        this.add(new JLabel("Result:"));
    }
}
