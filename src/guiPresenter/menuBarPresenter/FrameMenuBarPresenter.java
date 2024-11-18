package guiPresenter.menuBarPresenter;

import guiView.MenubarPanel.FrameMenubar;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FrameMenuBarPresenter {
    private FrameMenubar frameMenubar;

    public FrameMenuBarPresenter(FrameMenubar frameMenubar) {
        this.frameMenubar = frameMenubar;
        frameMenubar.setMenuBarListener(new FrameMenuListener(), new FrameMenuItemListener());
    }

    /**
     * 다이얼로그 세팅 패널
     */
    public void settingMenuClicked() {
        System.out.println("settingMenuClicked");

    }

    /**
     * 다이얼로그 헬프 패널 호출
     */
    public void helpMenuClicked() {
        System.out.println("helpMenuClicked");
    }

    /**
     * 파일 값 메인 프레임, 오른쪽 패널, 텍스트 에디터로
     * 프로젝트 lastedit에 값으로 추가
     */
    public void openItemClicked() {
        frameMenubar.showJFileChooser();
        File file = frameMenubar.getChooseFile();
    }

    public void closeItemClicked() {
        System.out.println("closeItemClicked");
    }

    /**
     * 프로젝트 파일이면 로그에 추가
     */
    public void saveItemClicked() {
        System.out.println("saveItemClicked");
    }

    /**
     * 다이얼로그 저장 패널 호출?
     */
    public void saveAsItemClicked() {
        System.out.println("saveAsItemClicked");
    }

    /**
     * 뭘 하라는지 모르겠음
     */
    public void quitItemClicked() {
        System.out.println("quitItemClicked");
    }

    /**
     * 단독파일이 포커스 열려 있으면 단독파일만 컴파일
     * 프로젝트 파일이 포커스면 프로젝트 소스리스트 업데이트 후 컴파일
     */
    public void compileItemClicked() {

    }

    /**
     * 단독파일이면 컴파일된 대상 추적
     * 프로젝트면 프로젝트 프로퍼티에서 메인 클래스 수정
     */
    public void compileAndRunItemClicked() {
        
    }

    /**
     * 최근 실행한 파일이 프로젝트인지 단독파일인지 구분 후 실행.
     */
    public void runItemClicked() {

    }

    public class FrameMenuListener extends MouseAdapter implements MouseInputListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            JMenu me = (JMenu) e.getSource();
            switch (me.getText()) {
                case "Settings": System.out.println("settingMenuClicked"); break;
                case "Help": System.out.println("helpMenuClicked"); break;
            }
        }
    }

    public class FrameMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem) e.getSource();
            System.out.println("아이템 클릭");
            switch (menuItem.getText()) {
                case "Open": openItemClicked(); break;
                case "Close": closeItemClicked(); break;
                case "Save": saveItemClicked(); break;
                case "Save as": saveAsItemClicked(); break;
                case "Quit": quitItemClicked(); break;

                case "Compile": compileItemClicked(); break;
                case "CompileAndRun": compileAndRunItemClicked(); break;
                case "Run": runItemClicked(); break;
            }
        }
    }
}
