/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JTextField userTextField;
    private JPasswordField passTextField;
    private JButton loginButton;
    private JButton signupLinkButton;

    public Login() {
        // Window Configuration
        setTitle("EventEase - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main background panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 255, 255));
        add(mainPanel);

        // --- LEFT HALF PANEL (Branding Section) ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(243, 239, 249)); // Soft purple branding background
        leftPanel.setBounds(0, 0, 450, 600);
        mainPanel.add(leftPanel);
        
        int iconWidth = 120;
        int iconHeight = 120;
        
        // PNG Icon Label Setup
        JLabel iconLabel = new JLabel();
        iconLabel.setBounds(165, 135, iconWidth, iconHeight);
        
        URL imgURL = getClass().getResource("/icons/eventplan.png");
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image scaledImage = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(scaledImage));
            iconLabel.setHorizontalAlignment(JLabel.CENTER);
        } else {
            iconLabel.setText("[Icon Not Found]");
            iconLabel.setHorizontalAlignment(JLabel.CENTER);
            iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            iconLabel.setForeground(new Color(114, 91, 155));
        }
        leftPanel.add(iconLabel);

        // Title Label ("EventEase")
        JLabel titleLabel = new JLabel("EventEase", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(40, 30, 60));
        titleLabel.setBounds(100, 275, 250, 40);
        leftPanel.add(titleLabel);

        // Subtitle / Steps Label
        JLabel subtitleLabel = new JLabel("Plan  •  Register  •  Attend  •  Succeed", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(100, 90, 120));
        subtitleLabel.setBounds(75, 325, 300, 25);
        leftPanel.add(subtitleLabel);


        // --- RIGHT HALF PANEL (Login Form Section) ---

        JLabel formTitle = new JLabel("Welcome Back");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        formTitle.setForeground(new Color(40, 30, 60));
        formTitle.setBounds(500, 100, 250, 30);
        mainPanel.add(formTitle);

        // Username Label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(new Color(80, 80, 80));
        userLabel.setBounds(500, 175, 100, 30);
        mainPanel.add(userLabel);

        // Username Field
        userTextField = new JTextField();
        userTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userTextField.setBounds(610, 175, 220, 35);
        mainPanel.add(userTextField);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setForeground(new Color(80, 80, 80));
        passLabel.setBounds(500, 240, 100, 30);
        mainPanel.add(passLabel);

        // Password Field
        passTextField = new JPasswordField();
        passTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passTextField.setBounds(610, 240, 220, 35);
        mainPanel.add(passTextField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(114, 91, 155));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(500, 320, 330, 40);
        loginButton.addActionListener(this);
        mainPanel.add(loginButton);

        // Don't have an account? Sign Up Link Button
        signupLinkButton = new JButton("<html>Don't have an account? <b>Sign up</b></html>");
        signupLinkButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        signupLinkButton.setBorderPainted(false);
        signupLinkButton.setContentAreaFilled(false);
        signupLinkButton.setFocusPainted(false);
        signupLinkButton.setForeground(new Color(114, 91, 155));
        signupLinkButton.setBounds(500, 380, 330, 30);
        signupLinkButton.addActionListener(this);
        mainPanel.add(signupLinkButton);
        
    }
    
    public String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    // --- ACTION LISTENER METHOD ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText().trim();
            String password = new String(passTextField.getPassword());
            String hashedPassword = hashPassword(password);

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Database connection settings
            String url = "jdbc:mysql://localhost:3306/event_db";
            String dbUser = "root";
            String dbPass = "*Cuppee07*"; 

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
                     PreparedStatement pst = conn.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?")) {
                    
                    pst.setString(1, username);
                    pst.setString(2, hashedPassword);

                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String role = rs.getString("role");
                        dispose();

                        // Role-based routing
                        if ("Admin".equalsIgnoreCase(role)) {
                            new Dashboard().setVisible(true); // Route to Admin Dashboard (make sure Dashboard.java exists)
                            // new Dashboard().setVisible(true);
                        } else {
                            // Route to Attendee Registration page (make sure Registration.java exists)
                            // new Registration().setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } 
        else if (e.getSource() == signupLinkButton) {
            dispose();
            new Signup().setVisible(true);
        }
    }
}