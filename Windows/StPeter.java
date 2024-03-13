package Windows;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StPeter extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextArea displayArea;
    private JTextArea resultTextArea;

    // Public accessor method to retrieve the address field
    public JTextField getAddressField() {
        return addressField;
    }

    public StPeter() {
        setTitle("SnapSack");
        setSize(1100, 1000); // Set window size to 900 width and 1000 height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        inputPanel.setBackground(new Color(255, 195, 116));
        
       
        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        nameField = new JTextField();
        nameField.setFont(new Font("Serif", Font.PLAIN, 16));
        nameField.setMaximumSize(new Dimension(500, 30)); // Limit maximum size
        Border newBorder = BorderFactory.createLineBorder(new Color(100, 32, 170)); // Example: red border
        nameField.setBorder(newBorder);


        JLabel addressLabel = new JLabel("<html>Enter Your Address:<br><font size=\"4\">(#No, Street Name, Barangay, Municipality, Province)</font></html>");


        addressLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        addressField = new JTextField();
        addressField.setFont(new Font("Serif", Font.PLAIN, 16));
        addressField.setMaximumSize(new Dimension(500, 60)); // Limit maximum size
        Border new1Border = BorderFactory.createLineBorder(new Color(100, 32, 170)); // Example: red border
        addressField.setBorder(new1Border);




        // Add more rigid areas to increase height
        inputPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add more height
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Add spacing
        inputPanel.add(nameField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Add spacing
        inputPanel.add(addressLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Add spacing
        inputPanel.add(addressField);


        

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(430, 60)); // Set preferred size
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                StringBuilder displayText = new StringBuilder();
                displayText.append("\t\tCustomer's Information\n\n\n");
                displayText.append("Customer's Name: ").append(name).append("\n\n\n\n");
                displayText.append("Address: ").append(address);
            
                // Setting text and formatting
                displayArea.setText(displayText.toString());
                displayArea.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
                displayArea.setForeground(Color.BLACK);
                displayArea.setBackground(new Color(249, 232, 151));
                displayArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Setting HTML content to JTextArea
                nameField.setText("");
                addressField.setText("");
                nameField.setEnabled(false);
                addressField.setEnabled(false);
                submitButton.setEnabled(false);
            }
        });

        // Next Button
        JButton distanceButton = new JButton("Distance");
        distanceButton.setPreferredSize(new Dimension(430, 60)); // Set preferred size
        distanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // findShortestPath("St. Peter");
                distanceButton.setEnabled(false);
            }
        });

        // Display Area of Customer Name and Address
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        displayArea.setBackground(new Color(255, 243, 199));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(300, 300)); // Adjust size to add more height

        // Result TextArea
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("SansSerif", Font.PLAIN, 32));
        resultTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultTextArea.setBackground(new Color(255, 190, 152));

        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultScrollPane.setPreferredSize(new Dimension(400, 250)); // Adjust size as needed

        // Add Components to Frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.WEST);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(distanceButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalGlue()); // Add space above the buttons
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue()); // Add space below the buttons
        mainPanel.add(resultPanel);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true); // Make the window visible after all components are added
    }

// algo for tsp
}
