package guiView.framePanel;

import GUIInterface.HistoryInterface;
import guiPresenter.framePresenter.HistoryPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class HistoryView extends JPanel implements HistoryInterface.View {
    private final JTextField activatedProjectName;
    private DefaultListModel<String> historyModel;
    private Color projectColor;
    private Color historyColor;

    private EventListener historyViewListener;

    public HistoryView() {
        new HistoryPresenter(this);

        setLayout(new BorderLayout());

        activatedProjectName = new JTextField("asdasd");
        historyModel = new DefaultListModel<>();
        projectColor = Color.green;
        historyColor = Color.yellow;

        //########### 테스트 코드\
        historyModel.addElement("history test1");
        historyModel.addElement("history test2");
        //########### 테스트 코드
        JList historyList = new JList(historyModel);

        activatedProjectName.setEditable(false);
        activatedProjectName.setFocusable(false);
        activatedProjectName.setHorizontalAlignment(SwingConstants.CENTER);
        activatedProjectName.setBackground(projectColor);

        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.setBackground(historyColor);

       // activatedProjectName.addActionListener(historyViewListener);
       // historyList.addListSelectionListener(historyViewListener);

        add(activatedProjectName, BorderLayout.NORTH);
        add(historyList, BorderLayout.CENTER);
    }

    @Override
    public void setHistoryViewListener(EventListener textEditorViewListener) {
        this.historyViewListener = textEditorViewListener;
    }

    @Override
    public String getProjectNameField() {
        return this.activatedProjectName.getText();
    }

    @Override
    public void setProjectNameField(String projectName) {
        this.activatedProjectName.setText(projectName);
    }

    @Override
    public void setHistoryModel(DefaultListModel<String> historyModel) {
        this.historyModel = historyModel;
    }

    @Override
    public void setProjectColor(Color projectColor) {
        this.projectColor = projectColor;
    }

    @Override
    public void setHistoryColor(Color color) {
        this.historyColor = color;
    }
}
