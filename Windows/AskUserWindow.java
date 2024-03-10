package Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AskUserWindow extends JFrame {
  JLabel label = new JLabel();
    JButton customerButton = new JButton("Customer");
    JButton riderButton = new JButton("Rider");

  AskUserWindow() {
    this.add(label);
    ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
    this.setIconImage(icon.getImage());
    this.getContentPane().setBackground(new Color(225, 175, 209));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(500, 500);
    this.setVisible(true);
    this.setLayout(null);
    this.setLocationRelativeTo(null);

    label.setText("I AM A");
    label.setForeground(new Color(116, 105, 182));
    label.setFont(new Font("Monospaced", Font.BOLD, 50));
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.TOP);
    this.add(label);

    int buttonWidth = 150;
    int buttonHeight = 50;
    int gap = 15;
    int x = (this.getWidth()  + buttonWidth) / 4; 
    int y = 200; 
    customerButton.setBounds(x, y, buttonWidth, buttonHeight);
    customerButton.setFont(new Font("Arial", Font.BOLD, 14));
    customerButton.setBackground(Color.white);
    customerButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    customerButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    customerButton.setFocusPainted(false); 
    customerButton.addActionListener((ActionEvent e) -> {
        if (e.getSource() == customerButton) {
            CustomerHomeWindow custHome = new CustomerHomeWindow();
            custHome.setVisible(true);
            custHome.setLocationRelativeTo(null);
            this.dispose();
        }
    });
    this.add(customerButton);

    
    y += buttonHeight + gap;
    riderButton.setBounds(x, y, buttonWidth, buttonHeight);
    riderButton.setFont(new Font("Arial", Font.BOLD, 14));
    riderButton.setBackground(Color.white);
    riderButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    riderButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    riderButton.setFocusPainted(false); 
    riderButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == riderButton) {
          RiderHomeWindow riderHome = new RiderHomeWindow();
          riderHome.setVisible(true);
          riderHome.setLocationRelativeTo(null);
          this.dispose();
      }
  });
    this.add(riderButton); 
  }

  @Override
  public void setVisible(boolean visible) {
      super.setVisible(visible);
      if (visible) {
          int labelWidth = 180;
          int labelHeight = 50;
          int labelX = (int) ((this.getWidth() + labelWidth) / 4.5);
          int labelY = 130;
          label.setBounds(labelX, labelY, labelWidth, labelHeight);
      }
    }
}
