import guiView.MainFrame;

public class ProjectEntryPoint {
    public static void main(String[] args) {

        /*
        if(!new SettingManager().isInstalled()){
            SwingUtilities.invokeLater(MainFrame :: new);
        }
        else {
            SwingUtilities.invokeLater(Dialog :: new);
        }

         */

        new MainFrame();
    }
}
