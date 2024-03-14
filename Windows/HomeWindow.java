
package Windows;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HomeWindow extends JFrame implements ActionListener {
    JLabel label = new JLabel();

    // For navigation panel
    JPanel navigationPanel = new JPanel(null);
    JButton productButton = new JButton("Proceed");

    // For asking user the weight panel
    JPanel askWeightPanel = new JPanel();
    JLabel weightLabel = new JLabel("Enter the weight (1-15 kilos)?: ");
    JTextField weightTextField = new JTextField();
    JButton submitButton = new JButton("Submit");

    // For main panel
    JPanel mainContainerPanel = new JPanel(null);
    JButton refreshButton = new JButton("Enter new weight");

    HomeWindow() {
        //navigation panel size and color
        navigationPanel.setLayout(null);
        navigationPanel.setBackground(new Color(31, 37, 68));
        navigationPanel.setBounds(1270, 0, 230, 900);

        // Add image to the navigation panel
        ImageIcon logo = new ImageIcon("Windows\\pictures\\2-removebg-preview.png");
        Image scaleImage = logo.getImage().getScaledInstance(210, 230, Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(scaleImage));
        label.setBounds(0, 300, 210, 200);
        navigationPanel.add(label);

        // Add button to the navigation panel
        productButton.setBounds(18, 780, 180, 70);
        productButton.setFocusable(false);
        productButton.setBackground(new Color(210, 145, 188));
        productButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        productButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        productButton.setFocusPainted(false);
        productButton.addActionListener((ActionEvent e) -> {
            if (weightTextField.getText().isEmpty()) {

                ImageIcon icon = new ImageIcon("Windows\\pictures\\download (3).jfif"); 
                Image scaledImage = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
                ImageIcon scaledIcon = new ImageIcon(scaledImage);       

                JOptionPane.showMessageDialog(this, "Please enter the weight before proceeding.", "Missing Weight", JOptionPane.WARNING_MESSAGE, scaledIcon);
            } else {
                double weight = Double.parseDouble(weightTextField.getText());
                if (e.getSource() == productButton) {
                    ProductWindow pd = new ProductWindow(weight);
                    pd.setVisible(true);
                    pd.setLocationRelativeTo(null);
                    dispose();
                }
            }
        });
        // Add  the productButton to navigationPanel
        navigationPanel.add(productButton);

        // Create askWeight panel
        askWeightPanel.setLayout(null);
        askWeightPanel.setBounds(0, 0, 1270, 50);
        askWeightPanel.setBackground(new Color(255, 230, 230));

        // Set text label properties
        weightLabel.setForeground(new Color(129, 104, 157));
        weightLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        weightLabel.setBounds(25, 10, 600, 30);

        // Add weight label to askWeight panel
        askWeightPanel.add(weightLabel);

        // Add text field for weight input
        weightTextField.setBounds(400, 10, 100, 30);
        askWeightPanel.add(weightTextField);

        refreshButton.setFocusable(false);
        refreshButton.setBackground(new Color(210, 145, 188));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        refreshButton.setBorder(BorderFactory.createEtchedBorder());
        refreshButton.setFocusPainted(false);
        refreshButton.setBounds(1100, 10, 150, 30);
        refreshButton.addActionListener((ActionEvent e) -> {
            if (e.getSource() == refreshButton) {
                HomeWindow home = new HomeWindow();
                home.setVisible(true);
                home.setLocationRelativeTo(null);
                this.dispose();
            }
        });
        askWeightPanel.add(refreshButton);

        // Add submit button
        submitButton.setBounds(1013, 10, 80, 30);
        submitButton.setBackground(new Color(210, 145, 188));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        submitButton.setBorder(BorderFactory.createEtchedBorder());
        submitButton.addActionListener(this);
        askWeightPanel.add(submitButton);

        // Main Panel
        mainContainerPanel.setBackground(new Color(129, 104, 157));
        mainContainerPanel.setBounds(0, 50, 1270, 850);

        // Frame settings
        this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500, 900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        navigationPanel.add(label);
        this.add(navigationPanel);
        this.add(askWeightPanel);
        this.add(mainContainerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                double weight = Double.parseDouble(weightTextField.getText());
                if (weight < 1 || weight > 15) {
                    ImageIcon icon = new ImageIcon("Windows\\pictures\\download (2).jfif"); 
                    Image scaledImage = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);

                    JOptionPane.showMessageDialog(this, "Invalid weight. Please enter a value between 1 and 15 kilos.", "Invalid Weight", JOptionPane.WARNING_MESSAGE, scaledIcon);
                } else {
                    List<Product> selectedProducts = selectProductsForWeight(weight);
                    if (selectedProducts.isEmpty()) {
                        ImageIcon icon = new ImageIcon("Windows\\\\pictures\\\\download (1).jfif"); 
                        Image scaledImage = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        JOptionPane.showMessageDialog(this, "No products found for the given weight.", "No Products", JOptionPane.WARNING_MESSAGE, scaledIcon);
                    } else {
                        displayProducts(selectedProducts);
                    }
                }
            } catch (NumberFormatException ex) {
                ImageIcon icon = new ImageIcon("Windows\\pictures\\download (4).jfif"); 
                Image scaledImage = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH); 
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Numbers only!", JOptionPane.WARNING_MESSAGE, scaledIcon);
            }
        }
    }

    private void displayProducts(List<Product> products) {
        mainContainerPanel.removeAll();

        JPanel labelPanel = new JPanel(new GridLayout(1, 3));
        labelPanel.setBackground(new Color(129, 104, 157));

        JLabel productLabel = new JLabel("Product");
        productLabel.setForeground(new Color(255, 228, 201));
        productLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        labelPanel.add(productLabel);

        JLabel weightLabel = new JLabel("Weight/Box");
        weightLabel.setForeground(new Color(255, 228, 201));
        weightLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        labelPanel.add(weightLabel);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setForeground(new Color(255, 228, 201));
        amountLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        labelPanel.add(amountLabel);

        labelPanel.setBounds(150, 0, 1270, 50);
        mainContainerPanel.add(labelPanel);

        int yPosition = 70;

        for (Product product : products) {
            // Display product information
            JPanel productPanel = new JPanel();
            productPanel.setLayout(null);
            productPanel.setBackground(new Color(255, 230, 230));
            productPanel.setBounds(90, yPosition, 200, 200);
            Border productPanelBorder = BorderFactory.createLineBorder(new Color(210, 145, 188), 1);
            productPanel.setBorder(productPanelBorder);
            mainContainerPanel.add(productPanel);

            ImageIcon productImageIcon = new ImageIcon(product.imagePath);
            Image productImage = productImageIcon.getImage().getScaledInstance(190, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledProductImageIcon = new ImageIcon(productImage);

            JLabel productImageLabel = new JLabel(scaledProductImageIcon);
            productImageLabel.setBounds(0, 0, 200, 160);
            productPanel.add(productImageLabel);

            JPanel productPanelName = new JPanel();
            productPanelName.setBounds(0, 160, 200, 40);
            Border productBorder = BorderFactory.createLineBorder(new Color(210, 145, 188), 1);
            productPanelName.setBorder(productBorder);
            productPanel.add(productPanelName);

            JLabel productPanelText = new JLabel(product.name);
            productPanelText.setForeground(new Color(129, 104, 157));
            productPanelText.setFont(new Font("Monospaced", Font.BOLD, 20));
            productPanelText.setBounds(60, 210, 100, 20);
            productPanelName.add(productPanelText);

            //for weight
            JPanel weightPanel = new JPanel(null); // Use null layout to position the text manually
            weightPanel.setBounds(530, yPosition + 70, 200, 40);
            mainContainerPanel.add(weightPanel);

            JLabel weightPanelText = new JLabel(String.valueOf(product.weight)); // Convert double to String
            weightPanelText.setForeground(new Color(129, 104, 157));
            weightPanelText.setFont(new Font("Monospaced", Font.BOLD, 20));

            int textWidthWeight = weightPanelText.getPreferredSize().width;
            int textHeightWeight = weightPanelText.getPreferredSize().height;
            int panelWidthWeight = weightPanel.getWidth();
            int panelHeightWeight = weightPanel.getHeight();
            int xWeight = (panelWidthWeight - textWidthWeight) / 2;
            int yWeight = (panelHeightWeight - textHeightWeight) / 2;

            weightPanelText.setBounds(xWeight, yWeight, textWidthWeight, textHeightWeight);
            weightPanel.add(weightPanelText);

            //for amount
            JPanel amountPanel = new JPanel(null); 
            amountPanel.setBounds(940, yPosition + 70, 200, 40);
            mainContainerPanel.add(amountPanel);

            JLabel amountPanelText = new JLabel(String.valueOf(product.amount));
            amountPanelText.setForeground(new Color(129, 104, 157));
            amountPanelText.setFont(new Font("Monospaced", Font.BOLD, 20));

            int textWidthAmount = amountPanelText.getPreferredSize().width;
            int textHeightAmount = amountPanelText.getPreferredSize().height;
            int panelWidthAmount = amountPanel.getWidth();
            int panelHeightAmount = amountPanel.getHeight();
            int xAmount = (panelWidthAmount - textWidthAmount) / 2;
            int yAmount = (panelHeightAmount - textHeightAmount) / 2;

            amountPanelText.setBounds(xAmount, yAmount, textWidthAmount, textHeightAmount);
            amountPanel.add(amountPanelText);
            yPosition += 250;
        }

        double totalAmount = calculateTotalAmount(products);
        displayTotalAmount(totalAmount);

        mainContainerPanel.revalidate();
        mainContainerPanel.repaint();
    }

    private double calculateTotalAmount(List<Product> products) {
        double totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getAmount();
        }
        return totalAmount;
    }

    private void displayTotalAmount(double totalAmount) {
        JPanel totalAmountPanel = new JPanel();
        totalAmountPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        totalAmountPanel.setBackground(new Color(255, 230, 230));
    
        totalAmountPanel.setBounds(18, 730, 180, 130); 
        Border amountPanelBorder = BorderFactory.createLineBorder(new Color(210, 145, 188), 1);
        totalAmountPanel.setBorder(amountPanelBorder);
        navigationPanel.add(totalAmountPanel); 
    
        JLabel totalAmountTextLabel = new JLabel("TOTAL AMOUNT:");
        totalAmountTextLabel.setForeground(new Color(129, 104, 157));
        totalAmountTextLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        totalAmountPanel.add(totalAmountTextLabel);
    
        JLabel totalAmountLabel = new JLabel(String.format("%.2f  ", totalAmount));
        totalAmountLabel.setForeground(new Color(129, 104, 157));
        totalAmountLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
    
        int fontSize = Math.min(16, 100 / totalAmountLabel.getText().length()); 
        totalAmountLabel.setFont(new Font("Monospaced", Font.BOLD, fontSize)); 
        totalAmountPanel.add(totalAmountLabel); 
    }
    static class Product {
        String name;
        double weight;
        int amount;
        String imagePath;

        Product(String name, double weight, int amount, String imagePath) {
            this.name = name;
            this.weight = weight;
            this.amount = amount;
            this.imagePath = imagePath;
        }
        public int getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = (int)amount;
        }
    }

    static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("Canned Goods", 5, 450, "Windows\\pictures\\Rice rat Can Organizer for Pantry, Can Rack Can Storage Dispenser for Canned Food (5 tiers).jfif"));
        products.add(new Product("Cooking Oil", 3, 725, "Windows\\pictures\\Substituting Oil for Butter in Recipes.jfif"));
        products.add(new Product("Noodles", 2.5, 375, "Windows\\pictures\\download.jfif"));
        products.add(new Product("Soap", 7, 500, "Windows\\pictures\\Untitled.jfif"));
    }

    static List<Product> selectProductsForWeight(double weight) {
        List<Product> selectedProducts = new ArrayList<>();
        int n = products.size();
        int max = (1 << n); 
        double closestWeightDiff = Double.MAX_VALUE;
        double bestWeight = 0;

        for (int i = 0; i < max; i++) {
            List<Product> combination = new ArrayList<>();
            double totalWeight = 0;
            int totalAmount = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    Product product = products.get(j);
                    combination.add(product);
                    totalWeight += product.weight;
                    totalAmount += product.amount;
                }
            }

            if (totalWeight <= weight) {
                double diff = Math.abs(weight - totalWeight);
                if (diff < closestWeightDiff) {
                    closestWeightDiff = diff;
                    bestWeight = totalWeight;
                    selectedProducts = combination;
                }
            }
        }

        return selectedProducts;
    }
}

