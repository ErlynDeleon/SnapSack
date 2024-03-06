package Pages;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame {
  Frame() {
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(1500, 900);
    this.setVisible(true);

    ImageIcon image = new ImageIcon("Pages\\Pages\\pictures\\image.png");
    this.setIconImage(image.getImage());
    this.getContentPane().setBackground(new Color(225, 175, 209));
  }
}
