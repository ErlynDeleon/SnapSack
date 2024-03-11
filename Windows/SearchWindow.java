/* -----------------------------------------------------------------------------/////////////

package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchWindow extends JFrame {
    private JTextField searchField;
    private JButton searchButton;

    public SearchWindow() {
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
                String searchText = searchField.getText().toLowerCase();
                String address = CustomerInfoWindow.getAddress(); // Assuming CustomerInfoWindow has a static method to retrieve the address
                String[] words = address.toLowerCase().split("\\s+");
                int count = 0;
                StringBuilder positions = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(searchText)) {
                        count++;
                        positions.append(i).append(", ");
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

/* ----------------------------------------------------------//////////////

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchWindow();
            }
        });
    }
}
