/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;


import dsa.WaitingList;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/** 
 *
 * @author Rowena
 */
public class Registration extends JFrame{   
    private WaitingList waitingList = new WaitingList();
    private JComboBox<String> eventBox, eventBox1;
    private JTable table;
    private JTextField  idField, nameField, emailField;
    private DefaultTableModel tableModel;
    private JPanel panel, panel2, panel3, panel4;
    private JButton registerButton, clearButton;
    private JLabel resultMessage, resultEvent, resultAttendeeID, resultName;
// >>> ADDED CODE: Panel 3 capacity labels
private JLabel capacityEventLabel;
private JLabel capacityRegisteredLabel;
private JLabel capacityAvailableLabel;

// >>> ADDED CODE: Store registration counts separately for each event
private java.util.Map<String, Integer> registeredCounts =
        new java.util.HashMap<>();

// >>> ADDED CODE: Capacity for each event
private final int EVENT_CAPACITY = 1;

    
 
    Color darkBlue = new Color(35, 100, 165);
    Color lightBlue = new Color(220, 235, 250);
    Color veryLightBlue = new Color(240, 246, 253);
    Color white = Color.WHITE;
    Color darkText = new Color(35, 55, 80);
    Color lavender = new Color(230, 230, 250);    
    
    
    
    Registration (){
        
        
        setTitle("Event Management System");
        setSize(1000,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
panel = new JPanel();
panel.setLayout(null);
panel.setBorder(BorderFactory.createLineBorder(lightBlue));
panel.setBounds(20, 20, 450, 265);
panel.setBackground(veryLightBlue);

// Lavender header
JPanel header = new JPanel();
header.setLayout(null);
header.setBounds(0, 0, 450, 40);
header.setBackground(new Color(230, 230, 250));

JLabel title = new JLabel("Register Attendee");
title.setBounds(25, 5, 250, 35);
title.setForeground(darkText);
title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

header.add(title);
panel.add(header);

JLabel event = new JLabel("Event:");
event.setBounds(30, 50, 100, 30);


eventBox = new JComboBox();
eventBox.addItem("Tech Summit (EVT001)");
eventBox.addItem("Art Workshop (EVT002)");
eventBox.addItem("Business Forum (EVT003)");

eventBox.setBounds(120, 50, 290, 30);

JLabel att = new JLabel("Attendee ID:");
att.setBounds(30, 85, 100, 30);

idField = new JTextField();
idField.setBounds(120, 85, 290, 30);

JLabel name = new JLabel("Name:");
name.setBounds(30, 120, 100, 30);

nameField = new JTextField();
nameField.setBounds(120, 120, 290, 30);

JLabel email = new JLabel("Email:");
email.setBounds(30, 153, 100, 30);

emailField = new JTextField();
emailField.setBounds(120, 155, 290, 30);

registerButton = new JButton("Register");
registerButton.setBounds(30, 200, 150, 40);

clearButton = new JButton("Clear");
clearButton.setBounds(260, 200, 150, 40);


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

// PANEL 2

panel2 = new JPanel();
panel2.setLayout(null);
panel2.setBorder(BorderFactory.createLineBorder(lightBlue));
panel2.setBounds(490, 150, 470, 135);

// Lavender header
JPanel header2 = new JPanel();
header2.setLayout(null);
header2.setBounds(0, 0, 480, 40);
header2.setBackground(new Color(230, 230, 250));

JLabel title2 = new JLabel("Registration Result");
title2.setBounds(25, 5, 250, 35);
title2.setForeground(darkText);
title2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

header2.add(title2);
panel2.add(header2);

// Registration result labels
        resultMessage = new JLabel("No registration yet.");
        resultMessage.setBounds(25, 50, 390, 30);

        resultEvent = new JLabel("Event: ");
        resultEvent.setBounds(25, 95, 390, 30);

        resultAttendeeID = new JLabel("Attendee ID: ");
        resultAttendeeID.setBounds(250, 50, 390, 30);
        
        resultName = new JLabel("Name: ");
        resultName.setBounds(250, 95, 390, 30);


        panel2.add(resultMessage);
        panel2.add(resultEvent);
        panel2.add(resultAttendeeID);
        panel2.add(resultName);

add(panel2);
        

// PANEL 3


panel3 = new JPanel();
panel3.setLayout(null);
panel3.setBorder(BorderFactory.createLineBorder(lightBlue));
panel3.setBounds(490, 20, 470, 120);
panel3.setBackground(veryLightBlue);

// Lavender header
JPanel header3 = new JPanel();
header3.setLayout(null);
header3.setBounds(0, 0, 600, 40);
header3.setBackground(new Color(230, 230, 250));

JLabel title3 = new JLabel("Event Capacity");
title3.setBounds(25, 5, 250, 35);
title3.setForeground(darkText);
title3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

header3.add(title3);
panel3.add(header3);

// >>> ADDED CODE: Display event capacity information
capacityEventLabel = new JLabel("Event: ");
capacityEventLabel.setBounds(25, 50, 400, 25);
panel3.add(capacityEventLabel);

capacityRegisteredLabel = new JLabel("Registered Attendees: 0");
capacityRegisteredLabel.setBounds(25, 85, 400, 25);
panel3.add(capacityRegisteredLabel);

capacityAvailableLabel = new JLabel("Available Slots: 0");
capacityAvailableLabel.setBounds(320, 85, 400, 25);
panel3.add(capacityAvailableLabel);

// >>> ADDED CODE: Initialize registration count for each event
for (int i = 1; i < eventBox.getItemCount(); i++) {
    registeredCounts.put(eventBox.getItemAt(i), 0);
}

// >>> ADDED CODE: Update Panel 3 when the selected event changes
eventBox.addActionListener(e -> {
    String selectedEvent =
            (String) eventBox.getSelectedItem();

if (selectedEvent == null
        || selectedEvent.equals("Select Event")) {

    capacityEventLabel.setText("Event: Select Event");
    capacityRegisteredLabel.setText("Registered Attendees: -");
    capacityAvailableLabel.setText("Available Slots: - 0");

} else {
    int count = registeredCounts.getOrDefault(selectedEvent, 0);

    capacityEventLabel.setText("Event: " + selectedEvent);
    capacityRegisteredLabel.setText("Registered Attendees: " + count);
    capacityAvailableLabel.setText(
        "Available Slots: " + (EVENT_CAPACITY - count)
    );
}
});

add(panel3);


// PANEL 4

panel4 = new JPanel();
panel4.setLayout(null);
panel4.setBorder(BorderFactory.createLineBorder(lightBlue));
panel4.setBounds(20, 290, 940, 280);
panel4.setBackground(white);

// Lavender header
JPanel header4 = new JPanel();
header4.setLayout(null);
header4.setBounds(0, 0, 945, 40);
header4.setBackground(new Color(230, 230, 250));

JLabel title4 = new JLabel("Waiting List");
title4.setBounds(25, 5, 250, 35);
title4.setForeground(darkText);
title4.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

header4.add(title4);
panel4.add(header4);

tableModel = new DefaultTableModel();

        tableModel.addColumn("Priority");
        tableModel.addColumn("Attendee ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");

        
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(22, 52, 900, 220);

        panel4.add(scrollPane);
        
        

        add(panel4);

 

panel.setBackground(veryLightBlue);
panel2.setBackground(veryLightBlue);
panel3.setBackground(veryLightBlue);
panel4.setBackground(veryLightBlue);

registerButton.setBackground(darkBlue);
registerButton.setForeground(Color.WHITE);
registerButton.setFocusPainted(false);
    
eventBox.setBackground(white);
eventBox.setForeground(darkText);

clearButton.setBackground(lavender);
clearButton.setForeground(darkText);
clearButton.setFocusPainted(false);


registerButton.addActionListener(e -> {

            String attendeeId = idField.getText().trim();
            String selectedEvent = (String) eventBox.getSelectedItem();
            String attendeeName = nameField.getText().trim();
            String attendeeEmail = emailField.getText().trim();

            
           
            // Check required fields
            if (selectedEvent == null
                    || selectedEvent.trim().isEmpty()
                    || attendeeId.isEmpty()
                    || attendeeName.isEmpty()
                    || attendeeEmail.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields!",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            // Check if email contains @ and a valid domain format
if (!attendeeEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

    JOptionPane.showMessageDialog(
        this,
        "Invalid email! Please enter a valid email address with '@'.\n"
        + "Example: juan@gmail.com",
        "Invalid Email",
        JOptionPane.WARNING_MESSAGE
    );

    return;
}


            int count = registeredCounts.getOrDefault(selectedEvent, 0);

            // Add the typed information to the Waiting List table
            // every time a valid registration is submitted.
            

            // Update registration count if the event has available slots
            if (count < EVENT_CAPACITY) {

                registeredCounts.put(selectedEvent, count + 1);

                resultMessage.setText("Attendee registered successfully!");
                resultEvent.setText("Event: " + selectedEvent);
                resultAttendeeID.setText("Attendee ID: " + attendeeId);
                resultName.setText("Name: " + attendeeName);

                JOptionPane.showMessageDialog(
                    this,
                    "Registered successfully! Details added to the table.",
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } else {

    // Add attendee to the priority queue
    waitingList.addAttendee(
        attendeeId,
        attendeeName,
        attendeeEmail
    );

    // Update the Waiting List table
    refreshWaitingListTable();

    JOptionPane.showMessageDialog(
        this,
        "Event is full! Attendee added to the waiting list.",
        "Waiting List",
        JOptionPane.INFORMATION_MESSAGE
    );
}
            
        resultMessage.setText("No Registration yet.");
        resultEvent.setText("");
        resultAttendeeID.setText("");
        resultName.setText("");


           
        capacityEventLabel.setText("");
        capacityRegisteredLabel.setText("");
        capacityAvailableLabel.setText("");
        
        
            // Clear input fields
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            eventBox.setSelectedIndex(0);
        });

        // CLEAR BUTTON LOGIC
        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            eventBox.setSelectedIndex(0);
        });
    }
    private void refreshWaitingListTable() {
    tableModel.setRowCount(0);

    for (WaitingList.Attendee attendee
            : waitingList.getWaitingAttendees()) {

        tableModel.addRow(new Object[]{
            attendee.getPriority(),
            attendee.getId(),
            attendee.getName(),
            attendee.getEmail()
        });
    }
}

}
