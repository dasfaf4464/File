package guiView;

import GUIInterface.DialogInterface;
import guiPresenter.DialogPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Dialog extends JDialog implements DialogInterface.DialogViewInterface {

    private JPanel dialogPanel;
    private final InstallerDialog installerPanel;
    private final SettingDialog settingsPanel;
    private ActionListener installerListener;
    private ActionListener settingsListener;

    public Dialog() {
        new DialogPresenter(this);
        setAlwaysOnTop(true);
        setLocationRelativeTo(getParent());

        installerPanel = new InstallerDialog(this);
        settingsPanel = new SettingDialog(this);

        setPanel(installerPanel);

        add(dialogPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    @Override
    public void setPanel(JPanel panel) {
        if(panel instanceof InstallerDialog) {
            setTitle("Installer");
            setModal(true);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            dialogPanel = installerPanel;
        } else if(panel instanceof SettingDialog) {
            setTitle("Settings");
            setModal(false);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            dialogPanel = settingsPanel;
        }

        setResizable(false);
    }

    @Override
    public String getBasicJDKField() {
        return installerPanel.basicJDKField.getText();
    }

    @Override
    public String getBasicOutputField() {
        return installerPanel.basicOutputField.getText();
    }

    @Override
    public String getFirstProjectField() {
        return installerPanel.newProjectField.getText();
    }

    public class InstallerDialog extends JPanel {
        JDialog dialogFrame;
        private final JTextField basicJDKField;
        private final JTextField basicOutputField;
        private final JTextField newProjectField;

        public InstallerDialog(JDialog dialogFrame) {
            this.dialogFrame = dialogFrame;

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(600, 200));

            JButton saveButton = new JButton("Save");
            saveButton.setPreferredSize(new Dimension(100, 30));
            saveButton.addActionListener(installerListener);

            JPanel valuePanel = new JPanel();
            valuePanel.setLayout(new GridLayout(6, 1, 0, 0));

            valuePanel.add(new JLabel("단일 자바 파일 컴파일과 실행 루트"));
            valuePanel.add(basicJDKField = new JTextField(System.getProperty("java.home")));
            valuePanel.add(new JLabel("단일 자바 파일 컴파일시 클래스 파일과 에러 파일 생성 위치, 폴더가 존재하지 않을 경우 폴더를 생성합니다."));
            valuePanel.add(basicOutputField = new JTextField(System.getProperty("user.home")+"\\Downloads\\output"));
            valuePanel.add(new JLabel("새로운 프로젝트를 만드려면 프로젝트 폴더를 입력해주세요, 비어있다면 프로젝트 생성 없이 실행합니다."));
            valuePanel.add(newProjectField = new JTextField(System.getProperty("user.home")));

            add(valuePanel, BorderLayout.CENTER);
            add(saveButton, BorderLayout.SOUTH);
        }

    }

    public static class SettingDialog extends JPanel {
        JDialog dialogFrame;

        public SettingDialog(JDialog dialogFrame) {
            this.dialogFrame = dialogFrame;

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(600, 500));

        }
    }

    public static class saveCheckDialog extends JPanel {
        JDialog dialogFrame;

        public saveCheckDialog(JDialog dialogFrame) {
            this.dialogFrame = dialogFrame;

            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(300, 150));

        }
    }

    public void setEventListener(ActionListener installerListener, ActionListener settingsListener) {
        this.installerListener = installerListener;
        this.settingsListener = settingsListener;
    }
}
