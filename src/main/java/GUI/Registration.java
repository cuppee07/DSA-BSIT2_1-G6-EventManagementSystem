/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;


import dsa.WaitingList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/** 
 *
 * @author Rowena
 */
public class Registration extends JFrame implements ActionListener{   
    private Map<String, WaitingList> waitingLists = new HashMap<>();    private JComboBox<String> eventBox, eventBox1;
    private JTable table;
    private JTextField  idField, nameField, emailField;
    private DefaultTableModel tableModel;
    private JPanel panel, panel2, panel3, panel4;
    private JButton registerButton, clearButton, processButton, backButton;
    private JLabel resultMessage, resultEvent, resultAttendeeID, resultName, capacityEventLabel, capacityRegisteredLabel, capacityAvailableLabel;
    private java.util.Map<String, Integer> registeredCounts =
        new java.util.HashMap<>();

    private final int EVENT_CAPACITY = 5;

    Color blue = new Color(49, 70, 130);
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
panel.setBounds(20, 10, 450, 265);
panel.setBackground(veryLightBlue);

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


panel2 = new JPanel();
panel2.setLayout(null);
panel2.setBorder(BorderFactory.createLineBorder(lightBlue));
panel2.setBounds(490, 140, 470, 135);


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
        


panel3 = new JPanel();
panel3.setLayout(null);
panel3.setBorder(BorderFactory.createLineBorder(lightBlue));
panel3.setBounds(490, 10, 470, 120);
panel3.setBackground(veryLightBlue);

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

capacityEventLabel = new JLabel("Event: ");
capacityEventLabel.setBounds(25, 50, 400, 25);
panel3.add(capacityEventLabel);

capacityRegisteredLabel = new JLabel("Registered Attendees: 0");
capacityRegisteredLabel.setBounds(25, 85, 400, 25);
panel3.add(capacityRegisteredLabel);

capacityAvailableLabel = new JLabel("Available Slots: 0");
capacityAvailableLabel.setBounds(320, 85, 400, 25);
panel3.add(capacityAvailableLabel);

for (int i = 0; i < eventBox.getItemCount(); i++) {
    String eventName = eventBox.getItemAt(i);

    registeredCounts.put(eventName, 0);
    waitingLists.put(eventName, new WaitingList());
}

eventBox.addActionListener(e -> {
    String selectedEvent = (String) eventBox.getSelectedItem();

    if (selectedEvent == null) {
        return;
    }

    int count = registeredCounts.getOrDefault(selectedEvent, 0);
    int availableSlots = EVENT_CAPACITY - count;

    capacityEventLabel.setText("Event: " + selectedEvent);
    capacityRegisteredLabel.setText(
        "Registered Attendees: " + count
    );
    capacityAvailableLabel.setText(
        "Available Slots: " + availableSlots
    );

    refreshWaitingListTable();
});

add(panel3);


panel4 = new JPanel();
panel4.setLayout(null);
panel4.setBorder(BorderFactory.createLineBorder(lightBlue));
panel4.setBounds(20, 280, 940, 320);
panel4.setBackground(white);

JPanel header4 = new JPanel();
header4.setLayout(null);
header4.setBounds(0, 0, 945, 40);
header4.setBackground(new Color(230, 230, 250));

JLabel title4 = new JLabel("Waiting List");
title4.setBounds(25, 5, 250, 35);
title4.setForeground(darkText);
title4.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

processButton = new JButton ("Process Next");     
processButton.setBounds(742, 280, 180, 30);
add(processButton); 

backButton = new JButton ("Back");     
backButton.setBounds(20, 280, 140, 30);
add(backButton);



header4.add(title4);
panel4.add(backButton); 
panel4.add(processButton); 
panel4.add(header4);


tableModel = new DefaultTableModel();

        tableModel.addColumn("Priority");
        tableModel.addColumn("Attendee ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Email");

        
        table = new JTable(tableModel);

table.getTableHeader().setPreferredSize(
    new java.awt.Dimension(900, 30)
);

table.getTableHeader().setFont(
    new java.awt.Font("Arial", java.awt.Font.BOLD, 14)
);

table.setBackground(Color.WHITE);
table.setForeground(Color.BLACK);

table.setFillsViewportHeight(true);
table.setOpaque(true);

JScrollPane scrollPane = new JScrollPane(table);
scrollPane.getViewport().setBackground(Color.WHITE);
scrollPane.setBackground(Color.WHITE);

scrollPane.getViewport().setBackground(Color.WHITE);
scrollPane.setBackground(Color.WHITE);

table.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 16));

table.setRowHeight(40);

table.setFillsViewportHeight(true);

table.setBackground(Color.WHITE);
table.setForeground(Color.BLACK);

table.getTableHeader().setFont(
    new java.awt.Font("Arial", java.awt.Font.BOLD, 16)
);

table.getTableHeader().setPreferredSize(
    new java.awt.Dimension(0, 45)
);
        scrollPane.setBounds(22, 52, 900, 220);

        panel4.add(scrollPane);
        
        

        add(panel4);

 

panel.setBackground(veryLightBlue);
panel2.setBackground(veryLightBlue);
panel3.setBackground(veryLightBlue);
panel4.setBackground(veryLightBlue);

registerButton.setBackground(blue);
registerButton.setForeground(Color.WHITE);
registerButton.setFocusPainted(false);
registerButton.setBorderPainted(false);
registerButton.setContentAreaFilled(true);
    
eventBox.setBackground(white);
eventBox.setForeground(darkText);

clearButton.setBackground(lavender);
clearButton.setForeground(darkText);
clearButton.setFocusPainted(false);
clearButton.setBorderPainted(false);
clearButton.setContentAreaFilled(true);

backButton.setBackground(lavender);
backButton.setForeground(darkText);
backButton.setFocusPainted(false);
backButton.setBorderPainted(false);
backButton.setContentAreaFilled(true);

processButton.setBackground(blue);
processButton.setForeground(Color.WHITE);
processButton.setFocusPainted(false);
processButton.setBorderPainted(false);
processButton.setContentAreaFilled(true);






processButton.addActionListener(e -> {

    String selectedEvent = (String) eventBox.getSelectedItem();

    if (selectedEvent == null) {
        return;
    }

    WaitingList selectedWaitingList = waitingLists.get(selectedEvent);

    if (selectedWaitingList == null || selectedWaitingList.isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "There are no attendees waiting for this event!",
            "Waiting List",
            JOptionPane.INFORMATION_MESSAGE
        );
        return;
    }

    int count = registeredCounts.getOrDefault(selectedEvent, 0);
    int availableSlots = EVENT_CAPACITY - count;

    if (availableSlots <= 0) {
        JOptionPane.showMessageDialog(
            this,
            "The event is full! No available slots.",
            "Event Full",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    WaitingList.Attendee nextAttendee =
        selectedWaitingList.removeNextAttendee();

    if (nextAttendee == null) {
        return;
    }

    registeredCounts.put(selectedEvent, count + 1);

    resultMessage.setText("Attendee processed successfully!");
    resultEvent.setText("Event: " + selectedEvent);
    resultAttendeeID.setText(
        "Attendee ID: " + nextAttendee.getId()
    );
    resultName.setText("Name: " + nextAttendee.getName());

    refreshWaitingListTable();

    capacityEventLabel.setText("Event: " + selectedEvent);
    capacityRegisteredLabel.setText(
        "Registered Attendees: " + (count + 1)
    );
    capacityAvailableLabel.setText(
        "Available Slots: " + (EVENT_CAPACITY - count - 1)
    );

    JOptionPane.showMessageDialog(
        this,
        nextAttendee.getName() + " has been processed successfully!",
        "Process Next",
        JOptionPane.INFORMATION_MESSAGE
    );
});


registerButton.addActionListener(e -> {

            String attendeeId = idField.getText().trim();
            String selectedEvent = (String) eventBox.getSelectedItem();
            String attendeeName = nameField.getText().trim();
            String attendeeEmail = emailField.getText().trim();

            
           
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
if (!attendeeEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

    JOptionPane.showMessageDialog(
        this,
        "Invalid email! Please enter a valid email address"
        + "Example: juan@gmail.com",
        "Invalid Email",
        JOptionPane.WARNING_MESSAGE
    );

    return;
}


            int count = registeredCounts.getOrDefault(selectedEvent, 0);


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

    WaitingList selectedWaitingList = waitingLists.get(selectedEvent);

    selectedWaitingList.addAttendee(
        attendeeId,
        attendeeName,
        attendeeEmail
    );

    refreshWaitingListTable();

    JOptionPane.showMessageDialog(
        this,
        "Event is full! Attendee added to the waiting list.",
        "Waiting List",
        JOptionPane.INFORMATION_MESSAGE
    );
}
            
        
        
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            eventBox.setSelectedIndex(0);
        });

        clearButton.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            eventBox.setSelectedIndex(0);
        });
           backButton.addActionListener(this);
    }

private void refreshWaitingListTable() {

    tableModel.setRowCount(0);

    String selectedEvent = (String) eventBox.getSelectedItem();

    if (selectedEvent == null) {
        return;
    }
    WaitingList selectedWaitingList = waitingLists.get(selectedEvent);

    if (selectedWaitingList == null) {
        return;
    }

    for (WaitingList.Attendee attendee :
            selectedWaitingList.getWaitingAttendees()) {

        tableModel.addRow(new Object[]{
            attendee.getPriority(),
            attendee.getId(),
            attendee.getName(),
            attendee.getEmail()
        });
    }
}

 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        }
    }

}

