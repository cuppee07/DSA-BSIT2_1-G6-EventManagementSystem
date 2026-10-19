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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JTextField userTextField;
    private JPasswordField passTextField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;
    private JButton signupLinkButton;

    public Login() {
        setTitle("EventEase - Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 255, 255));
        add(mainPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(243, 239, 249)); 
        leftPanel.setBounds(0, 0, 450, 600);
        mainPanel.add(leftPanel);
        
        int iconWidth = 120;
        int iconHeight = 120;
        
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

        JLabel titleLabel = new JLabel("EventEase", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(40, 30, 60));
        titleLabel.setBounds(100, 275, 250, 40);
        leftPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Plan  •  Register  •  Attend  •  Succeed", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(100, 90, 120));
        subtitleLabel.setBounds(75, 325, 300, 25);
        leftPanel.add(subtitleLabel);

        JLabel formTitle = new JLabel("Welcome Back!");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        formTitle.setForeground(new Color(40, 30, 60));
        formTitle.setBounds(500, 100, 250, 30);
        mainPanel.add(formTitle);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(new Color(80, 80, 80));
        userLabel.setBounds(500, 175, 100, 30);
        mainPanel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userTextField.setBounds(610, 175, 220, 35);
        mainPanel.add(userTextField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passLabel.setForeground(new Color(80, 80, 80));
        passLabel.setBounds(500, 240, 100, 30);
        mainPanel.add(passLabel);

        passTextField = new JPasswordField();
        passTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passTextField.setBounds(610, 240, 220, 35);
        mainPanel.add(passTextField);
        
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPasswordCheckBox.setBackground(new Color(255, 255, 255));
        showPasswordCheckBox.setFocusPainted(false);
        showPasswordCheckBox.setBounds(610, 280, 220, 25);
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passTextField.setEchoChar((char) 0); 
            } else {
                passTextField.setEchoChar('•');      
            }
        });
        mainPanel.add(showPasswordCheckBox);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(114, 91, 155));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(500, 320, 330, 40);
        loginButton.addActionListener(this);
        mainPanel.add(loginButton);

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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText().trim();
            String password = new String(passTextField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedPassword = DatabaseConnection.hashPassword(password);
                
            try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?")) {
                
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    
                pst.setString(1, username);
                pst.setString(2, hashedPassword);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role");
                        dispose();

                        if ("Admin".equalsIgnoreCase(role)) {
                            new Dashboard().setVisible(true); 
                        } 
                        else { 
                            // new Registration().setVisible(true);
                        }
                    } 
                    else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } 
            catch (Exception ex) {
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