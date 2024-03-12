package Windows;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow extends JFrame implements ActionListener {
    JLabel label = new JLabel();

    // For navigation panel
    JPanel navigationPanel = new JPanel(null);
    JButton productButton = new JButton("Proceed");

    // For asking user the weight panel
    JPanel askWeightPanel = new JPanel();
    JLabel weightLabel = new JLabel("What is the weight of the motor vehicle?: ");
    JTextField weightTextField = new JTextField();
    JButton submitButton = new JButton("Submit");

    // For main panel
    JPanel mainContainerPanel = new JPanel(null);


    HomeWindow(){
        //navigation panel size and color
        navigationPanel.setBackground(new Color(31, 37, 68));
        navigationPanel.setBounds(1270, 0, 230, 900);

        // Add image to the navigation panel
        ImageIcon logo = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
        Image scaleImage = logo.getImage().getScaledInstance(210, 230, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(scaleImage));
        label.setBounds(0, 300, 210, 200);
        navigationPanel.add(label);

        // Add button to the navigation panel
        productButton.setBounds(18, 780, 180, 70);
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
        // Add  the productButton to navigationPanel
        navigationPanel.add(productButton);

        // Create askWeight panel 
        askWeightPanel.setLayout(null);
        askWeightPanel.setBounds(0, 0, 1270, 50);
        askWeightPanel.setBackground(new Color(255, 230, 230));

        // Set text label properties
        weightLabel.setForeground(new Color(129, 104, 157));
        weightLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        weightLabel.setBounds(25, 10, 600, 30);

        // Add weight label to askWeight panel
        askWeightPanel.add(weightLabel);

        // Add text field for weight input 
        weightTextField.setBounds(530, 10, 100, 30);
        askWeightPanel.add(weightTextField);

        // Add submit button
        submitButton.setBounds(1170, 10, 80, 30);
        submitButton.setBackground(new Color(210, 145, 188)); 
        submitButton.setForeground(Color.WHITE); 
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); 
        submitButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        submitButton.setBorder(BorderFactory.createEtchedBorder()); 
        submitButton.addActionListener(this);
        askWeightPanel.add(submitButton);

        // Main Panel
        mainContainerPanel.setBackground(new Color(129, 104, 157));
        mainContainerPanel.setBounds(0, 50, 1270, 850);

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
        this.add(askWeightPanel);
        this.add(mainContainerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
        String input = weightTextField.getText();
        try {
            double weight = Double.parseDouble(input);
            
            // Check if weight is within the valid range
            if (weight == 1 || weight == 2) {
                JOptionPane.showMessageDialog(this, "The weight is not accepted. There are no products with this weight.");
            }
            else if (weight >= 2.5 && weight <= 15) {
                // If weight is within the valid range, add the product panel
                JLabel product = new JLabel("Product");
                product.setForeground(new Color(255, 228, 201));
                product.setFont(new Font("Monospaced", Font.BOLD, 20));
                product.setBounds(70, 20, 100, 50); // Adjust bounds as needed
                mainContainerPanel.add(product);

                if (weight == 2.5) {
                    JPanel noodles = new JPanel();
                    noodles.setLayout(null);
                    noodles.setBackground(new Color(255, 230, 230));
                    noodles.setBounds(10, 90, 200, 250); 
                    mainContainerPanel.add(noodles);
                    
                    JPanel labelNoodles = new JPanel();
                    labelNoodles.setBounds(0, 210, 200, 40);
                    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                    labelNoodles.setBorder(border);
                    noodles.add(labelNoodles);
                    JLabel noodlesText = new JLabel("Noodles");
                    noodlesText.setForeground(new Color(129, 104, 157));
                    noodlesText.setFont(new Font("Monospaced", Font.BOLD, 20));
                    noodlesText.setBounds(60, 210, 100, 20);
                    labelNoodles.add(noodlesText);
                }
                mainContainerPanel.repaint();
                mainContainerPanel.revalidate();
            } else {
                JOptionPane.showMessageDialog(this, "The weight is not accepted. There are no products with this weight.");
            }
            
        } catch (NumberFormatException ex) {
            // Invalid input format
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }
    }
}
}
