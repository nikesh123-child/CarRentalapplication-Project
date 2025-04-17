package com.myapp.ui;

import com.myapp.utils.DBHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Rentals extends JFrame {
    private JTable rentalTable;
    private DefaultTableModel tableModel;
    private DBHelper dbHelper;

    public Rentals() {
        setTitle("WeAreCars");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize database helper
        dbHelper = new DBHelper();

        // Create table model with columns
        String[] columns = {"Name", "Address", "Age", "License", "Days",
                "Car Type", "Fuel Type", "Total Price", "Actions"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };

        // Load data from database
        loadRentalData();

        // Create table
        rentalTable = new JTable(tableModel);
        rentalTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(rentalTable);

        // Pagination controls
        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        paginationPanel.add(new JLabel("Items per page:"));
        paginationPanel.add(new JComboBox<>(new String[]{"10", "25", "50"}));
        paginationPanel.add(new JLabel("1-" + tableModel.getRowCount() + " of " + tableModel.getRowCount()));

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(paginationPanel, BorderLayout.SOUTH);

        // Add Rental button
        JButton addButton = new JButton("Go Back");
        addButton.addActionListener(this::gobacktoselection);
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);

        // Add components to frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        centerWindow();
    }

    private void loadRentalData() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Fetch data from database
        List<String[]> rentals = dbHelper.getAllRentals();

        // Add data to table
        for (String[] rental : rentals) {
            tableModel.addRow(rental);
        }
    }
    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    private void gobacktoselection(ActionEvent e) {
        SelectionPage selectionPage = new SelectionPage();
        selectionPage.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Rentals system = new Rentals();
            system.setVisible(true);
        });
    }
}