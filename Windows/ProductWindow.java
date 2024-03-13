/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductGUI extends JFrame {

    private JTextField weightField;
    private JButton searchButton;

    private List<Product> productList;

    public ProductGUI() {
        setTitle("Snapsack");
        setSize(300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productList = new ArrayList<>();
        // Include image paths for each product
        productList.add(new Product("Canned Goods", 5, 450));
        productList.add(new Product("Cooking Oil", 3, 725));
        productList.add(new Product("Noodles", 2.5, 375));
        productList.add(new Product("Soap", 7, 500));

        initUI();
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Weight:"));
        weightField = new JTextField();
        panel.add(weightField);

        searchButton = new JButton("Enter");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProducts();
            }
        });
        panel.add(searchButton);

        add(panel, BorderLayout.NORTH);
    }

    private void searchProducts() {
        String weightStr = weightField.getText();

        try {
            double targetWeight = Double.parseDouble(weightStr);

            Result result = getClosestProducts(targetWeight);

            // Adjust the following line
            displayResult(result.selectedProducts);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid numeric value for Weight.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Result getClosestProducts(double targetWeight) {
        Result result = new Result();
        result.selectedProducts = new ArrayList<>(productList);

        // Sort the selected products by proximity to the target weight
        Collections.sort(result.selectedProducts, Comparator.comparingDouble(product -> Math.abs(product.getWeight() - targetWeight)));

        return result;
    }

    private void displayResult(List<Product> products) {
        // Find combinations of products whose total weight is less than or equal to the input weight
        List<List<Product>> combinations = findCombinations(products);

        // Sort the combinations by the difference between total weight and target weight (ascending order)
        Collections.sort(combinations, Comparator.comparingDouble(combination -> Math.abs(calculateTotalWeight(combination) - Double.parseDouble(weightField.getText()))));

        // Display the result
        ProductListWindow productListWindow = new ProductListWindow(combinations);
    }

    private List<List<Product>> findCombinations(List<Product> products) {
        List<List<Product>> combinations = new ArrayList<>();
        int n = products.size();
        for (int i = 1; i < (1 << n); i++) {
            List<Product> combination = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    combination.add(products.get(j));
                }
            }
            combinations.add(combination);
        }
        return combinations;
    }

    private double calculateTotalWeight(List<Product> products) {
        double totalWeight = 0;
        for (Product product : products) {
            totalWeight += product.getWeight();
        }
        return totalWeight;
    }

    private class Result {
        List<Product> selectedProducts;
    }

    private class ProductListWindow extends JFrame {

        public ProductListWindow(List<List<Product>> combinations) {
            setTitle("Snapsack");
            setSize(500, 750);
            JPanel panel = new JPanel();
            panel.setBackground(new Color(255,204,229));
            
            ImageIcon icon = new ImageIcon("C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png");
            setIconImage(icon.getImage());

            JEditorPane outputPane = new JEditorPane();
            outputPane.setContentType("text/html"); // Set content type to HTML
            outputPane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputPane);

            // Add header
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><body>");
            htmlContent.append(String.format("<h2>PRODUCT</h2><p>Weight = %.2f</p>", Double.parseDouble(weightField.getText())));
            htmlContent.append("<table border='1'><tr><th>Product Names</th><th>Total Weight</th><th>Total Amount</th></tr>");
          
            for (List<Product> combination : combinations) {
                htmlContent.append("<tr><td>");
                double totalWeight = calculateTotalWeight(combination);
                double totalAmount = calculateTotalAmount(combination);
                for (Product product : combination) {
                    htmlContent.append(product.name).append(", ");
                }
                htmlContent.delete(htmlContent.length() - 2, htmlContent.length());  // Remove the trailing comma and space
                htmlContent.append("</td><td>").append(totalWeight).append("</td><td>").append(totalAmount).append("</td></tr>");
            }

            htmlContent.append("</table></body></html>");

            outputPane.setText(htmlContent.toString());

            panel.add(scrollPane, BorderLayout.CENTER);

            // Proceed button
            JButton proceedButton = new JButton("Proceed");
            proceedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your logic for the "Proceed" button action here
                    // For now, let's just close the ProductListWindow
                    dispose();
                }
            });
            panel.add(proceedButton, BorderLayout.SOUTH);
            setContentPane(panel);
            setLocationRelativeTo(ProductGUI.this);
            setVisible(true);
        }

        // Add a method to calculate the total amount of a combination
        private double calculateTotalAmount(List<Product> products) {
            double totalAmount = 0;
            for (Product product : products) {
                totalAmount += product.getAmount();
            }
            return totalAmount;
        }
    }

    private static class Product {
        String name;
        double weight;
        double amount;

        public Product(String name, double weight, double amount) {
            this.name = name;
            this.weight = weight;
            this.amount = amount;
        }

        public double getWeight() {
            return weight;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("%-60s %-60s %-60s", name, weight, amount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductGUI());
    }
}

*/
package Windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProductWindow extends JFrame implements ActionListener{
  JLabel label = new JLabel();
  JButton button = new JButton("Continue");
  ProductWindow(){
    this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
            button.setBounds(668, 540, 150, 50);
    button.setFocusable(false);
    button.setBackground(new Color(210, 145, 188));
    button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    button.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
    button.addActionListener(this);

    this.add(button);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button) {
      StartingPoint st = new StartingPoint();
      st.setVisible(true);
      st.setLocationRelativeTo(null);
      this.dispose();
    }
  }
}
