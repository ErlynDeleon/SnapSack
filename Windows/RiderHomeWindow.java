package Windows;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RiderHomeWindow extends JFrame{
JLabel label = new JLabel();

  RiderHomeWindow(){
    this.getContentPane().setBackground(new Color(225, 175, 209));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
  }

}