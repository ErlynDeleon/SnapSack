package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow extends JFrame implements ActionListener {
    JLabel label = new JLabel();
    JPanel navigationPanel = new JPanel(null);
    JPanel buttonPanel = new JPanel();
    JButton productButton = new JButton("Proceed");

    JPanel mainContainerPanel = new JPanel(null);
    JPanel askWeightPanel = new JPanel();
    JLabel weightLabel = new JLabel("What is the weight of the motor vehicle?: ");
    JTextField weightTextField = new JTextField();
    JButton submitButton = new JButton("Submit");

    HomeWindow() {
        navigationPanel.setBackground(new Color(31, 37, 68));
        navigationPanel.setBounds(0, 0, 230, 900);

        // Add image to the navigation panel
        ImageIcon logo = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
        Image scaleImage = logo.getImage().getScaledInstance(210, 230, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(scaleImage));
        label.setBounds(10, 10, 210, 230);
        navigationPanel.add(label);

        // Create panel for buttons
        buttonPanel.setBounds(10, 300, 210, 300);
        buttonPanel.setBackground(new Color(31, 37, 68));
        buttonPanel.setLayout(new GridLayout(3, 0, 0, 50));

        buttonPanel.add(productButton);

        productButton.setFocusable(false);
        productButton.setBackground(new Color(210, 145, 188));
        productButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        productButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        productButton.setFocusPainted(false);
        productButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == productButton) {
                ProductWindow pd = new ProductWindow();
                pd.setVisible(true);
                pd.setLocationRelativeTo(null);
                this.dispose();
            }
        });

        navigationPanel.add(buttonPanel);

        //Main Panel
        mainContainerPanel.setBackground(new Color(129, 104, 157));
        mainContainerPanel.setBounds(230, 0, 1270, 900);

        // Create askWeight panel with BorderLayout
        askWeightPanel.setLayout(null);
        askWeightPanel.setBounds(0, 0, 1270, 50);
        askWeightPanel.setBackground(new Color(255, 230, 230));

        // Set text label properties
        weightLabel.setForeground(new Color(129, 104, 157));
        weightLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        weightLabel.setBounds(10, 10, 600, 30);

        // Add weight label to askWeight panel
        askWeightPanel.add(weightLabel);

        // Add text field for weight input
        weightTextField.setBounds(510, 10, 80, 30);
        askWeightPanel.add(weightTextField);

        // Add submit button
        submitButton.setBounds(590, 10, 80, 30);
        submitButton.setBackground(new Color(210, 145, 188)); 
        submitButton.setForeground(Color.WHITE); 
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        submitButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        submitButton.setBorder(BorderFactory.createEtchedBorder()); 
        submitButton.addActionListener(this);
        askWeightPanel.add(submitButton);

        // Add askWeight panel to mainContainerPanel
        mainContainerPanel.add(askWeightPanel);

        // Frame settings
        this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        navigationPanel.add(label);
        this.add(navigationPanel);
        this.add(mainContainerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String input = weightTextField.getText();
            try {
                int weight = Integer.parseInt(input);
                switch(weight){
                    case 1: 
                        
                    break;
                }
            } catch (NumberFormatException ex) {
                // Invalid input format
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
    }
}
