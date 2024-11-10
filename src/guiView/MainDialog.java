package guiView;

import javax.swing.*;
import java.awt.*;

public class GUIDialog extends JDialog {
    public GUIDialog() {
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        setTitle("GUIDialog");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLayout(null);
        JLabel lblNewLabel = new JLabel("GUIDialog");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 414, 14);
        contentPane.add(lblNewLabel);
        JButton btnNewButton = new JButton("New Button");
        setVisible(true);
    }
}
