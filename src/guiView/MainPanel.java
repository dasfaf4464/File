package guiView;

import GUIInterface.MainInterface;
import guiPresenter.MainPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel implements MainInterface.MainViewInterface {

    private TextEditorView textEditorView;//텍스트 에디터
    private ResultView resultView;//결과
    private ActionListener mainListener;

    /**
     * 메인 패널에 포함될 패널들(텍스트 에디터 + 메인 버튼, 결과 화면, 오픈&세이브) 생성 및 초기화
     * 버튼은 메인 이벤트 리스너에 연결
     */
    public MainPanel() {
        new MainPresenter(this);

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());
        textEditorView = new TextEditorView();
        resultView = new ResultView();

        centerPanel.add(textEditorView, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, centerPanel, resultView);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.7);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(8);

        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void showTextEditor(String text) {
        textEditorView.activatedTextArea.setText(text);
    }

    @Override
    public void showResult(String result) {
        resultView.resultArea.setText(result);
    }

    @Override
    public String getTextEditor() {
        return textEditorView.activatedTextArea.getText();
    }

    @Override
    public String getOpenTextField() {
        return null;
    }

    @Override
    public String getSaveTextField() {
        return null;
    }

    @Override
    public void showOpenTextField(String text) {

    }

    @Override
    public void showSaveTextField(String text) {

    }


    /**
     * 텍스트 에디터로 구성된 패널
     */
    public static class TextEditorView extends JPanel {
        private JTabbedPane tabbedPane;
        private ArrayList<JTextArea> textAreas;
        private JTextArea activatedTextArea;

        public TextEditorView() {
            setLayout(new BorderLayout());

            tabbedPane = new JTabbedPane();
            textAreas = new ArrayList<>();
            activatedTextArea = new JTextArea();

            tabbedPane.setTabPlacement(JTabbedPane.TOP);

            activatedTextArea = new JTextArea();

            ArrayList<JScrollPane> scrollPanes = new ArrayList<>();

            for(JTextArea textArea : textAreas) {
                textArea.setLineWrap(false);
                textArea.setWrapStyleWord(false);
                scrollPanes.add(new JScrollPane(textArea));

            }

            for(JScrollPane scrollPane : scrollPanes) {
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                tabbedPane.add("title", scrollPane);
            }

            add(tabbedPane, BorderLayout.CENTER);
            tabbedPane.setFocusable(true);
        }

    }

    /**
     * 결과 화면으로 구성된 패널
     */
    public static class ResultView extends JPanel {
        private JTextArea resultArea;

        public ResultView() {
            setLayout(new BorderLayout());

            resultArea = new JTextArea();
            resultArea.setEditable(false);
            resultArea.setLineWrap(false);
            resultArea.setWrapStyleWord(false);

            JScrollPane scrollPane = new JScrollPane(resultArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            add(scrollPane, BorderLayout.CENTER);
        }

    }

    public void setEventListener(ActionListener mainListener) {
        this.mainListener = mainListener;
    }

}
