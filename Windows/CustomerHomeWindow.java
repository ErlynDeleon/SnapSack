package Windows;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerHomeWindow extends JFrame implements ActionListener{
    JLabel label = new JLabel();
    JButton homeButton = new JButton("Home");
    JButton productButton = new JButton("Products");
    JButton checkOutButton = new JButton("Check Out");

    CustomerHomeWindow() {
        JPanel navigationPanel = new JPanel(null); 
        navigationPanel.setBackground(new Color(31, 37, 68));
        navigationPanel.setBounds(0, 0, 230, 900); 
        
        // Add image to the navigation panel
        ImageIcon logo = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
        Image scaleImage = logo.getImage().getScaledInstance(210, 230, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(scaleImage));
        label.setBounds(10, 10, 210, 230);
        navigationPanel.add(label);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 300, 210, 300); 
        buttonPanel.setBackground(new Color(31, 37, 68));
        buttonPanel.setLayout(new GridLayout(3, 0, 0, 50));

        // Add buttons to the button panel
        buttonPanel.add(homeButton);
        buttonPanel.add(productButton);
        buttonPanel.add(checkOutButton);

        // Button styling
        homeButton.setFocusable(false);
        homeButton.setBackground(new Color(210, 145, 188));
        homeButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        homeButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        homeButton.setFocusPainted(false);
        homeButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == homeButton) {
                CustomerHomeWindow custHome = new CustomerHomeWindow();
                custHome.setVisible(true);
                custHome.setLocationRelativeTo(null);
                this.dispose();
            }
        });

        productButton.setFocusable(false);
        productButton.setBackground(new Color(210, 145, 188));
        productButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        productButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        productButton.setFocusPainted(false);
        productButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == productButton) {
                ProductWindow prodWind = new ProductWindow();
                prodWind.setVisible(true);
                prodWind.setLocationRelativeTo(null);
                this.dispose();
            }
        });

        checkOutButton.setFocusable(false);
        checkOutButton.setBackground(new Color(210, 145, 188));
        checkOutButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        checkOutButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        checkOutButton.setFocusPainted(false);
        checkOutButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == checkOutButton) {
                CheckOutWindow checkWind = new CheckOutWindow();
                checkWind.setVisible(true);
                checkWind.setLocationRelativeTo(null);
                this.dispose();
            }
        });

        // Add button panel to the navigation panel
        navigationPanel.add(buttonPanel);

        //Asking user weight of vehicle
        JPanel mainCointaner = new JPanel(null);



        // Frame settings
        this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        navigationPanel.add(label);
        this.add(navigationPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}
