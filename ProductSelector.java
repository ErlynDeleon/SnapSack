import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSelector {

    public static void main(String[] args) {
        // Create and configure the main frame
        JFrame mainFrame = new JFrame("Product Selector");
        mainFrame.setSize(1500, 900);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Panel for weight input
        JPanel weightPanel = new JPanel();
        JLabel weightLabel = new JLabel("Enter vehicle weight (1-15 kilos): ");
        JTextField weightField = new JTextField(10);
        JButton calculateButton = new JButton("Calculate");

        // Panel for displaying products
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));

        // Total amount label
        JLabel totalLabel = new JLabel("Total Amount: ");

        // Calculate button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear previous products
                productsPanel.removeAll();

                // Get the entered weight
                int weight;
                try {
                    weight = Integer.parseInt(weightField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Please enter a valid weight.");
                    return;
                }

                // Check weight validity
                if (weight < 1 || weight > 15) {
                    JOptionPane.showMessageDialog(mainFrame, "Weight should be between 1 and 15 kilos.");
                    return;
                }

                // Calculate products based on weight
                Product[] products = calculateProducts(weight);
                double totalAmount = 0;
                for (Product product : products) {
                    JLabel productLabel = new JLabel(product.getName() + " - Weight/Box: " + product.getWeightPerBox()
                            + " kg - Amount: " + product.getAmount());
                    productsPanel.add(productLabel);
                    totalAmount += product.getAmount();
                }

                // Update total amount label
                totalLabel.setText("Total Amount: " + totalAmount);

                // Refresh panel
                productsPanel.revalidate();
                productsPanel.repaint();
            }
        });

        // Add components to weight panel
        weightPanel.add(weightLabel);
        weightPanel.add(weightField);
        weightPanel.add(calculateButton);

        // Add components to main frame
        mainFrame.add(weightPanel, BorderLayout.NORTH);
        mainFrame.add(new JScrollPane(productsPanel), BorderLayout.CENTER);
        mainFrame.add(totalLabel, BorderLayout.SOUTH);

        // Center the main frame on the screen
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Dummy method to calculate products based on vehicle weight
    private static Product[] calculateProducts(int weight) {
        // Dummy products, replace with actual logic
        if (weight <= 5) {
            return new Product[] { new Product("Product A", 2, 10), new Product("Product B", 3, 15) };
        } else {
            return new Product[] { new Product("Product C", 5, 20), new Product("Product D", 4, 12) };
        }
    }
}

class Product {
    private String name;
    private int weightPerBox;
    private double amount;

    public Product(String name, int weightPerBox, double amount) {
        this.name = name;
        this.weightPerBox = weightPerBox;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getWeightPerBox() {
        return weightPerBox;
    }

    public double getAmount() {
        return amount;
    }
}
