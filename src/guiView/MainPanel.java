package guiView;

import GUIInterface.MainInterface;
import guiPresenter.MainPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel implements MainInterface.MainViewInterface{
    private MainPresenter mainPresenter;

    private JSplitPane splitPane; // TextEditorView와 ResultView를 분할하는 SplitPane

    private TextEditorView textEditorView;
    private ResultView resultView;

    private JPanel buttonPanel;
    private ArrayList<JButton> buttonArrayList;
    private JButton openButton, saveButton, compileButton, deleteButton, clearButton;
    private ActionListener mainListener;

    public MainPanel() {
        mainPresenter = new MainPresenter(this);
        setLayout(new BorderLayout());

        // 파일 작업 버튼 패널 생성 및 설정
        buttonPanel = new JPanel(new FlowLayout());
        mainListener = new ButtonPanelEventListener();

        buttonArrayList = new ArrayList<>();
        buttonArrayList.add(new JButton("Open"));
        buttonArrayList.add(new JButton("Save"));
        buttonArrayList.add(new JButton("Compile"));
        buttonArrayList.add(new JButton("Delete"));
        buttonArrayList.add(new JButton("Clear"));

        /* eventListner arrayIndex로 이용
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        compileButton = new JButton("Compile");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
         */

        for(JButton button : buttonArrayList){
            buttonPanel.add(button);
            button.addActionListener(mainListener);
        }

        /* for each 로 변경
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(compileButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        */

        // TextEditorView와 ResultView 초기화 및 추가
        textEditorView = new TextEditorView();
        resultView = new ResultView();

        // JSplitPane을 사용하여 TextEditorView와 ResultView를 분할
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textEditorView, resultView);
        splitPane.setDividerLocation(400); // 초기 분할 위치 설정
        splitPane.setResizeWeight(1.0); // 위쪽 영역이 전체를 차지하게 설정
        splitPane.setOneTouchExpandable(true); // 확장/축소 버튼 활성화

        // 패널 구성
        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void showText(String text) {
        this.textEditorView.setText(text);
    }

    @Override
    public void clearText() {
        this.textEditorView.setText("");
    }

    @Override
    public void showResult(String result) {
        this.resultView.setResultText(result);
    }

    // TextEditorView 이너 클래스
    public class TextEditorView extends JPanel {
        private JTextArea textArea;

        public TextEditorView() {
            setLayout(new BorderLayout());

            // JTextArea 초기화 및 설정
            textArea = new JTextArea();
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);

            // JScrollPane을 사용하여 스크롤 가능하게 설정
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            add(scrollPane, BorderLayout.CENTER);
        }

        // 텍스트 가져오기 및 설정
        public String getText() {
            return textArea.getText();
        }

        public void setText(String text) {
            textArea.setText(text);
        }

        // JTextArea 접근자
        public JTextArea getTextArea() {
            return textArea;
        }
    }

    // ResultView 이너 클래스
    public class ResultView extends JPanel {
        private JTextArea resultArea;
        private JScrollPane scrollPane;

        public ResultView() {
            setLayout(new BorderLayout());

            // 결과 출력 영역
            resultArea = new JTextArea();
            resultArea.setEditable(false);
            resultArea.setLineWrap(false);
            resultArea.setWrapStyleWord(true);

            // 스크롤 가능하도록 설정
            scrollPane = new JScrollPane(resultArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            add(scrollPane, BorderLayout.CENTER);
        }

        // 결과 텍스트 설정 및 가져오기
        public void setResultText(String text) {
            resultArea.setText(text);
        }

        public String getResultText() {
            return resultArea.getText();
        }
    }

    //event listener inner class
    public class ButtonPanelEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            switch (button.getText()) {
                case "Open": mainPresenter.openButtonClicked(); break;
                case "Save": mainPresenter.saveButtonClicked(); break;
                case "Compile": mainPresenter.compileButtonClicked(); break;
                case "Delete": mainPresenter.deleteButtonClicked(); break;
                case "Clear": mainPresenter.clearButtonClicked(); break;
                default: break;
            }
        }
    }

    /*이너 클래스 사용으로 필요없어짐
    // Button getter methods for adding action listeners in Presenter
    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCompileButton() {
        return compileButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }
    */
    
    // Getters for text editor and result view
    public TextEditorView getTextEditorView() {
        return textEditorView;
    }

    public ResultView getResultView() {
        return resultView;
    }



}
