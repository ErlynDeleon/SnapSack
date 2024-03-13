package Windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StPeter extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextArea displayArea;
    private JTextArea resultTextArea;


    // Define locations and distances arrays
    private String[] locations = {"St. Peter", "St. John", "Lanao", "Maguindanao"};
    private int[][] distances = {
            {0, 300, 150, 200},
            {150, 0, 200, 300},
            {100, 120, 0, 200},
            {200, 200, 100, 0}
    };

    // Public accessor method to retrieve the address field
    public JTextField getAddressField() {
        return addressField;
    }

    public StPeter() {
        setTitle("SnapSack");
        setSize(1200, 800); // Set window size to 900 width and 1000 height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10)); // Add padding
        inputPanel.setBackground(new Color(249, 232, 151));


        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        nameField = new JTextField();
        nameField.setFont(new Font("Serif", Font.PLAIN, 18));
        nameField.setMaximumSize(new Dimension(500, 30)); // Limit maximum size
        Border newBorder = BorderFactory.createLineBorder(new Color(100, 32, 170)); // Example: red border
        nameField.setBorder(newBorder);


        JLabel addressLabel = new JLabel("<html>Enter Your Address:<br><font size=\"4\">(#No, Street Name, Barangay, Municipality, Province)</font></html>");


        addressLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        addressField = new JTextField();
        addressField.setFont(new Font("Serif", Font.PLAIN, 18));
        addressField.setMaximumSize(new Dimension(500, 50)); // Limit maximum size
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
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setPreferredSize(new Dimension(230, 60)); // Set preferred size
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
                displayArea.setBackground(new Color(255, 195, 116));
                displayArea.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Setting HTML content to JTextArea
                nameField.setText("");
                addressField.setText("");
                nameField.setEnabled(false);
                addressField.setEnabled(false);
                submitButton.setEnabled(false);
            }
        });

        // Distance Button
        JButton distanceButton = new JButton("DISTANCE");
        distanceButton.setPreferredSize(new Dimension(230, 60)); // Set preferred size
        distanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateRoutes();
                distanceButton.setEnabled(false);
            }
        });
       
        JButton proceedButton = new JButton("PROCEED");
        proceedButton.setPreferredSize(new Dimension(230, 60)); // Set preferred size
       
        /*submitButton.addActionListener(new ActionListener() {
            @Override
    public void actionPerformed(ActionEvent e) {
        // Add action for the button
    } */

        // Display Area of Customer Name and Address
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Bookman Old Style", Font.BOLD, 18));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        displayArea.setBackground(new Color(255, 243, 199));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(300, 100)); // Adjust size to add more height

        // Result TextArea
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("Monospaced", Font.BOLD, 28));
        resultTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultTextArea.setBackground(new Color(255, 142, 143));
        resultTextArea.setForeground(Color.BLACK);

        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);
        resultScrollPane.setPreferredSize(new Dimension(400, 250)); // Adjust size as needed

        // Add Components to Frame
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.WEST);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(distanceButton);
        buttonPanel.add(proceedButton);

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
    private void calculateRoutes() {
        int[] path = new int[4]; // Specific value of location length (4)
        for (int i = 0; i < 4; i++) {
            path[i] = i;
        }

        ArrayList<int[]> allPaths = new ArrayList<>();
        permute(path, 1, allPaths); // Start permutation from index 1

        int shortestDistance = 1000000; // Set an arbitrary large value
        int[] shortestPath = null;

        StringBuilder output = new StringBuilder();
        // Print the header line
    output.append("\tAll Possible Routes Starting on St.Peter Street\n\n");
        for (int[] p : allPaths) {
            int distance = calculateDistance(p);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                shortestPath = p;
            }
            output.append(formatPath(p)).append("\n");
        }

        output.append("\n\t\t\tShortest Path:\n").append(formatPath(shortestPath));
        resultTextArea.setText(output.toString());
    }

    private String formatPath(int[] path) {
        StringBuilder formattedPath = new StringBuilder();
        for (int i = 0; i < 4; i++) { // Specific value (4)
            formattedPath.append(locations[path[i]]);
            if (i != 3) { // Specific value (4 - 1 = 3)
                formattedPath.append(" -> ");
            }
        }
        
        formattedPath.append(" -> ").append(locations[path[0]]).append(" = ").append(calculateDistance(path)).append(" km");
        return formattedPath.toString();
    }

    private int calculateDistance(int[] path) {
        int distance = 0;
        for (int i = 0; i < 3; i++) { // Specific value (4 - 1 = 3)
            distance += distances[path[i]][path[i + 1]];
        }
        distance += distances[path[3]][path[0]]; // Return to starting point
        return distance;
    }

    private void permute(int[] path, int start, ArrayList<int[]> allPaths) {
        if (start == 4) {
            if (path[0] == 0) { // Check if the first location is "St. Peter"
                int[] newPath = new int[4]; // Create a new array with length 4
                System.arraycopy(path, 0, newPath, 0, 4); // Copy elements from path to newPath
                allPaths.add(newPath); // Add the new path to allPaths
            }
        } else {
            for (int i = start; i < 4; i++) { // Specific value (4)
                swap(path, start, i);
                permute(path, start + 1, allPaths);
                swap(path, start, i);
            }
        }
    }

    private void swap(int[] path, int i, int j) {
        int temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }
}
    
  
  //for next window
  
