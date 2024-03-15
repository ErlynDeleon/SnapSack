package Windows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class LandingWindow extends JFrame implements ActionListener {
  JLabel label = new JLabel();
  JButton button = new JButton("Continue");
  ImageIcon image = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
  Border border = BorderFactory.createLineBorder(new Color(254, 200, 216), 5);

  LandingWindow() {
    // for general
    this.add(label);
    ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
    this.setIconImage(icon.getImage());
    this.getContentPane().setBackground(new Color(255, 223, 211));
    this.setTitle("SnapSack");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false );
    this.setSize(1500, 900);
    this.setVisible(true);
    this.setLayout(null);
    this.setLocationRelativeTo(null);

    // image/logo design
    label.setIcon(image);
    label.setBorder(border);
    label.setVerticalAlignment(JLabel.CENTER);
    label.setHorizontalAlignment(JLabel.CENTER);

    // button design to continue to next window
    button.setBounds(668, 540, 150, 50);
    button.setFocusable(false);
    button.setBackground(new Color(210, 145, 188));
    button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    button.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    button.addActionListener(this);

    this.add(button);
  }
  //for next window
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button) {
      HomeWindow home = new HomeWindow();
      home.setVisible(true);
      home.setLocationRelativeTo(null);
      this.dispose();
    }
  }
}
