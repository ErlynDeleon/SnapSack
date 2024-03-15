package Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartingPoint extends JFrame {
  JLabel label = new JLabel();
  JButton stpeterButton = new JButton("St. Peter");
  JButton stjohnButton = new JButton("St. John");
  JButton lanaoButton = new JButton("Lanao");
  JButton maguindanaoButton = new JButton("Maguindanao");

  public StartingPoint() {
    // general window
    ImageIcon image = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");

    this.setIconImage(image.getImage());
    this.getContentPane().setBackground(new Color(255, 200, 221)); // Lighter pink color
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setSize(600, 600); // Reduced window size
    this.setVisible(true);
    this.setLayout(null);
    this.setLocationRelativeTo(null);

    // inside the window
    // title/text design
    JLabel label = new JLabel("Where are you?");
    label.setForeground(new Color(49, 54, 63));
    label.setFont(new Font("Baskerville Old Face", Font.BOLD, 50)); // Larger font size and bold
    label.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
    label.setVerticalAlignment(JLabel.TOP); // Align the text to the top
    label.setBounds(0, 100, this.getWidth(), 80); // Position the label slightly lower
    this.add(label);

    // button for st.peter
    int buttonWidth = 200; // Reduced button width
    int buttonHeight = 120; // Reduced button height
    int gap = 10;

    // Calculate x-coordinate for st.peter button (center left)
    int stpeterX = (this.getWidth() / 2 - buttonWidth - gap) / 2;
    int y = 200;
    stpeterButton.setBounds(stpeterX, y, buttonWidth, buttonHeight);
    stpeterButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24)); // Larger font size
    stpeterButton.setBackground(new Color(188, 190, 235)); // #bcbeeb color
    stpeterButton.setFocusPainted(false);
    stpeterButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == stpeterButton) {
        StPeter peter = new StPeter();
        peter.setVisible(true);
        peter.setLocationRelativeTo(null);
        this.dispose();
      }
    });
    this.add(stpeterButton);

    // Calculate x-coordinate for st.john button (center right)
    int stjohnX = this.getWidth() / 2 + (this.getWidth() / 2 - buttonWidth - gap) / 2;
    stjohnButton.setBounds(stjohnX, y, buttonWidth, buttonHeight);
    stjohnButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24)); // Larger font size
    stjohnButton.setBackground(new Color(188, 190, 235)); // #bcbeeb color
    stjohnButton.setFocusPainted(false);
    stjohnButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == stjohnButton) {
        StJohn john = new StJohn();
        john.setVisible(true);
        john.setLocationRelativeTo(null);
        this.dispose();
      }
    });
    this.add(stjohnButton);

    // Lanao button
    int lanaoX = stpeterX;
    lanaoButton.setBounds(lanaoX, y + buttonHeight + gap, buttonWidth, buttonHeight);
    lanaoButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24)); // Larger font size
    lanaoButton.setBackground(new Color(188, 190, 235)); // #bcbeeb color
    lanaoButton.setFocusPainted(false);
    lanaoButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == lanaoButton) {
        Lanao lanao = new Lanao();
        lanao.setVisible(true);
        lanao.setLocationRelativeTo(null);
        this.dispose();
      }
    });
    this.add(lanaoButton);

    // Maguindanao button
    int maguindanaoX = stjohnX;
    maguindanaoButton.setBounds(maguindanaoX, y + buttonHeight + gap, buttonWidth, buttonHeight);
    maguindanaoButton.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24)); // Larger font size
    maguindanaoButton.setBackground(new Color(188, 190, 235)); // #bcbeeb color
    maguindanaoButton.setFocusPainted(false);
    maguindanaoButton.addActionListener((ActionEvent e) -> {
      if (e.getSource() == maguindanaoButton) {
        Maguindanao maguin = new Maguindanao();
        maguin.setVisible(true);
        maguin.setLocationRelativeTo(null);
        this.dispose();
      }
    });
    this.add(maguindanaoButton);
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    if (visible) {
      int labelWidth = 180;
      int labelHeight = 50;
      int labelX = (int) ((this.getWidth() + labelWidth) / 4.5);
      int labelY = 100;
      label.setBounds(labelX, labelY, labelWidth, labelHeight);
    }
  }
}