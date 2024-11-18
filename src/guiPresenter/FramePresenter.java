package guiPresenter;

import guiModel.Installer;
import guiView.Dialog;
import guiView.Frame;

/**
 * 설치되었는지 확인, 다이얼로그와 프레임 관리
 */

public class FramePresenter {
    private final Frame frame;
    private final Dialog dialog;

    public FramePresenter() {
        frame = new Frame(1280, 720);
        dialog = new Dialog(frame);

        if(Installer.isInstalled()) {
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
