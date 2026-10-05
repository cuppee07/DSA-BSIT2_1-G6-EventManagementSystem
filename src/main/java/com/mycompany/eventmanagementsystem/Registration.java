/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rowena
 */
public class Registration extends JFrame{
    
    private JComboBox<String> eventBox;
    private JTable table;
    private JTextField  idField, eventField, nameField, emailField;
    private JLabel title, title2, title3, title4, att, att2, ARS, TS, event, event2, name, email;
    private DefaultTableModel tableModel;
    private JPanel panel, panel2, panel3, panel4;
 
    
    Registration (){
        
        
        setTitle("Event Management System");
        setSize(1000,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel panel = new JPanel();
panel.setLayout(null);
panel.setBorder(BorderFactory.createTitledBorder(""));
panel.setBounds(20, 20, 450, 265);

JLabel title = new JLabel("Register Attendee");
title.setBounds(25, 15, 115, 30);

JLabel event = new JLabel("Event:");
event.setBounds(30, 50, 100, 30);

JComboBox eventBox = new JComboBox();
eventBox.addItem("Select Event");
eventBox.setBounds(120, 50, 290, 30);

JLabel att = new JLabel("Attendee ID:");
att.setBounds(30, 85, 100, 30);

JTextField idField = new JTextField();
idField.setBounds(120, 85, 290, 30);

JLabel name = new JLabel("Name:");
name.setBounds(30, 120, 100, 30);

JTextField nameField = new JTextField();
nameField.setBounds(120, 120, 290, 30);

JLabel email = new JLabel("Email:");
email.setBounds(30, 153, 100, 30);

JTextField emailField = new JTextField();
emailField.setBounds(120, 155, 290, 30);

JButton registerButton = new JButton("Register");
registerButton.setBounds(30, 200, 150, 40);

JButton clearButton = new JButton("Clear");
clearButton.setBounds(260, 200, 150, 40);


panel.add(title);
panel.add(event);
panel.add(name);
panel.add(nameField);
panel.add(idField);
panel.add(eventBox);
panel.add(att);
panel.add(email);
panel.add(emailField);
panel.add(registerButton);
panel.add(clearButton);

add(panel);


// PANEL 2

JPanel panel2 = new JPanel();
panel2.setLayout(null);
panel2.setBorder(BorderFactory.createTitledBorder(""));
panel2.setBounds(20, 320, 450, 265);


JLabel title2 = new JLabel("Registration Result");
title2.setBounds(25, 15, 115, 30);

JLabel ARS = new JLabel("Attendee registered successfully!");
ARS.setBounds(25, 50, 200, 50);

JLabel event2 = new JLabel("Event: ");
event2.setBounds(25, 100, 100, 30);

JLabel att2 = new JLabel("Attendee: ");
att2.setBounds(25, 140, 100, 30);


panel2.add(title2);
panel2.add(ARS);
panel2.add(event2);
panel2.add(att2);

add(panel2);
        

JPanel panel3 = new JPanel();
panel3.setLayout(null);
panel3.setBorder(BorderFactory.createTitledBorder(""));
panel3.setBounds(510, 20, 450, 185);

JLabel title3 = new JLabel("Event Capacity");
title3.setBounds(25, 15, 115, 30);


panel3.add(title3);

add(panel3);

JPanel panel4 = new JPanel();
panel4.setLayout(null);
panel4.setBorder(BorderFactory.createTitledBorder(""));
panel4.setBounds(510, 220, 450, 360);

JLabel title4 = new JLabel("Waiting List");
title4.setBounds(25, 15, 115, 30);


panel4.add(title4);

add(panel4);

    }
    
}
