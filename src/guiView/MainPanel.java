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
    private FileTextView fileTextView;//오픈&세이브
    private JPanel mainButtonPanel;//버튼 네 개
    private ActionListener mainButtonListener;

    /**
     * 메인 패널에 포함될 패널들(텍스트 에디터 + 메인 버튼, 결과 화면, 오픈&세이브) 생성 및 초기화
     * 버튼은 메인 이벤트 리스너에 연결
     */
    public MainPanel() {
        new MainPresenter(this);

        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new BorderLayout());
        fileTextView = new FileTextView();
        mainButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        textEditorView = new TextEditorView();
        resultView = new ResultView();

        ArrayList<JButton> mainButtonArrayList = new ArrayList<>();
        Dimension buttonSize = new Dimension(100, 30);
        mainButtonArrayList.add(new JButton("Compile"));
        mainButtonArrayList.add(new JButton("SaveError"));
        mainButtonArrayList.add(new JButton("Delete"));
        mainButtonArrayList.add(new JButton("Clear"));

        for(JButton button : mainButtonArrayList){
            button.setPreferredSize(buttonSize);
            mainButtonPanel.add(button);
            button.addActionListener(mainButtonListener);
        }

        centerPanel.add(textEditorView, BorderLayout.CENTER);
        centerPanel.add(mainButtonPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, centerPanel, resultView);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.7);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(8);

        add(fileTextView, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    @Override
    public void showTextEditor(String text) {
        textEditorView.textArea.setText(text);
    }

    @Override
    public void showResult(String result) {
        resultView.resultArea.setText(result);
    }

    @Override
    public String getTextEditor() {
        return textEditorView.textArea.getText();
    }

    @Override
    public String getResult() {
        return resultView.resultArea.getText();
    }

    @Override
    public String getOpenTextField() {
        return fileTextView.openTextField.getText();
    }

    @Override
    public String getSaveTextField() {
        return fileTextView.saveTextField.getText();
    }

    @Override
    public void showOpenTextField(String text) {
        fileTextView.openTextField.setText(text);
    }

    @Override
    public void showSaveTextField(String text) {
        fileTextView.saveTextField.setText(text);
    }

    /**
     * 오픈 텍스트필드와 세이브 텍스트필드 버튼으로 구성된 패널
     * 버튼은 메인 이벤트 리스너에 연결
     */
    public class FileTextView extends JPanel {
        private JTextField openTextField;
        private JTextField saveTextField;

        public FileTextView() {
            setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5 ,5));
            JPanel textFieldPanel = new JPanel(new GridLayout(2,1, 5 ,5));

            openTextField = new JTextField();
            saveTextField = new JTextField();
            textFieldPanel.add(openTextField);
            textFieldPanel.add(saveTextField);

            ArrayList<JButton> fileButtonArrayList = new ArrayList<>();
            Dimension buttonSize = new Dimension(100, 30);
            fileButtonArrayList.add(new JButton("Open"));
            fileButtonArrayList.add(new JButton("Save"));

            for(JButton button : fileButtonArrayList) {
                button.setPreferredSize(buttonSize);
                buttonPanel.add(button);
                button.addActionListener(mainButtonListener);
            }

            this.add(textFieldPanel, BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.EAST);

        }
    }

    /**
     * 텍스트 에디터로 구성된 패널
     */
    public static class TextEditorView extends JPanel {
        private JTextArea textArea;

        public TextEditorView() {
            setLayout(new BorderLayout());

            textArea = new JTextArea();
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            add(scrollPane, BorderLayout.CENTER);
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

    public void setEventListener(ActionListener buttonListener) {
        this.mainButtonListener = buttonListener;
    }

}
