package Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ExitWindow extends JFrame implements ActionListener{
    JLabel label = new JLabel();

    //title panel
    JLabel titleText = new JLabel("Programmers...");
    JPanel titlePanel = new JPanel();

    //main panel
    JPanel mainPanel = new JPanel();
    static List<Programmers> programmers = new ArrayList<>();

    //button exit panel
    JPanel exitPanel = new JPanel();
    JButton exitButton = new JButton("Exit");

    ExitWindow() {
        //title panel
        titlePanel.setVisible(true);
        titlePanel.setBounds(0, 0, 800, 80);
        titlePanel.setBackground(new Color(226, 110, 229));
        this.add(titlePanel);

        titleText.setBounds(400, 60, 200, 60);
        titleText.setForeground(new Color(126, 48, 225));
        titleText.setFont(new Font("Monospaced", Font.BOLD, 50));
        titlePanel.add(titleText);

        //mainPanel
        mainPanel.setBounds(0, 80, 800, 900); 
        mainPanel.setLayout(null);

        int panelWidth = 700;
        int panelHeight = 170; 
        int gap = 20;
        int startX = 50;
        int startY = 40;

        for (Programmers programmer : programmers) {
            JPanel programmerPanel = new JPanel(new GridLayout(1, 2));
            programmerPanel.setBackground(new Color(73, 16, 139));
            programmerPanel.setBounds(startX, startY, panelWidth, panelHeight);
            mainPanel.add(programmerPanel);

            // Picture Panel
            JPanel picturePanel = new JPanel();
            picturePanel.setLayout(null);
            picturePanel.setBounds(0, 0, panelWidth / 2, panelHeight);
            picturePanel.setBackground(new Color(73, 16, 139)); 
            programmerPanel.add(picturePanel);

            ImageIcon imageIcon = new ImageIcon(programmer.imagePath);
            Image image = imageIcon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH); 
            ImageIcon scaledImageIcon = new ImageIcon(image);

            JLabel pictureLabel = new JLabel(scaledImageIcon);
            pictureLabel.setBounds(20, 15, 140, 140); 
            picturePanel.add(pictureLabel);

            // Name and Role Panel
            JPanel nameRolePanel = new JPanel();
            nameRolePanel.setLayout(null); 
            nameRolePanel.setBounds(panelWidth / 2, 0, panelWidth / 2, panelHeight);
            nameRolePanel.setBackground(new Color(73, 16, 139));
            programmerPanel.add(nameRolePanel);

            JLabel nameLabel = new JLabel(programmer.fullName);
            nameLabel.setForeground(new Color(226, 110, 229));
            nameLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
            nameLabel.setBounds(10, 10, panelWidth / 2 - 20, 30); 
            nameRolePanel.add(nameLabel);

            String[] roles = programmer.role.split(", "); 

            // Main Role Label
            JLabel mainRoleLabel = new JLabel(roles[0]); 
            mainRoleLabel.setForeground(new Color(226, 110, 229));
            mainRoleLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
            mainRoleLabel.setBounds(10, 60, panelWidth / 2 - 20, 20); 
            nameRolePanel.add(mainRoleLabel);

            // Secondary Role Label
            if (roles.length > 1) { 
                JLabel secondaryRoleLabel = new JLabel(roles[1]); 
                secondaryRoleLabel.setForeground(new Color(226, 110, 229));
                secondaryRoleLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
                secondaryRoleLabel.setBounds(10, 90, panelWidth / 2 - 20, 20); 
                nameRolePanel.add(secondaryRoleLabel);
            }

          startY += panelHeight + gap;
        }

        exitPanel.setBounds(0, 820, 800, 70);
        exitPanel.setBackground(new Color(226, 110, 229));
        mainPanel.add(exitPanel);

        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(210, 145, 188));
        exitButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(149, 125, 173), 3));
        exitButton.setPreferredSize(new Dimension(200, 50));
        Dimension buttonSize = exitButton.getPreferredSize();

        int buttonX = (exitPanel.getWidth() - buttonSize.width) / 2;
        int buttonY = (exitPanel.getHeight() - buttonSize.height) / 2;
        
        exitButton.setBounds(buttonX, buttonY, buttonSize.width, buttonSize.height);
        exitButton.addActionListener(this);
        exitPanel.add(exitButton);

        // Frame settings
        this.add(label);
        ImageIcon icon = new ImageIcon("Windows\\pictures\\1-removebg-preview.png");
        this.setIconImage(icon.getImage());
        this.setTitle("SnapSack");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(243, 248, 255));
        this.setResizable(false);
        this.setSize(800, 1000);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(mainPanel);
    }

    static class Programmers {
        String imagePath;
        String fullName;
        String role;

        Programmers(String imagePath, String fullName, String role) {
            this.imagePath = imagePath;
            this.fullName = fullName;
            this.role = role;
        }
    }

    static {
        programmers.add(new Programmers("Windows\\pictures\\jhoana.jpeg", "Jhoana Decarla Barrameda", "Technical Writer, Algorithm Developer for No. 4"));
        programmers.add(new Programmers("Windows\\pictures\\erlyn.jpeg", "Erlyn Queen De Leon","Project and Layout Manager, Algorithm Developer for No. 1"));
        programmers.add(new Programmers("Windows\\pictures\\lyrine.jpeg", "Lyrine Poliarco", "Researcher, Algorithm Developer for No. 2"));
        programmers.add(new Programmers("Windows\\pictures\\angelica.jpeg", "Angelica Toquero", "Tester, Algorithm Developer for No. 3"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == exitButton) {
        System.exit(0);
      }
    }
}