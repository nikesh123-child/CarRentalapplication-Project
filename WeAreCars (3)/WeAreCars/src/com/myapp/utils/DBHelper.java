package com.myapp.utils;  // Adjust package as needed

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/wearecars";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual password

    private Connection connection;

    public Connection getConnection() {
        try {
            // Load MySQL JDBC driver (not required for JDBC 4.0+, but kept for compatibility)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected successfully.");
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return null;
    }



        public boolean insertBooking(String firstName, String lastName, String address, int age,
                                 boolean hasLicense, String carType, String fuelType,
                                 String extras, int days,double totalPrice) {
                 String sql = "INSERT INTO newbookings (firstname, lastname, address, age, driving_license, " +
                "car_type, fuel_type, extras, rental_days, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setInt(4, age);
            stmt.setBoolean(5, hasLicense);
            stmt.setString(6, carType);
            stmt.setString(7, fuelType);
            stmt.setString(8, extras);
            stmt.setInt(9, days);
            stmt.setDouble(10, totalPrice);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting booking: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> getAllRentals() {
        List<String[]> rentals = new ArrayList<>();
        String sql = "SELECT firstname, lastname, address, age, driving_license, " +
                "rental_days, car_type, fuel_type, total_price FROM newbookings";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String[] rental = new String[9]; // 8 data fields + actions
                rental[0] = rs.getString("firstname") + " " + rs.getString("lastname");
                rental[1] = rs.getString("address");
                rental[2] = String.valueOf(rs.getInt("age"));
                rental[3] = rs.getBoolean("driving_license") ? "Yes" : "No";
                rental[4] = String.valueOf(rs.getInt("rental_days"));
                rental[5] = rs.getString("car_type");
                rental[6] = rs.getString("fuel_type");
                rental[7] = "£" + String.format("%.2f", rs.getDouble("total_price"));
                rental[8] = "✔"; // Action column

                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching rentals: " + e.getMessage());
        }
        return rentals;
    }

    // Optional: Utility method to show error dialogs (if using Swing/JavaFX)
    private void showError(String message) {
        // Example for Swing:
        // JOptionPane.showMessageDialog(null, message, "Database Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("ERROR: " + message);
    }
}