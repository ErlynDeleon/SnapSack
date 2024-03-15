// palitan yung algorithm, hindi tinatanggap ng window na to pag ang address is may kasamang comma sa dulo
// try mo ilagay St peter, peter, peter
// ang lalabas lang sa no. of occur is 1
// and word position is 3
// make sure na maayos na algo nito ngayon 
// ayusin design pag aralan
// designan niyo buttons 


package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame {
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JButton proceedButton; 
    private String address;

    SearchWindow(String address) {
        this.address = address;

        // Frame setup
        setTitle("Search Window");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Search Panel
        setupSearchPanel();

        // Result TextArea
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Exit Button Panel
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        proceedButton = new JButton("Proceed");
        proceedButton.setEnabled(false); 
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
    }

    private void setupSearchPanel() {
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        JLabel searchLabel = new JLabel("Search Term:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClicked();
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(searchPanel, BorderLayout.NORTH);
    }

    private void searchButtonClicked() {
        String searchTerm = searchField.getText().toLowerCase();
        if (searchTerm.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            searchAndDisplay(searchTerm);
            proceedButton.setEnabled(true); 
        }
    }

    private void searchAndDisplay(String searchTerm) {
        String[] addresses = address.split("\n");

        int totalOccurrences = 0;
        StringBuilder resultText = new StringBuilder();
        StringBuilder positions = new StringBuilder();

        for (int i = 0; i < addresses.length; i++) {
            String currentAddress = addresses[i];
            if (currentAddress.toLowerCase().contains(searchTerm)) {
                totalOccurrences++;
                resultText.append("\nAddress: ").append(currentAddress).append("\n");

                String[] words = currentAddress.split("\\s+");
                for (int j = 0; j < words.length; j++) {
                    if (words[j].toLowerCase().equals(searchTerm)) {
                        positions.append(j + 1).append(", ");
                    }
                }
            }
        }

        if (totalOccurrences > 0) {
            positions.delete(positions.length() - 2, positions.length());
            resultText.append("Number of Occurrences: ").append(totalOccurrences).append("\n");
            resultText.append("Word's Position(s): ").append(positions);
        } else {
            resultText.append("No occurrences of the searched word found in the address.");
        }

        resultArea.setText(resultText.toString());
    }
}
