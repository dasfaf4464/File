package guiView;

import javax.swing.*;
import java.awt.*;

public class SettingManagerView extends JPanel {
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, viewMenu, resultMenu, helpMenu;
    private JButton hamburgerButton;

    public SettingManagerView() {
        setLayout(new BorderLayout());

        // 메뉴 바 초기화
        menuBar = new JMenuBar();

        // 각 메뉴 추가
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        resultMenu = new JMenu("result");
        helpMenu = new JMenu("Help");

        // 메뉴 바에 메뉴 추가
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(resultMenu);
        menuBar.add(helpMenu);

        // 햄버거 버튼 초기화 및 추가
        hamburgerButton = new JButton("☰");
        hamburgerButton.setFocusPainted(false);
        hamburgerButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        hamburgerButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // 햄버거 버튼 클릭 시 팝업 메뉴 표시
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem settingsItem = new JMenuItem("Settings");
        JMenuItem preferencesItem = new JMenuItem("Preferences");
        popupMenu.add(settingsItem);
        popupMenu.add(preferencesItem);

        hamburgerButton.addActionListener(e -> {
            popupMenu.show(hamburgerButton, hamburgerButton.getWidth() / 2, hamburgerButton.getHeight());
        });

        // 상단 레이아웃 구성
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(hamburgerButton, BorderLayout.WEST);
        topPanel.add(menuBar, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
    }
}
