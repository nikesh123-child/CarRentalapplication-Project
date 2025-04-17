package com.myapp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffLogin extends JFrame {
    private static final String VALID_USERNAME = "sta001";
    private static final String VALID_PASSWORD = "givemethekeys123";
    public StaffLogin() {
        setTitle("WeAreCars");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create main panel with border layout
//        JPanel mainPanel = new JPanel(new BorderLayout(1, 1));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 20));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 4, 30));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Email/Username field
        JPanel usernamePanel = new JPanel(new BorderLayout(10, 10));
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField("");
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField);
        formPanel.add(usernamePanel);

        // Password field
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 10));
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField("");
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField);
        formPanel.add(passwordPanel);

        // Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(82, 222, 241, 181));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        formPanel.add(loginButton, BorderLayout.SOUTH);


        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Here you would typically validate the credentials
                if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                    // Successful login
                    JOptionPane.showMessageDialog(StaffLogin.this,
                            "Login successful! Access granted.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    SelectionPage newSelectionPage = new SelectionPage();
                    newSelectionPage.setVisible(true);
                } else {
                    // Failed login
                    JOptionPane.showMessageDialog(StaffLogin.this,
                            "Access denied. Invalid credentials",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);

                    // Clear the password field
                    passwordField.setText("");
                    usernameField.requestFocus();
                }

            }
        });
        add(formPanel, BorderLayout.SOUTH);
        setVisible(true);

    }
        public static void main (String[]args){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    StaffLogin loginFrame = new StaffLogin();
                    loginFrame.setVisible(true);
                }
            });
        }
    }
