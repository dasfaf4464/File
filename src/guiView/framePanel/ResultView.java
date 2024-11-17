package guiView.framePanel;

import GUIInterface.ResultInterface;
import guiPresenter.framePresenter.ResultPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResultView extends JPanel implements ResultInterface.View {
    private final JTextArea resultTextArea;
    private Font font;
    private Color color;

    private ActionListener resultViewListener;

    public ResultView() {
        new ResultPresenter(this);

        setLayout(new BorderLayout());

        resultTextArea = new JTextArea("testsetsadfasfdasdf");

        font = new Font("Cascade", Font.LAYOUT_LEFT_TO_RIGHT, 16);
        color = Color.white;

        resultTextArea.setBackground(color);
        resultTextArea.setFont(font);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(false);
        resultTextArea.setWrapStyleWord(false);
        resultTextArea.setFocusable(true);

        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void setResultViewListener(ActionListener resultViewListener) {
        this.resultViewListener = resultViewListener;
    }

    @Override
    public String getResultTextArea() {
        return this.resultTextArea.getText();
    }

    @Override
    public void setResultTextArea(String resultTextArea) {
        this.resultTextArea.setText(resultTextArea);
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
