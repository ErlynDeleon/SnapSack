package Windows;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Maguindanao extends JFrame {
    JLabel label = new JLabel ();
    Maguindanao () {
    ImageIcon image = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
    Border border = BorderFactory.createLineBorder(new Color(251, 136, 180), 5);
    this.add(label);
    this.setIconImage(image.getImage());
    this.getContentPane().setBackground(new Color(255, 190, 152));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(900, 1000); // resize
    this.setVisible(true);
    }
}

