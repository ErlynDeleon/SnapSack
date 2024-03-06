package Windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class LandingWindow extends JFrame {
  LandingWindow() {

    // for general
    JLabel label = new JLabel();
    ImageIcon image = new ImageIcon("Windows\\pictures\\image.png");
    Border border = BorderFactory.createLineBorder(new Color(251, 136, 180), 5);
    this.add(label);
    this.setIconImage(image.getImage());
    this.getContentPane().setBackground(new Color(225, 175, 209));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(1500, 900);
    this.setVisible(true);

    // inside the window
    // title/text design
    label.setText("SnapSack");
    label.setForeground(new Color(255, 230, 230));
    label.setFont(new Font("Monospaced", Font.BOLD, 50));
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.BOTTOM);

    // image/logo design
    label.setIcon(image);
    label.setBorder(border);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);

    // for image and text
    label.setIconTextGap(+25);
  }
}
