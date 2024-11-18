import guiPresenter.FramePresenter;
import guiView.Frame;

import javax.swing.*;

public class ProjectEntryPoint {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
        new FramePresenter();
    }
}
