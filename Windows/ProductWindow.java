package Windows;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ProductWindow extends JFrame implements ActionListener {

    private JTextField weightField;
    private JButton searchButton;
    private JLabel label;
    private JButton button;
    private List<Product> productList;
    private double targetWeight; // Added member variable to store the target weight

    private double weight;
    private JLabel weightLabel = new JLabel();

    ProductWindow(double weight) {
        this.weight = weight;

        productList = new ArrayList<>(); // Initialize productList here

        productList.add(new Product("Canned Goods", 5, 450));
        productList.add(new Product("Cooking Oil", 3, 725));
        productList.add(new Product("Noodles", 2.5, 375));
        productList.add(new Product("Soap", 7, 500));

        initUI(); // Initialize the user interface
    }

    private void initUI() {
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label = new JLabel();
        label.setBounds(100, 100, 200, 50); // Adjust bounds as needed
        this.add(label);

        button = new JButton("Continue");
        button.setBounds(668, 540, 150, 50);
        button.setFocusable(false);
        button.setBackground(new Color(210, 145, 188));
        button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        button.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        button.addActionListener(this);
        this.add(button);

        weightLabel.setBounds(668, 480, 150, 50);
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        weightLabel.setText("Weight: " + weight + " kg");
        this.add(weightLabel);

        this.setVisible(true);

        searchProducts();
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

    private void searchProducts() {
        Result result = getClosestProducts(weight); // Use the provided weight
        displayResult(result.selectedProducts, weight); // Display the result
    }

    private Result getClosestProducts(double targetWeight) {
        Result result = new Result();
        result.selectedProducts = new ArrayList<>(productList);

        // Manually implement bubble sort to sort the selected products by proximity to the target weight
        for (int i = 0; i < result.selectedProducts.size() - 1; i++) {
            for (int j = 0; j < result.selectedProducts.size() - i - 1; j++) {
                double weightDiff1 = Math.abs(result.selectedProducts.get(j).getWeight() - targetWeight);
                double weightDiff2 = Math.abs(result.selectedProducts.get(j + 1).getWeight() - targetWeight);
                if (weightDiff1 > weightDiff2) {
                    // Swap the products
                    Product temp = result.selectedProducts.get(j);
                    result.selectedProducts.set(j, result.selectedProducts.get(j + 1));
                    result.selectedProducts.set(j + 1, temp);
                }
            }
        }

        return result;
    }

    private void displayResult(List<Product> products, double targetWeight) {
        // Find combinations of products whose total weight is less than or equal to the input weight
        List<List<Product>> combinations = findCombinations(products);

        // Manually implement bubble sort to sort the combinations by the difference between total weight and target weight (ascending order)
        for (int i = 0; i < combinations.size() - 1; i++) {
            for (int j = 0; j < combinations.size() - i - 1; j++) {
                double weightDiff1 = Math.abs(calculateTotalWeight(combinations.get(j)) - targetWeight);
                double weightDiff2 = Math.abs(calculateTotalWeight(combinations.get(j + 1)) - targetWeight);
                if (weightDiff1 > weightDiff2) {
                    // Swap the combinations
                    List<Product> temp = combinations.get(j);
                    combinations.set(j, combinations.get(j + 1));
                    combinations.set(j + 1, temp);
                }
            }
        }

        // Display the result
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 229));

        ImageIcon icon = new ImageIcon("C:\\Users\\lyyri\\Downloads\\1-removebg-preview.png");
        setIconImage(icon.getImage());

        JEditorPane outputPane = new JEditorPane();
        outputPane.setContentType("text/html"); // Set content type to HTML
        outputPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputPane);

        // Add header
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        htmlContent.append(String.format("<h2>PRODUCT</h2><p>Weight = %.2f</p>", targetWeight));
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
                StartingPoint st = new StartingPoint();
                st.setVisible(true);
                st.setLocationRelativeTo(null);
                dispose();
            }
        });
        panel.add(proceedButton, BorderLayout.SOUTH);
        setContentPane(panel);
        setLocationRelativeTo(this);
        setVisible(true);
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

    private double calculateTotalAmount(List<Product> products) {
        double totalAmount = 0;
        for (Product product : products) {
            totalAmount += product.getAmount();
        }
        return totalAmount;
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

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format("%-60s %-60s %-60s", name, weight, amount);
        }
    }
}
