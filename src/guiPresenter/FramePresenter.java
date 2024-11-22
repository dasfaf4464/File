package guiPresenter;

import guiModel.Installer;
import guiModel.PropertiesUtil;
import guiView.Dialog;
import guiView.Frame;

import java.io.File;
import java.util.Properties;

/**
 * 설치되었는지 확인, 다이얼로그와 프레임 관리
 */

public class FramePresenter {
    private final Frame frame;
    private final Dialog dialog;
    private Properties ideProperties = new Properties();

    public FramePresenter() {
        frame = new Frame();
        dialog = new Dialog(frame);
        PropertiesUtil.loadProperties(ideProperties, new File(Installer.idePropFolder+Installer.idePropName));

        if(Installer.isInstalled()) {
            frame.setSize(Integer.parseInt(ideProperties.getProperty("width")), Integer.parseInt(ideProperties.getProperty("height")));
            frame.setVisible(true);
            dialog.setVisible(false);
        } else {
            frame.setVisible(false);
            dialog.setDialog(dialog.getInstallingPanel());
            dialog.setVisible(true);
        }
        frame.setFrameVisible(true);
    }



}
