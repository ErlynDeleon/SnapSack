package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame {
    private JTextArea addressField;
    private JTextArea searchResultArea;
    private String inputAddress; // Added field to store the input address

    public SearchWindow(String inputAddress) {
        this.inputAddress = inputAddress; // Initialize the input address

        setTitle("Search Address");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Set up the UI components
        JLabel searchLabel = new JLabel("Search Address:");
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));

        // Create the search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                search(searchText);
            }
        });

        // Set up the search result area
        searchResultArea = new JTextArea();
        searchResultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(searchResultArea);

        // Arrange UI components
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(searchLabel, BorderLayout.WEST);
        panel.add(searchField, BorderLayout.CENTER);
        panel.add(searchButton, BorderLayout.EAST);
        add(panel, BorderLayout.NORTH);
        add(resultScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void search(String searchText) {
        String lowercaseInputAddress = inputAddress.toLowerCase();
        String lowercaseSearchText = searchText.toLowerCase();

        int occurrences = 0;
        StringBuilder positions = new StringBuilder();

        int index = lowercaseInputAddress.indexOf(lowercaseSearchText);
        while (index != -1) {
            occurrences++;
            if (positions.length() > 0) {
                positions.append(", ");
            }
            positions.append(getWordPosition(inputAddress, index));
            index = lowercaseInputAddress.indexOf(lowercaseSearchText, index + 1);
        }

        StringBuilder result = new StringBuilder();
        if (occurrences > 0) {
            result.append("Address: ").append(inputAddress).append("\n");
            result.append("Number of Occurrences: ").append(occurrences).append("\n");
            result.append("Word's Position: ").append(positions);
        } else {
            result.append("No occurrences found for the word \"").append(searchText).append("\"");
        }

        searchResultArea.setText(result.toString());
    }

    private int getWordPosition(String inputAddress, int currentIndex) {
        int position = 1;
        for (int i = 0; i < currentIndex; i++) {
            if (inputAddress.charAt(i) == ' ') {
                position++;
            }
        }
        return position;
    }
}
