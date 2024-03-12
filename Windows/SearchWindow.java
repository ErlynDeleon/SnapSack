package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchWindow extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private StPeter stPeter;

    public SearchWindow(StPeter stPeter) {
        this.stPeter = stPeter;

        setTitle("Search Address");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Enter Address:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText(); // No need to convert to lowercase
                JTextField addressField = stPeter.getAddressField(); 
                String address = addressField.getText(); 

                int count = 0;
                StringBuilder positions = new StringBuilder();
                int wordStart = -1;

                for (int i = 0; i < address.length(); i++) {
                    char currentChar = address.charAt(i);
                    if (Character.isWhitespace(currentChar) || i == address.length() - 1) {
                        if (wordStart != -1) {
                            String word = address.substring(wordStart, i);
                            if (word.equalsIgnoreCase(searchText)) {
                                count++;
                                positions.append(wordStart).append(", ");
                            }
                            wordStart = -1;
                        }
                    } else {
                        if (wordStart == -1) {
                            wordStart = i;
                        }
                    }
                }

                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "Occurrences: " + count + "\nPositions: " + positions.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No occurrences found.");
                }
            }
        });

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);

        add(panel);
        setVisible(true);
    }
}
// --------------------------------------------------------------------------------------- 
/*
        public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create an instance of StPeter and pass it to the SearchWindow constructor
                StPeter stPeter = new StPeter();
                new SearchWindow(stPeter);
            }
        });
    }
} 

*/
// -------------------------------------------------------------------------------------


