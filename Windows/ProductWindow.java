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
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productList = new ArrayList<>();
        productList.add(new Product("a", 2, 40));
        productList.add(new Product("b", 16, 50));
        productList.add(new Product("c", 1.98, 100));
        productList.add(new Product("d", 5, 95));
        productList.add(new Product("e", 11, 200));
        productList.add(new Product("f", 4, 30));
        productList.add(new Product("g", 21, 500));
        productList.add(new Product("h", 2.21, 800));
        initUI();
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
//input the weight
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

            ProductListWindow productListWindow = new ProductListWindow(result.selectedProducts);
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

    private static class Result {
        List<Product> selectedProducts;
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

        @Override
        public String toString() {
            return String.format("%-60s %-60s %-60s", name, weight, amount);
        }
    }

    private class ProductListWindow extends JFrame {

        public ProductListWindow(List<Product> products) {
            setTitle("Snapsack");
            setSize(900, 600);
         
            ImageIcon icon = new ImageIcon("C:\Users\lyyri\Downloads\1-removebg-preview.png");

            
            setIconImage(icon.getImage());

            JPanel panel = new JPanel();

            JTextArea outputTextArea = new JTextArea();
            outputTextArea.setEditable(false);
            outputTextArea.setBackground(Color.PINK);

            // Add header
            outputTextArea.append(String.format("%-50s %-50s %-50s%n", "Product Name", "Weight", "Amount"));
            outputTextArea.append("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            for (Product product : products) {
                outputTextArea.append(product + "\n");
            }
            panel.setBackground(Color.PINK);
            panel.add(new JScrollPane(outputTextArea));

            // Set the content pane to your panel
            setContentPane(panel);

            setLocationRelativeTo(ProductGUI.this); 
            setVisible(true);
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