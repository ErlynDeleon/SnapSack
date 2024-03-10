package Windows;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerHomeWindow extends JFrame{
    JLabel label = new JLabel();

    CustomerHomeWindow() {
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(31, 37, 68));
        navigationPanel.setBounds(0, 0, 230, 900);
        ImageIcon logo = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
        Image scaleImage = logo.getImage().getScaledInstance(210, 230,Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(scaleImage));
        
        this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        navigationPanel.add(label);
        this.add(navigationPanel);
        

    }
}
