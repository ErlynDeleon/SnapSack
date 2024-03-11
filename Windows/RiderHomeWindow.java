package Windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class RiderHomeWindow extends JFrame{
JLabel label = new JLabel();
JButton customerButton = new JButton("Panget");

  RiderHomeWindow(){
    //general window
    ImageIcon image = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
    //Border border = BorderFactory.createLineBorder(new Color(251, 136, 180), 5);
    this.add(label);
    this.setIconImage(image.getImage());
    this.getContentPane().setBackground(new Color(255, 190, 152));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(900, 1000); // pang resize ng window
    this.setVisible(true);

    // inside the window
    // title/text design
    label.setText("SnapSack");
    label.setForeground(new Color(255, 230, 230));
    label.setFont(new Font("Monospaced", Font.BOLD, 50));
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.BOTTOM);
  
}}