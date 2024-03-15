package Windows;

import javax.swing.*;
import java.awt.*;

public class Lanao extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextArea displayArea;
    private JTextArea resultTextArea;

    public Lanao() {
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        setTitle("SnapSack");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        inputPanel.setBackground(new Color(232, 147, 207));

        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        nameField = createTextField(500, 30);

        JLabel addressLabel = new JLabel("<html>Enter Your Address:<br><font size=\"4\">(#No, Street Name, Barangay, Municipality, Province)</font></html>");
        addressLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        addressField = createTextField(500, 50);

        addComponentsWithSpacing(inputPanel, nameLabel, 50);
        addComponentsWithSpacing(inputPanel, nameField, 0);
        addComponentsWithSpacing(inputPanel, addressLabel, 40);
        addComponentsWithSpacing(inputPanel, addressField, 0);

        boolean[] isClicked = {false, false}; // Index 0 for isSubmitClicked, Index 1 for isDistanceClicked

        // Submit Button
        JButton submitButton = createButton("SUBMIT", 230, 60);
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String address = addressField.getText();
            if (!name.isEmpty() && !address.isEmpty()) {
                isClicked[0] = true;
                String displayText = "\t\tCustomer's Information\n\n\n" +
                        "Customer's Name: " + name + "\n\n\n\n" +
                        "Address: " + address;
                updateDisplayArea(displayText);
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in both Name and Address fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Distance Button
        JButton distanceButton = createButton("DISTANCE", 230, 60);
        distanceButton.addActionListener(e -> {
            if (isClicked[0]) {
                performTSP();
                isClicked[1] = true;
            } else {
                JOptionPane.showMessageDialog(null, "Please fill up your information first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Proceed Button
        JButton proceedButton = createButton("PROCEED", 230, 60);
        proceedButton.addActionListener(e -> {
            if (isClicked[1]) {
                String address = addressField.getText();
                openSearchWindow(address);
            } else {
                JOptionPane.showMessageDialog(null, "Please calculate distance first.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        

        // Display Area of Customer Name and Address
        displayArea = createDisplayArea(300, 100);

        // Result TextArea
        resultTextArea = createResultTextArea(400, 250);

        addComponentsToFrame(inputPanel, submitButton, distanceButton, proceedButton);
    }

    private JTextField createTextField(int width, int height) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Serif", Font.PLAIN, 18));
        textField.setMaximumSize(new Dimension(width, height));
        textField.setBorder(BorderFactory.createLineBorder(new Color(100, 32, 170)));
        return textField;
    }

    private void addComponentsWithSpacing(JPanel panel, Component component, int spacing) {
        panel.add(Box.createRigidArea(new Dimension(0, spacing)));
        panel.add(component);
    }

    private JButton createButton(String label, int width, int height) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    private JTextArea createDisplayArea(int width, int height) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Bookman Old Style", Font.BOLD, 16));
        area.setForeground(Color.BLACK);
        area.setBackground(new Color(243, 188, 200));
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(width, height));
        return area;
    }

    private JTextArea createResultTextArea(int width, int height) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.BOLD, 28));
        area.setBackground(new Color(255, 142, 143));
        area.setForeground(Color.BLACK);
        area.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(width, height));
        return area;
    }

    private void updateDisplayArea(String text) {
        displayArea.setText(text);
    }

    private void addComponentsToFrame(JPanel inputPanel, JButton submitButton, JButton distanceButton, JButton proceedButton) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.WEST);
        topPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        buttonPanel.add(distanceButton);
        buttonPanel.add(proceedButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(resultPanel);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Algorithm
    private void performTSP() {
        int[][] graph = {
                {0, 300, 150, 200},
                {150, 0, 200, 300},
                {100, 120, 0, 200},
                {200, 200, 100, 0}
        };
        resultTextArea.append("\t\t\tAll Possible Routes on Lanao" + "\n");
        boolean[] visited = new boolean[4];
        setAllFalse(visited);

        int[] path = new int[4];
        for (int i = 0; i < 4; i++) {
            path[i] = -1;
        }

        path[0] = 2;
        visited[2] = true;
        tsp(2, 1, 0, path, graph, visited);

        resultTextArea.append("\n\t\t\t\tShortest Path: \n");
        resultTextArea.append(shortestPath + "\n");
    }

    private void setAllFalse(boolean[] array) {
        for (int i = 0; i < 4; i++) {
            array[i] = false;
        }
    }

    private int shortestDistance = 1000;
    private String shortestPath;

    private void tsp(int currentVertex, int count, int dist, int[] path, int[][] graph, boolean[] visited) {
        if (count == 4 && graph[currentVertex][2] > 0) {
            String route = buildRouteString(path, dist + graph[currentVertex][2]);
            resultTextArea.append(route + "\n");
            if (dist + graph[currentVertex][2] < shortestDistance) {
                shortestDistance = dist + graph[currentVertex][2];
                shortestPath = route;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 4; i++) {
            sb.append(locations[path[i]]);
            if (i < 3) {
                sb.append(" --> ");
            }
        }
        sb.append(" --> ").append(locations[2]).append(" = ").append(dist).append("km");
        return sb.toString();
    }

    private void openSearchWindow(String address) {
        SearchWindow searchWindow = new SearchWindow(address);
        searchWindow.setVisible(true);
        searchWindow.setLocationRelativeTo(null);
    }
}
