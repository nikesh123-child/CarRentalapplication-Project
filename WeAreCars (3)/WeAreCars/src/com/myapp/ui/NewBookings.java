package com.myapp.ui;

import com.myapp.utils.DBHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class NewBookings extends JFrame {
    private JTextField firstNameField, lastNameField, addressField;
    private JFormattedTextField ageField;
    private JCheckBox drivingLicenseCheck;
    private JComboBox<String> carTypeCombo, fuelTypeCombo, extrasCombo;
    private JSpinner daysSpinner;
    private JTextArea summaryArea;
    double totalPrice = 0;

    public NewBookings() {
        setTitle("WeAreCars");
        setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createTitledBorder("Booking Form"));

        // Form fields
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        addressField = new JTextField(15);

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        ageField = new JFormattedTextField(format);
        ageField.setColumns(5);

        drivingLicenseCheck = new JCheckBox("Yes");

        carTypeCombo = new JComboBox<>(new String[] {
                "City Car (No additional charge)",
                "Family Car (+£50)",
                "Sports Car (+£75)",
                "SUV (+£65)"
        });

        fuelTypeCombo = new JComboBox<>(new String[] {
                "Petrol (No extra cost)",
                "Diesel (No extra cost)",
                "Hybrid (+£30)",
                "Full (+£50)"
        });

        extrasCombo = new JComboBox<>(new String[] {
                "None",
                "Unlimited Mileage (+£10 per day)",
                "Breakdown Cover (+£2 per day)"
        });

        daysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 28, 1));

        JButton bookButton = new JButton("BOOK A CAR");

        // Adding fields to the form panel
        addField(formPanel, "First Name:", firstNameField);
        addField(formPanel, "Last Name:", lastNameField);
        addField(formPanel, "Address:", addressField);
        addField(formPanel, "Age (Numbers only):", ageField);
        addField(formPanel, "Valid Driving License?", drivingLicenseCheck);
        addField(formPanel, "Select Car Type:", carTypeCombo);
        addField(formPanel, "Select Fuel Type:", fuelTypeCombo);
        addField(formPanel, "Select Optional Extras:", extrasCombo);
        addField(formPanel, "Rental Days (1-28):", daysSpinner);

        formPanel.add(bookButton);


        // Sidebar (Summary Panel)
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Booking Summary"));
        summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        mainPanel.add(formPanel);
        mainPanel.add(summaryPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Add listeners for real-time summary updates
        addListeners();

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateForm();
            }
        });
        centerWindow();
    }

    private void addField(JPanel panel, String label, JComponent field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    private void addListeners() {
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { updateSummary(); }
            @Override
            public void removeUpdate(DocumentEvent e) { updateSummary(); }
            @Override
            public void changedUpdate(DocumentEvent e) { updateSummary(); }
        };

        firstNameField.getDocument().addDocumentListener(documentListener);
        lastNameField.getDocument().addDocumentListener(documentListener);
        addressField.getDocument().addDocumentListener(documentListener);
        ageField.getDocument().addDocumentListener(documentListener);

        drivingLicenseCheck.addActionListener(e -> updateSummary());
        carTypeCombo.addActionListener(e -> updateSummary());
        fuelTypeCombo.addActionListener(e -> updateSummary());
        extrasCombo.addActionListener(e -> updateSummary());
        daysSpinner.addChangeListener(e -> updateSummary());
    }

    private void updateSummary() {

        String carType = (String) carTypeCombo.getSelectedItem();
        if (carType.equals("City Car (No additional charge)")) {
            totalPrice = 0;
        } else if (carType.equals("Family Car (+£50)")) {
            totalPrice = 50;
        } else if (carType.equals("Sports Car (+£75)")) {
            totalPrice = 75;
        } else if (carType.equals("SUV (+£65)")) {
            totalPrice = 65;
        }

        // Fuel type prices
        String fuelType = (String) fuelTypeCombo.getSelectedItem();
        if (fuelType.equals("Hybrid (+£30)")) {
            totalPrice += 30;
        } else if (fuelType.equals("Full Electric(+£50)")) {
            totalPrice += 50;
        }

        // Extras
        String extras = (String) extrasCombo.getSelectedItem();
        if (extras.equals("Unlimited Mileage (+£10 per day)")) {
            totalPrice += 10 * (int) daysSpinner.getValue();
        } else if (extras.equals("Breakdown Cover (+£2 per day)")) {
            totalPrice += 2 * (int) daysSpinner.getValue();
        }

        // Rental days
        totalPrice *= (int) daysSpinner.getValue();

        String summary = "Customer Name: " + firstNameField.getText() + " " + lastNameField.getText() + "\n"
                + "Address: " + addressField.getText() + "\n"
                + "Age: " + ageField.getText() + "\n"
                + "Driving License: " + (drivingLicenseCheck.isSelected() ? "Yes" : "No") + "\n"
                + "Car Type: " + carTypeCombo.getSelectedItem() + "\n"
                + "Fuel Type: " + fuelTypeCombo.getSelectedItem() + "\n"
                + "Rental Days: " + daysSpinner.getValue() + "\n"
                + "Optional Extras: " + extrasCombo.getSelectedItem() + "\n"
                + "Total Price: £" + totalPrice;

        summaryArea.setText(summary);
    }

    private void validateForm() {
        if (firstNameField.getText().trim().isEmpty()) {
            showError("Please enter your First Name!");
            firstNameField.requestFocus();
            return;
        }
        if (lastNameField.getText().trim().isEmpty()) {
            showError("Please enter your Last Name!");
            lastNameField.requestFocus();
            return;
        }
        if (addressField.getText().trim().isEmpty()) {
            showError("Please enter your Address!");
            addressField.requestFocus();
            return;
        }
        if (ageField.getText().trim().isEmpty()) {
            showError("Please enter your Age!");
            ageField.requestFocus();
            return;
        }

        try {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 18) {
                showError("You must be at least 18 years old to book!");
                ageField.requestFocus();
                return;
            }

            // Age restriction for car booking
            if (age < 21 && !carTypeCombo.getSelectedItem().equals("City Car (No additional charge)")) {
                showError("Users under 21 can only book City Car!");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Please enter a valid Age (numbers only)!");
            ageField.requestFocus();
            return;
        }

        if (!drivingLicenseCheck.isSelected()) {
            showError("You must have a valid Driving License to book a car!");
            return;
        }


        // Insert into database
        DBHelper dbHelper = new DBHelper();
        boolean success = dbHelper.insertBooking(
                firstNameField.getText().trim(),
                lastNameField.getText().trim(),
                addressField.getText().trim(),
                Integer.parseInt(ageField.getText().trim()),
                drivingLicenseCheck.isSelected(),
                (String) carTypeCombo.getSelectedItem(),
                (String) fuelTypeCombo.getSelectedItem(),
                (String) extrasCombo.getSelectedItem(),
                (int) daysSpinner.getValue(),
                totalPrice
        );



        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Booking submitted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            Rentals rentals = new Rentals();
            rentals.setVisible(true);
            dispose();
         // Optional: Add a method to reset form fields
        } else {
            showError("Failed to save booking to database.");
        }
        updateSummary();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NewBookings frame = new NewBookings();
            frame.setVisible(true);
        });
    }
}
