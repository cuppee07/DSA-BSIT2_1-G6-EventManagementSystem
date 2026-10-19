/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Carine
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Signup extends JFrame implements ActionListener {

    private JComboBox<String> roleComboBox;
    private JTextField emailTextField;
    private JTextField userTextField;
    private JPasswordField passTextField;
    private JPasswordField confirmPassTextField;
    private JCheckBox showPasswordCheckBox;
    private JButton signupButton;
    private JButton loginLinkButton;

    public Signup() {
        setTitle("EventEase - Sign Up");
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

        JLabel formTitle = new JLabel("Create an Account");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        formTitle.setForeground(new Color(40, 30, 60));
        formTitle.setBounds(500, 45, 250, 30);
        mainPanel.add(formTitle);

        JLabel roleLabel = new JLabel("Register As:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roleLabel.setForeground(new Color(80, 80, 80));
        roleLabel.setBounds(500, 95, 100, 30);
        mainPanel.add(roleLabel);

        String[] roles = {"Attendee", "Admin"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roleComboBox.setBounds(630, 95, 200, 32);
        mainPanel.add(roleComboBox);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailLabel.setForeground(new Color(80, 80, 80));
        emailLabel.setBounds(500, 145, 100, 30);
        mainPanel.add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailTextField.setBounds(630, 145, 200, 32);
        mainPanel.add(emailTextField);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        userLabel.setForeground(new Color(80, 80, 80));
        userLabel.setBounds(500, 195, 100, 30);
        mainPanel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        userTextField.setBounds(630, 195, 200, 32);
        mainPanel.add(userTextField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passLabel.setForeground(new Color(80, 80, 80));
        passLabel.setBounds(500, 245, 100, 30);
        mainPanel.add(passLabel);

        passTextField = new JPasswordField();
        passTextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passTextField.setBounds(630, 245, 200, 32);
        mainPanel.add(passTextField);

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        confirmPassLabel.setForeground(new Color(80, 80, 80));
        confirmPassLabel.setBounds(500, 295, 110, 30);
        mainPanel.add(confirmPassLabel);

        confirmPassTextField = new JPasswordField();
        confirmPassTextField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        confirmPassTextField.setBounds(630, 295, 200, 32);
        mainPanel.add(confirmPassTextField);
        
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPasswordCheckBox.setBackground(new Color(255, 255, 255));
        showPasswordCheckBox.setFocusPainted(false);
        showPasswordCheckBox.setBounds(630, 332, 200, 25);
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passTextField.setEchoChar((char) 0);         
                confirmPassTextField.setEchoChar((char) 0);
            } else {
                passTextField.setEchoChar('•');             
                confirmPassTextField.setEchoChar('•');
            }
        });
        mainPanel.add(showPasswordCheckBox);
        
        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signupButton.setBackground(new Color(114, 91, 155));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setBounds(500, 360, 330, 40);
        signupButton.addActionListener(this);
        mainPanel.add(signupButton);

        loginLinkButton = new JButton("<html>Already have an account? <b>Log in</b></html>");
        loginLinkButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        loginLinkButton.setBorderPainted(false);
        loginLinkButton.setContentAreaFilled(false);
        loginLinkButton.setFocusPainted(false);
        loginLinkButton.setForeground(new Color(114, 91, 155));
        loginLinkButton.setBounds(500, 420, 330, 30);
        loginLinkButton.addActionListener(this);
        mainPanel.add(loginLinkButton);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String role = (String) roleComboBox.getSelectedItem();
            String email = emailTextField.getText().trim();
            String username = userTextField.getText().trim();
            String password = new String(passTextField.getPassword());
            String confirmPassword = new String(confirmPassTextField.getPassword());

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address containing '@'.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String hashedPassword = DatabaseConnection.hashPassword(password);
            try (Connection conn = DatabaseConnection.connect();
                PreparedStatement pst = conn.prepareStatement("INSERT INTO users (email, username, password, role) VALUES (?, ?, ?, ?)")) {
                
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                }
                
                pst.setString(1, email);
                pst.setString(2, username);
                pst.setString(3, hashedPassword);
                pst.setString(4, role);
                
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Account created successfully! Please log in.");
                    dispose();
                    new Login().setVisible(true);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == loginLinkButton) {
            dispose();
            new Login().setVisible(true);
        }
    }
}
