import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductWindow extends JFrame {

    private JTextField weightField;
    private JButton searchButton;

    private List<Product> productList;

    public ProductWindow() {
        setTitle("Snapsack");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productList = new ArrayList<>();
        // Include image paths for each product 
        productList.add(new Product("a", 2, 40, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("b", 16, 50, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("c", 1.98, 100, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("d", 5, 95, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("e", 11, 200, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("f", 4, 30, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("g", 21, 500, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));
        productList.add(new Product("h", 2.21, 800, "C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png"));

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
            ProductListWindow productListWindow = new ProductListWindow(targetWeight, result.selectedProducts);
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
        String imagePath; // Image path for the product

        public Product(String name, double weight, double amount, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.amount = amount;
            this.imagePath = imagePath;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return String.format("%-60s %-60s %-60s", name, weight, amount);
        }

        // Getter for the ImageIcon
        public ImageIcon getImageIcon() {
            return new ImageIcon(imagePath);
        }
    }

    private class ProductListWindow extends JFrame {

        private final double targetWeight;

        public ProductListWindow(double targetWeight, List<Product> products) {
            this.targetWeight = targetWeight;
            setTitle("Snapsack");
            setSize(900, 600);
            ImageIcon icon = new ImageIcon("C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png");
            setIconImage(icon.getImage());
            JPanel panel = new JPanel();
            panel.setBackground(Color.PINK);
            
            JEditorPane outputPane = new JEditorPane();
            outputPane.setContentType("text/html"); // Set content type to HTML
            outputPane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputPane);

            // Add header
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><body>");
            htmlContent.append(String.format("<h2>PRODUCTS</h2><p>Weight = %.2f</p>", targetWeight));
            htmlContent.append("<table border='1'><tr><th>Product Name</th><th>Weight</th><th>Amount</th><th>Image</th></tr>");

            for (Product product : products) {
                htmlContent.append(String.format("<tr><td>%s</td><td>%.2f</td><td>%.2f</td><td><img src='%s' width='100' height='100'/></td></tr>",
                        product.name, product.weight, product.amount, product.imagePath));
            }
             
            
            htmlContent.append("</table></body></html>");

            outputPane.setText(htmlContent.toString());

            panel.add(scrollPane);

            setContentPane(panel);
            setLocationRelativeTo(ProductGUI.this);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductWindow());
    }
}

