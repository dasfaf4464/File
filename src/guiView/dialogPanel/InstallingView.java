package guiView.dialogPanel;

import GUIInterface.dialog.InstallingInterface;
import guiPresenter.dialogPresenter.InstallingPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InstallingView extends JPanel implements InstallingInterface.View {
    private final JTextField basicJDKField;
    private final JTextField basicOutputField;
    private final JTextField newProjectFolderField;
    private final JTextField newProjectNameField;
    public final JDialog parentDialog;

    private ActionListener installingViewListener;

    public InstallingView(JDialog dialog) {
        new InstallingPresenter(this);

        parentDialog = dialog;
        parentDialog.setTitle("Installer");
        parentDialog.setModal(true);
        parentDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 250));

        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 30));
        saveButton.addActionListener(installingViewListener);

        JPanel valuePanel = new JPanel();
        valuePanel.setLayout(new GridLayout(8, 1, 0, 0));

        valuePanel.add(new JLabel("단일 자바 파일 컴파일과 실행 루트, 프로젝트 생성시 프로젝트 기본 JDK로 설정됩니다."));
        valuePanel.add(basicJDKField = new JTextField(System.getProperty("java.home")));
        valuePanel.add(new JLabel("단일 자바 파일 컴파일시 클래스 파일과 에러 파일 생성 위치, 폴더가 존재하지 않을 경우 폴더를 생성합니다."));
        valuePanel.add(basicOutputField = new JTextField(System.getProperty("user.home")+"\\Downloads\\output"));
        valuePanel.add(new JLabel("프로젝트를 만들 폴더를 입력해주세요, 비어있다면 프로젝트 생성 없이 실행합니다."));
        valuePanel.add(newProjectFolderField = new JTextField(System.getProperty("user.home") + "\\Downloads"));
        valuePanel.add(new JLabel("프로젝트 이름을 입력해주세요, 프로젝트 이름으로 프로젝트 폴더가 생성됩니다."));
        valuePanel.add(newProjectNameField = new JTextField("ProjectTest1"));
        add(valuePanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    @Override
    public void setInstallingViewListener(ActionListener installingViewListener) {
        this.installingViewListener = installingViewListener;
    }

    @Override
    public String getBasicJDKField() {
        return basicJDKField.getText();
    }

    @Override
    public String getBasicOutputField() {
        return basicOutputField.getText();
    }

    @Override
    public String getFirstProjectField() {
        return newProjectFolderField.getText();
    }

    @Override
    public String getProjectNameField() {
        return newProjectNameField.getText();
    }
}
