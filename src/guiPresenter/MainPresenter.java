package guiPresenter;

import GUIInterface.MainInterface;
import guiModel.Compiler;
import guiModel.FileUtil;
import guiModel.Run;
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
           mainPanel.showResult("파일이 존재하지 않음");
        } else {
            FileUtil fileUtil = new FileUtil();
            fileUtil.openFile(file);
            mainPanel.showResult("");
            mainPanel.showTextEditor(fileUtil.getActivatedFileContent());
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
            mainPanel.showResult("저장될 주소를 입력해야합니다.");
        } else if(mainPanel.getSaveTextField().isBlank()){
            mainPanel.showResult("내용을 " + mainPanel.getOpenTextField() + " 에 저장합니다.");
            fileUtil.saveContent(mainPanel.getTextEditor(), new File(mainPanel.getOpenTextField()));
        } else {
            if(new File(mainPanel.getSaveTextField()).exists()) {
               mainPanel.showResult("동일한 이름의 파일이 존재합니다. Open버튼을 눌러 해당 파일을 편집하세요");
               mainPanel.showOpenTextField(mainPanel.getSaveTextField());
            } else {
                mainPanel.showResult("파일이 성공적으로 저장되었습니다.");
                mainPanel.showOpenTextField(mainPanel.getSaveTextField());
                new FileUtil().openFile(new File(mainPanel.getOpenTextField()));
                fileUtil.saveContent(mainPanel.getTextEditor(), new File(mainPanel.getSaveTextField()));
            }
        }
    }

    /**
     * 메인.텍스트.텍스트 에리어의 내용 임시저장(저장안하고 종료되면 삭제)
     * 임시 저장된 파일 확장자 바꿔서 저장하고 컴파일, 컴파일 하고 내용 결과패널.텍스트 에리어에 출력
     * 결과창 닫혀있으면 결과창 위로하고 버튼 아래방향으로
     */
    @Override
    public void compileButtonClicked() {

        Compiler compiler = new Compiler(FileUtil.getActivatedFile(), System.getProperty("java.home")+"\\bin\\javac.exe");//이거 설정에서 컴파일러 위치 가져오는거로 변경 필요
        if(compiler.compile(System.getProperty("user.home")+"\\Downloads")){//설정에서 저장파일 필요

            mainPanel.showResult(compiler.getMassage());
        } else{
            mainPanel.showResult(compiler.getLastErrorContent());
        }

    }

    @Override
    public void deleteButtonClicked() {
        File file = FileUtil.getActivatedFile();

        if(!file.exists()) {
            mainPanel.showResult("삭제하려는 파일이 존재하지 않습니다.");
        }
        FileUtil.deleteActivatedFile();
        mainPanel.showResult("성공적으로 삭제했습니다.");
        mainPanel.showOpenTextField("");
    }

    @Override
    public void errorSaveButtonClicked() {
        String errorContent = mainPanel.getResult();
        if(FileUtil.getActivatedFile().exists()){
            FileUtil.saveContent(errorContent, new File(System.getProperty("user.home") + "\\Downloads\\" + FileUtil.getActivatedFile().getName() + ".error"));
        } else {
            mainPanel.showResult("열린 파일이 없습니다. 먼저 파일을 열어주세요");
        }

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