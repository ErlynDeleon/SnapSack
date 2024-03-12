package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setSize(900, 1000); // Set window size to 900 width and 1000 height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setMaximumSize(new Dimension(500, 30)); // Limit maximum size

        JLabel addressLabel = new JLabel(
                "<html>Enter Your Address:<br>(#No, Street Name, Barangay, Municipality, Province):</html>");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        addressField = new JTextField();
        addressField.setFont(new Font("Arial", Font.PLAIN, 16));
        addressField.setMaximumSize(new Dimension(500, 30)); // Limit maximum size

        // Add more rigid areas to increase height
        inputPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Add more height
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing
        inputPanel.add(nameField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 0))); // Add spacing
        inputPanel.add(addressLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing
        inputPanel.add(addressField);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(430, 60)); // Set preferred size
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                displayArea.append("Customer's Name: " + name + "\nAddress: " + address + "\n\n");
                nameField.setText("");
                addressField.setText("");
            }
        });

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(430, 60)); // Set preferred size
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAndPrintDistances();
            }
        });

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        displayArea.setBackground(Color.ORANGE);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(500, 500)); // Adjust size to add more height

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
        buttonPanel.add(nextButton);

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

    private void sortAndPrintDistances() {
        String[] locations = { "St. Peter", "St. John", "Lanao", "Maguindanao" };
        int[] distances = { 0, 300, 150, 200 };
        int n = 4; // manually specify the length

        selectionSort(distances, locations, n);

        // Construct the string for sorted distances
        StringBuilder output = new StringBuilder();
        output.append("Distance from \n      St. Peter \n                to ").append(locations[1]).append(" = ")
                .append(distances[1]).append("km");
        output.append("\n                         to ").append(locations[2]).append(" = ").append(distances[2])
                .append("km");
        output.append("\n                                      to ").append(locations[3]).append(" = ")
                .append(distances[3]).append("km");

        // Set the string to the text area
        resultTextArea.setText(output.toString());
    }

    public static void selectionSort(int[] distances, String[] locations, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minDistance = distances[i];
            String minLocation = locations[i];
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (distances[j] < minDistance) {
                    minDistance = distances[j];
                    minLocation = locations[j];
                    minIndex = j;
                }
            }
            // Swap distances
            distances[minIndex] = distances[i];
            distances[i] = minDistance;

            // Swap locations
            locations[minIndex] = locations[i];
            locations[i] = minLocation;
        }
    }
}