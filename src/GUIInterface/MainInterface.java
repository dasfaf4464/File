package GUIInterface;

import guiPresenter.MainPresenter;

public interface MainInterface {

    interface MainViewInterface {

        //뷰에 프레젠터 연결
        void setPresenter(MainPresenter presenter);

        //메인뷰. 텍스트에디터. textArea(string)
        void showTextFile(String text);

        //메인뷰. 텍스트에디터. textArea("") && 메인뷰. 결과. textArea("")
        void clearText();

        //메인뷰. 텍스트에디터 카드 레이아웃
        //void swapText(String text);

        //메인뷰. 결과창. textArea(string)
        void showResult(String result);
    }

    interface MainPresenterInterface {
        //텍스트 파일 저장 -> 파일뷰 업데이트
        void saveButtonClicked();

        //텍스트 파일 있으면 읽고 없으면 result에 에러출력
        void openButtonClicked();

        //전부 날리기 , 업데이트 해야하는게 texteditor, result, open&save textfield
        void clearButtonClicked();

        //내용을 임시파일로 만들고 임시파일을 컴파일-> 컴파일된 class 파일 실행 -> result view update
        void compileButtonClicked();

        //오픈된 파일 삭제->삭제 메세지: result
        void deleteButtonClicked();
        //오류내용 파일에 저장
        void errorSaveButtonClicked();
    }

}