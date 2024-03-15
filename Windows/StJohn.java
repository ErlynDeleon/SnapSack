package Windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StJohn extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextArea displayArea;
    private JTextArea resultTextArea;
    private String customerAddress;



    public StJohn() {
        setTitle("SnapSack");
        setSize(1300, 800); // Set window size to 900 width and 1000 height
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
                performTSP();
                distanceButton.setEnabled(false);
            }
        });
       
        JButton proceedButton = new JButton("PROCEED");
proceedButton.setPreferredSize(new Dimension(230, 60)); // Set preferred size

proceedButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the address from the class-level customerAddress field
        String address = addressField.getText();
        
        // Open the SearchWindow with the name and address
        SearchWindow searchWindow = new SearchWindow(address);
        searchWindow.setVisible(true);
        searchWindow.setLocationRelativeTo(null);
        dispose(); // Close the StPeter window
    }
});


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
    int numVertices = 4;
    private void performTSP() {
        int[][] graph = {
                {0, 300, 150, 200},
                {150, 0, 200, 300},
                {100, 120, 0, 200},
                {200, 200, 100, 0}
        };
        resultTextArea.append("\t\t\tAll Possible Routes on St. John" + "\n");
        boolean[] visited = new boolean[numVertices];
        setAllFalse(visited);

        int[] path = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            path[i] = -1;
        }
        //change of index to 1
        path[0] = 1; 
        visited[1] = true;
        tsp(1, 1, 0, path, graph, visited);

        resultTextArea.append("\n\t\t\t\tShortest Path: \n");
        resultTextArea.append(shortestPath + "\n");
    }

    private void setAllFalse(boolean[] array) {
        for (int i = 0; i < numVertices; i++) {
            array[i] = false;
        }
    }

    private int shortestDistance = 1000;
    private String shortestPath;

    private void tsp(int currentVertex, int count, int dist, int[] path, int[][] graph, boolean[] visited) {
        if (count == numVertices && graph[currentVertex][1] > 0) { // Check if all vertices are visited and there's a connection back to St. John
            String route = buildRouteString(path, dist + graph[currentVertex][1]); // Add the distance back to St. John
            resultTextArea.append(route + "\n");
            if (dist + graph[currentVertex][1] < shortestDistance) {
                shortestDistance = dist + graph[currentVertex][1]; // Add the distance back to St. John
                shortestPath = route;
            }
            return;
        }

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && graph[currentVertex][i] > 0) {
                visited[i] = true;
                path[count] = i;
                tsp(i, count + 1, dist + graph[currentVertex][i], path, graph, visited);
                visited[i] = false;
                path[count] = -1;
            }
        }
    }

    private String buildRouteString(int[] path, int dist) {
        StringBuilder sb = new StringBuilder();
        String[] locations = {"St. Peter", "St. John", "Lanao", "Maguindanao"};
        for (int i = 0; i < numVertices; i++) {
            sb.append(locations[path[i]]);
            if (i < numVertices - 1) {
                sb.append(" --> ");
            }
        }
        sb.append(" --> ").append(locations[1]).append(" = ").append(dist).append("km");
        return sb.toString();
    }

}
  
