package Windows;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductWindow extends JFrame implements ActionListener {

    private List<Product> productList;
    private double weight;

    ProductWindow(double weight) {
        this.weight = weight;

        productList = new ArrayList<>();
        productList.add(new Product("Canned Goods", 5, 450));
        productList.add(new Product("Cooking Oil", 3, 725));
        productList.add(new Product("Noodles", 2.5, 375));
        productList.add(new Product("Soap", 7, 500));

        initUI();
    }

    private void initUI() {
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(129, 104, 157));
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1000, 700); // Adjust the size here
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        // Panel for Product Label
        JPanel productLabelPanel = new JPanel();
        productLabelPanel.setBackground(new Color(255, 204, 229));
        productLabelPanel.setBounds(50, 50, 500, 50);
        productLabelPanel.setLayout(new BorderLayout());

        JLabel productListLabel = new JLabel("PRODUCTS");
        productListLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productListLabel.setHorizontalAlignment(SwingConstants.CENTER);
        productLabelPanel.add(productListLabel, BorderLayout.CENTER);
        add(productLabelPanel);

        // Panel for Weight Label
        JPanel weightLabelPanel = new JPanel();
        weightLabelPanel.setBackground(new Color(255, 204, 229));
        weightLabelPanel.setBounds(50, 100, 500, 50);
        weightLabelPanel.setLayout(new BorderLayout());

        JLabel weightLabel = new JLabel("Weight: " + weight + " kg");
        weightLabel.setForeground(new Color(32, 32,32));
        weightLabel.setFont(new Font("Monospaced Bold Italic", Font.BOLD, 14));
        weightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weightLabelPanel.add(weightLabel, BorderLayout.CENTER);
        add(weightLabelPanel);

        // Panel for Table
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(new Color(255, 204, 229));
        tablePanel.setBounds(50, 150, 500, 400);
        tablePanel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product Names");
        tableModel.addColumn("Total Weight");
        tableModel.addColumn("Total Amount");

        List<List<Product>> combinations = findCombinations(productList);
        for (List<Product> combination : combinations) {
            Object[] rowData = new Object[3];
            StringBuilder productNames = new StringBuilder();
            double totalWeight = 0;
            double totalAmount = 0;
            for (Product product : combination) {
                productNames.append(product.name).append(", ");
                totalWeight += product.weight;
                totalAmount += product.amount;
            }
            productNames.delete(productNames.length() - 2, productNames.length());
            rowData[0] = productNames.toString();
            rowData[1] = totalWeight;
            rowData[2] = totalAmount;
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setEnabled(false);
        table.setRowHeight(25);
        table.setBackground(new Color(255, 204, 229));

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel);

        // Panel for Proceed Button
        JPanel proceedButtonPanel = new JPanel();
        proceedButtonPanel.setBounds(600, 350, 300, 30);
        proceedButtonPanel.setLayout(new BorderLayout());
       
        
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setForeground(new Color(33, 33, 33));
        proceedButton.setBackground(new Color(255,153,204));
        proceedButton.setFont(new Font("Monospaced Bold", Font.BOLD, 20));
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartingPoint st = new StartingPoint();
                st.setVisible(true);
                st.setLocationRelativeTo(null);
                dispose();
            }
        });
        proceedButtonPanel.add(proceedButton, BorderLayout.CENTER);
        add(proceedButtonPanel);

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
