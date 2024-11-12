package GUI;

import guiView.MainDialog;
import guiView.MainFrame;

/**
 * 프로그램 진입점
 * 메인 프레임 생성,
 * */
public class GUImain{

    public static void main(String[] args) {
        MainFrame window = new MainFrame();
        MainDialog dialog = new MainDialog();
    }
}