package guiPresenter;

import GUIInterface.MainInterface;
import guiView.frameAndPanel.MainPanel;

public class MainPresenter implements MainInterface.MainPresenterInterface {
    public MainPresenter(MainPanel mainLinkPanel) {
        mainPanel = mainLinkPanel;
    }

    /**
     * 오픈시 파일에서 내용 가져오고 메인.텍스트.텍스트에리어에 업데이트
     */
    @Override
    public void openButtonClicked() {

    }

    /**
     * 메인.텍스트.텍스트 에리어 내용 초기화
     */
    @Override
    public void clearButtonClicked() {

    }

    /**
     * 메인.텍스트.텍스트 에리어의 내용 임시저장(저장안하고 종료되면 삭제)
     * 임시 저장된 파일 확장자 바꿔서 저장하고 컴파일, 컴파일 하고 내용 결과패널.텍스트 에리어에 출력
     * 결과창 닫혀있으면 결과창 위로하고 버튼 아래방향으로
     */
    @Override
    public void compileButtonClicked() {

    }

    /**
     * 
     */
    @Override
    public void deleteButtonClicked() {

    }

    /**
     * 파일 내용을 위치에 저장
     */
    @Override
    public void saveButtonClicked() {

    }

    @Override
    public void errorSaveButtonClicked() {

    }

    private MainPanel mainPanel;
    private guiModel.File fileModel = new guiModel.File();
}