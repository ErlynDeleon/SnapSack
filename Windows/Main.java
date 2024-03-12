package Windows;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
            LandingWindow landingWindow = new LandingWindow();
            landingWindow.setVisible(true);
        });
  }
}