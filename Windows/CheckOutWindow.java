package Windows;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheckOutWindow extends JFrame{
  JLabel label = new JLabel();
  CheckOutWindow(){
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
  }
}