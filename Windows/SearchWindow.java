/*package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame {
    private JTextArea searchResultArea;
    private String customerAddress;

    public SearchWindow(String address) {
        this.customerAddress = address;

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
                if (customerAddress != null) { // Check if customerAddress is not null
                    String searchText = searchField.getText();
                    search(searchText);
                } else {
                    JOptionPane.showMessageDialog(SearchWindow.this, "Customer address is null!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
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
        if (customerAddress != null && !customerAddress.isEmpty()) {
            String lowercaseInputAddress = customerAddress.toLowerCase();
            String lowercaseSearchText = searchText.toLowerCase();
    
            int occurrences = 0;
            StringBuilder positions = new StringBuilder();
    
            int index = indexOfIgnoreCase(lowercaseInputAddress, lowercaseSearchText);
            while (index != -1) {
                occurrences++;
                if (positions.length() > 0) {
                    positions.append(", ");
                }
                positions.append(getWordPosition(customerAddress, index) + 1); // Add 1 to adjust position (1-based index)
                index = indexOfIgnoreCase(lowercaseInputAddress, lowercaseSearchText, index + 1);
            }
    
            StringBuilder result = new StringBuilder();
            if (occurrences > 0) {
                result.append("Address: ").append(customerAddress).append("\n");
                result.append("Number of Occurrences: ").append(occurrences).append("\n");
                result.append("Word's Position: ").append(positions);
            } else {
                result.append("No occurrences found for the word \"").append(searchText).append("\"");
            }
    
            searchResultArea.setText(result.toString());
        } else {
            searchResultArea.setText("Customer address is null or empty!");
        }
    }

    private int indexOfIgnoreCase(String input, String search) {
        return indexOfIgnoreCase(input, search, 0);
    }

    private int indexOfIgnoreCase(String input, String search, int fromIndex) {
        for (int i = fromIndex; i < input.length() - search.length() + 1; i++) {
            if (input.regionMatches(true, i, search, 0, search.length())) {
                return i;
            }
        }
        return -1;
    }

    private int getWordPosition(String inputAddress, int currentIndex) {
        int position = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (Character.isWhitespace(inputAddress.charAt(i))) {
                position++;
            }
        }
        return position;
    }
}*/
package Windows;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SearchWindow extends JFrame {

    private String address;
    private JLabel addressLabel = new JLabel();

    JLabel label = new JLabel();

    SearchWindow() {
        this.add(label);
       // ImageIcon icon = new ImageIcon("Windows\pictures\1-removebg-preview.png");
       // this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        addressLabel.setBounds(668, 480, 150, 50);
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addressLabel.setText("Address: " + address);
        this.add(addressLabel);
    }

    public SearchWindow(String address) {
        this();
        this.address= address;
        addressLabel.setText("Address: " + address); // Update with appropriate address
    }

}