package com.myapp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPage extends JFrame{
    public SelectionPage(){
        setTitle("WeAreCars");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the background panel
        MainFrame.BackgroundPanel backgroundPanel = new MainFrame.BackgroundPanel("src/resources/background.png");
        backgroundPanel.setLayout(new GridBagLayout());
        add(backgroundPanel);

        //Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,80,10));
        buttonPanel.setBackground(new Color(0,0,0,0));
//
        backgroundPanel.add(buttonPanel);

        JButton newBookingButton = new JButton("New Bookings");
        newBookingButton.setFont(new Font("Arial", Font.BOLD, 16));
        newBookingButton.setBackground(new Color(82, 222, 241, 181)); // Semi-transparent white
        newBookingButton.setPreferredSize(new Dimension(150,120 ));
        buttonPanel.add(newBookingButton);

        JButton newRentalButton= new JButton("Rentals");
        newRentalButton.setFont(new Font("Arial", Font.BOLD, 16));
        newRentalButton.setBackground(new Color(82, 222, 241, 181)); // Semi-transparent white
        newRentalButton.setPreferredSize(new Dimension(150,120 ));
        buttonPanel.add(newRentalButton);




        // Add panel to frame


        // Make frame visible

// Add ActionListener to Login Button
        newBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Link to another Frame
                NewBookings newBookings = new NewBookings();
                newBookings.setVisible(true);
                dispose();
            }
        });
        newRentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Link to another Frame
                Rentals rentals = new Rentals();
                rentals.setVisible(true);
                dispose();
            }
        });

// Add components to login panel


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
        new SelectionPage();
    }
}

