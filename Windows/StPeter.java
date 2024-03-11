package Windows;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class StPeter extends JFrame {
    JLabel label = new JLabel();
    public JTextArea resultTextArea;
   
    
    public StPeter() {
        
        ImageIcon image = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.add(label);
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(255, 190, 152));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(900, 1000); // resize

        //dto mag edit ng ano
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("SansSerif", Font.PLAIN, 32));
        resultTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultTextArea.setBackground(new Color(255, 190, 152));

        // Add the JTextArea to the JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(resultTextArea, BorderLayout.CENTER);

        // Call the sorting and printing method
        sortAndPrintDistances();

        setVisible(true); // Make the window visible after all components are added
    }
    private void sortAndPrintDistances() {
        String[] locations = {"St. Peter", "St. John", "Lanao", "Maguindanao"};
        int[] distances = {0, 300, 150, 200};
        int n = 4; // manually specify the length

        selectionSort(distances, locations, n);

        // Construct the string for sorted distances
        StringBuilder output = new StringBuilder();
        output.append("St. Peter to ").append(locations[1]).append(" ").append(distances[1]).append("km");
        output.append(" to ").append(locations[2]).append(" ").append(distances[2]).append("km");
        output.append(" to ").append(locations[3]).append(" ").append(distances[3]).append("km");

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
