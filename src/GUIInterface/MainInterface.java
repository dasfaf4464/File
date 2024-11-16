package GUIInterface;

public  interface MainInterface {

    interface MainViewInterface {

        void showTextEditor(String text);

        String getTextEditor();

        void showResult(String result);

        String getOpenTextField();

        String getSaveTextField();

        void showOpenTextField(String text);

        void showSaveTextField(String text);
    }

    interface MainPresenterInterface {

        void saveButtonClicked();

        void openButtonClicked();

        void clearButtonClicked();

        void compileButtonClicked();

        void deleteButtonClicked();

        void errorSaveButtonClicked();
    }

}