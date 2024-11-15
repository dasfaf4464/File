package guiPresenter;

import GUIInterface.MainInterface;
import guiModel.Compiler;
import guiModel.Error;
import guiModel.FileUtil;
import guiModel.SettingManager;
import guiView.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainPresenter implements MainInterface.MainPresenterInterface {
    public MainPresenter(MainPanel mainLinkPanel) {
        mainPanel = mainLinkPanel;
        MainButtonListener mainButtonListener = new MainButtonListener();
        mainPanel.setEventListener(mainButtonListener);
    }

    /**
     * 오픈시 파일에서 내용 가져오고 메인.텍스트.텍스트에리어에 업데이트
     */
    @Override
    public void openButtonClicked() {
        File file = new File(mainPanel.getOpenTextField());
        
        if(!file.exists() || file.isDirectory()) {
           //오류 출력
        } else {
            FileUtil fileUtil = new FileUtil();
            fileUtil.openFile(file);
            mainPanel.showTextEditor(fileUtil.getFileContent());
        }
    }

    /**
     * 메인.텍스트.텍스트 에리어 내용 초기화, 내용끝
     */
    @Override
    public void clearButtonClicked() {
        mainPanel.showOpenTextField("");
        mainPanel.showSaveTextField("");
        mainPanel.showTextEditor("");
        mainPanel.showResult("");
    }

    /**
     * 파일 내용을 위치에 저장
     */
    @Override
    public void saveButtonClicked() {
        FileUtil fileUtil = new FileUtil();

        if(mainPanel.getSaveTextField().isBlank() && mainPanel.getOpenTextField().isBlank()) {
            //오류 출력
        } else if(mainPanel.getSaveTextField().isBlank()){
            //Valid 검사 필요
            fileUtil.saveContent(mainPanel.getTextEditor(), new File(mainPanel.getOpenTextField()));
        } else {
            //Valid 검사 필요
            fileUtil.saveContent(mainPanel.getTextEditor(), new File(mainPanel.getSaveTextField()));
        }
    }

    /**
     * 메인.텍스트.텍스트 에리어의 내용 임시저장(저장안하고 종료되면 삭제)
     * 임시 저장된 파일 확장자 바꿔서 저장하고 컴파일, 컴파일 하고 내용 결과패널.텍스트 에리어에 출력
     * 결과창 닫혀있으면 결과창 위로하고 버튼 아래방향으로
     */
    @Override
    public void compileButtonClicked() {

        Compiler compiler = new Compiler(mainPanel.getOpenTextField(), System.getProperty("java.home") + "\\bin\\javac.exe", System.getProperty("java.home") + "\\bin\\java.exe");
        compiler.compile();

        /*
        if(컴파일 실패)
                then
                컴파일러에서 에러파일 반환
                File = compiler.getErrorFile()
                에러 클래스에서 에러 파일 내용 가져오고 에러파일 내용 반환
                Error(File)
                String = Error.getErrorContent() : 에러파일 지우고 내용을 스트링으로 반환
         else(컴파일 성공)
                then
                컴파일러에서 실행 파일 반환
                File = compiler.getClassFile() [File :  컴파일러에서 컴파일러 버전]
                Run(File) [Run : 가상머신 버전]
                "컴파일 성공\n" + String = Run.start();
                컴파일된 클래스 파일을 런에서 실행하고 런에서 결과내용 반환
                
          finally
                내용을 메인패널의 결과화면에 전달
                mainPanel.showResult(string)
         */

    }

    /**
     * 
     */
    @Override
    public void deleteButtonClicked() {
        FileUtil fileUtil = new FileUtil();

    }

    @Override
    public void errorSaveButtonClicked() {

    }

    public class MainButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            switch (button.getText()) {
                case "Open": openButtonClicked(); break;
                case "Save": saveButtonClicked(); break;
                case "Compile": compileButtonClicked(); break;
                case "Delete": deleteButtonClicked(); break;
                case "Clear": clearButtonClicked(); break;
                case "SaveError": errorSaveButtonClicked(); break;
                default: break;
            }
        }
    }

    private MainPanel mainPanel;
}