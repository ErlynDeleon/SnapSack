package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame {
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel resultPanel;
    private JTextArea resultArea;
    private String address;
    private JButton proceedButton;

    SearchWindow(String address) {
        this.address = address;

        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(23, 107, 135));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600); // Adjust width and height here
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Exit Button Panel
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBackground(new Color(224, 174, 208));
        proceedButton = new JButton("Proceed");
        proceedButton.setBackground(new Color(254, 217, 237));
        proceedButton.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        proceedButton.setEnabled(false);
        proceedButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        proceedButton.setFocusPainted(false);
        Dimension buttonSize = new Dimension(90, 25);
        proceedButton.setPreferredSize(buttonSize);
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExitWindow ew = new ExitWindow();
                ew.setVisible(true);
                ew.setLocationRelativeTo(null);
                dispose();
            }
        });
        exitPanel.add(proceedButton);
        add(exitPanel, BorderLayout.SOUTH);
        setVisible(true);

        // Search Panel
        searchPanel = new JPanel();
        searchPanel.setPreferredSize(new Dimension(350, 38)); // Set preferred size here
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(224, 174, 208)); // Set background color

        JLabel searchLabel = new JLabel("Search Address:");
        searchLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14)); // Set font and increase font size
        searchLabel.setForeground(Color.BLACK);
        searchField = new JTextField(20); // Increase the width of the text field

        searchButton = new JButton("Search");
        searchButton.setFont(new Font(Font.SERIF, Font.PLAIN, 13)); // Set font and increase font size
        searchButton.setForeground(Color.BLACK);
        searchButton.setFocusable(true);
        searchButton.setBackground(new Color(254, 217, 237)); // Set button color
        searchButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        searchButton.setFocusPainted(false);
        searchButton.setPreferredSize(buttonSize);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClicked();
            }
        });
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        this.add(searchPanel, BorderLayout.NORTH);

        // Result Panel
        resultPanel = new JPanel(new BorderLayout());
        resultPanel.setPreferredSize(new Dimension(700, 200)); // Set preferred size here

        // Result components
        resultArea = new JTextArea(5, 20); // Adjust the size here (rows, columns)
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(254, 217, 237)); // Set background color

        // Set font and size for the result text area
        resultArea.setFont(new Font(Font.SERIF, Font.PLAIN, 20)); // Set font to Sans-serif and adjust font size

        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(resultPanel, BorderLayout.CENTER);
        this.pack(); // Pack the components to fit preferred sizes
        this.setVisible(true);

    }

    private void searchButtonClicked() {
        String searchTerm = searchField.getText(); // Convert search term to lowercase
        if (searchTerm.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a word.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            // Search and display results
            searchAndDisplay(address, searchTerm);
            proceedButton.setEnabled(true);
        }
    }

    private void searchAndDisplay(String address, String searchTerm) {
        searchTerm = searchTerm.toLowerCase(); // Convert search term to lowercase

        StringBuilder resultText = new StringBuilder();
        StringBuilder positions = new StringBuilder();
        String displayedAddress = "";
        int totalOccurrences = 0;
        int index = 0;

        while (index < getAddressLength(address)) {
            int startIndex = index;
            while (index < getAddressLength(address) && getAddressChar(address, index) != '\n') {
                index++;
            }
            String currentAddress = getAddressSubstring(address, startIndex, index);

            if (containsString(currentAddress.toLowerCase(), searchTerm)) {
                if (!currentAddress.equals(displayedAddress)) {
                    if (!displayedAddress.isEmpty()) {
                        resultText.append("\nNumber of Occurrences: ").append(totalOccurrences).append("\n\n");
                        totalOccurrences = 0; // Reset occurrences count for the new address
                    }
                    resultText.append("Address: ").append(currentAddress).append("\n");
                    displayedAddress = currentAddress;
                }

                int wordCount = countOccurrences(currentAddress.toLowerCase(), searchTerm);
                totalOccurrences += wordCount;
                positions.append(getPositions(currentAddress.toLowerCase(), searchTerm)).append(", ");
            }

            index++;
        }

        // Append the last occurrence count and positions
        if (!displayedAddress.isEmpty()) {
            resultText.append("\nNumber of Occurrences: ").append(totalOccurrences).append("\n");
            if (totalOccurrences > 0) {
                positions.delete(positions.length() - 2, positions.length()); // Remove trailing comma and space
                resultText.append("Word's Position(s): ").append(positions);
            } else {
                resultText.append("No occurrences of the searched word found in this address.");
            }
        } else {
            resultText.append("No address found.");
        }

        resultArea.setText(resultText.toString());
    }

    private int getAddressLength(String address) {
        int len = 0;
        for (int i = 0;; i++) {
            try {
                getAddressChar(address, i);
                len++;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return len;
    }

    private char getAddressChar(String address, int index) {
        char[] addressChars = address.toCharArray();
        return addressChars[index];
    }

    private String getAddressSubstring(String address, int startIndex, int endIndex) {
        StringBuilder substring = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            substring.append(getAddressChar(address, i));
        }
        return substring.toString();
    }

    private boolean containsString(String str, String searchStr) {
        int strIndex = 0;
        int searchIndex = 0;

        while (strIndex < getAddressLength(str) && searchIndex < getAddressLength(searchStr)) {
            if (getAddressChar(str, strIndex) == getAddressChar(searchStr, searchIndex)) {
                searchIndex++;
            }
            strIndex++;
        }

        return searchIndex == getAddressLength(searchStr);
    }

    private int countOccurrences(String str, String subStr) {
        int count = 0;
        int index = 0;
        while ((index = indexOfSubString(str, subStr, index)) != -1) {
            count++;
            index += getAddressLength(subStr);
        }
        return count;
    }

    private int indexOfSubString(String str, String subStr, int startIndex) {
        int strIndex = startIndex;
        int searchIndex = 0;
        while (strIndex < getAddressLength(str)) {
            char strChar = getAddressChar(str, strIndex);
            char searchChar = getAddressChar(subStr, searchIndex);
            if (strChar == searchChar) {
                searchIndex++;
                if (searchIndex == getAddressLength(subStr)) {
                    return strIndex - getAddressLength(subStr) + 1;
                }
            } else {
                searchIndex = 0;
            }
            strIndex++;
        }
        return -1;
    }

    private String getPositions(String str, String subStr) {
        StringBuilder positions = new StringBuilder();
        int index = 0;
        int lastPosition = -1; // Keep track of the last position added
        int commaCount = 0; // Keep track of the number of commas added
        while ((index = indexOfSubString(str, subStr, index)) != -1) {
            int position = getWordPosition(str, index);
            positions.append(position).append(", ");
            lastPosition = position;
            commaCount++;
            index += getAddressLength(subStr);
        }
        if (lastPosition != -1 && commaCount > 0) {
            // Calculate the index of the last comma and remove it
            int commaIndex = positions.length() - 2; // Index of the last comma
            positions.delete(commaIndex, commaIndex + 2);
        }
        return positions.toString();
    }

    private int getWordPosition(String str, int index) {
        int count = 1;
        for (int i = 0; i < index; i++) {
            if (getAddressChar(str, i) == ' ') {
                count++;
            }
        }
        return count;
    }
}