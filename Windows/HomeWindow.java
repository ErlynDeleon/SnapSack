package Windows;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class HomeWindow extends JFrame {
  JLabel label = new JLabel();
  JButton button = new JButton("Continue");
  ImageIcon image = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
  Border border = BorderFactory.createLineBorder(new Color(251, 136, 180), 5);

  HomeWindow() {
    this.add(label);
    ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
    this.setIconImage(icon.getImage());
    this.getContentPane().setBackground(new Color(225, 175, 209));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(1500, 900);
    this.setVisible(true);
    this.setLayout(null);

  }
}
