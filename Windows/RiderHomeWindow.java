package Windows;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class RiderHomeWindow extends JFrame{
JLabel label = new JLabel();
    JButton stpeterButton = new JButton("St.Peter");
    JButton stjohnButton = new JButton("St.John");

  RiderHomeWindow(){
    //general window
    ImageIcon image = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
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
    label.setText("WHERE ARE YOU?");
    label.setForeground(new Color(49, 54, 63));
    label.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
    label.setHorizontalTextPosition(JLabel.CENTER);
    label.setVerticalTextPosition(JLabel.BOTTOM);
  
    //Buttons for St.Peter Street
    
    /*stpeterButton.setBounds(20, 10, 30, 20);
    stpeterButton.setFocusable(false);
    stpeterButton.setBackground(new Color(210, 145, 188));
    stpeterButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    stpeterButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    //stpeterButton.addActionListener(this);
  
    this.add(stpeterButton);

    
      y += buttonHeight + gap;
    stjohnButton.setBounds(x, y, buttonWidth, buttonHeight);
    stjohnButton.setFont(new Font("Arial", Font.BOLD, 14));
    stjohnButton.setBackground(Color.white);
    stjohnButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    stjohnButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    stjohnButton.setFocusPainted(false); 
    stjohnButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == stjohnButton) {
        StJohn john = new StJohn();
        john.setVisible(true);
        john.setLocationRelativeTo(null);
        this.dispose();
      }
  });
    this.add(stjohnButton); */
  }
  
}
