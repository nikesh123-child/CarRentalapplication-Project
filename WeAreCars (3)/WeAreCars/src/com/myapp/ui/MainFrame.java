package com.myapp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("WeAreCars");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/resources/background.jpg");
        backgroundPanel.setLayout(new GridBagLayout());

        add(backgroundPanel);
        // Create login panel (centered box)

        // Create a Login Button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setLocation(loginButton.getX(), loginButton.getY() - 20);
        loginButton.setBackground(new Color(253, 253, 253, 255)); // Semi-transparent white
        loginButton.setPreferredSize(new Dimension(100,50 ));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for easy centering
        panel.add(loginButton, BorderLayout.CENTER); // Add button in the center of the panel

        // Add panel to frame
        add(panel);

        // Make frame visible

// Add ActionListener to Login Button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Link to another Frame
                StaffLogin staffLogin = new StaffLogin();
                staffLogin.setVisible(true);
                dispose();
            }
        });

// Add components to login panel
        panel.add(loginButton);
        backgroundPanel.add(panel);
        add(backgroundPanel);
        setVisible(true);
    }

    // Custom JPanel for background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String filePath) {
            backgroundImage = new ImageIcon(filePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
