/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rowena
 */
public class WaitingList extends JFrame{
    
    
    private JLabel title, event;
    private JTable table;
    private JTextField txtF;
    private JPanel panel, panel2;
    private JButton view, pro;
    private DefaultTableModel tableModel;
    private JComboBox<String> eventBox;
    
    Color darkBlue = new Color(35, 100, 165);
    Color lightBlue = new Color(220, 235, 250);
    Color veryLightBlue = new Color(240, 246, 253);
    Color white = Color.WHITE;
    Color darkText = new Color(35, 55, 80);
    Color lavender = new Color(230, 230, 250);
    
    WaitingList (){
        
        setTitle("Event Management System");
        setSize(1000,650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBorder(BorderFactory.createLineBorder(lightBlue));
    panel.setBounds(15, 20, 955, 550);
    panel.setBackground(veryLightBlue);
    
    JPanel header = new JPanel();
header.setLayout(null);
header.setBounds(0, 0, 990, 40);
header.setBackground(new Color(230, 230, 250));
    
JLabel title = new JLabel("Waiting List (Priority Queue)");
title.setBounds(25, 5, 250, 35);
title.setForeground(darkText);
title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

 JLabel event = new JLabel ("Event:");
 event.setBounds (30, 50, 100, 50);
 
 JComboBox eventBox = new JComboBox ();
 eventBox.setBounds(80, 60, 400, 30);
 
 JButton view = new JButton ("View Details");
 view.setBounds(500, 60, 150, 30);

 tableModel = new DefaultTableModel();

tableModel.addColumn("Priority");
tableModel.addColumn("Attendee ID");
tableModel.addColumn("Name");
tableModel.addColumn("Email");

table = new JTable(tableModel);

JScrollPane scrollPane = new JScrollPane(table);
scrollPane.setBounds(40, 120, 600, 400);

panel.add(scrollPane);
 
 
 
panel.add(event);
panel.add(eventBox);
panel.add(view);

header.add(title);
panel.add(header);

JPanel panel2 = new JPanel();
    panel2.setLayout(null);
    panel2.setBorder(BorderFactory.createLineBorder(lightBlue));
    panel2.setBounds(650, 200, 290, 250);
    panel2.setBackground(veryLightBlue);
    
    JPanel header2 = new JPanel();
header2.setLayout(null);
header2.setBounds(0, 0, 290, 40);
header2.setBackground(new Color(230, 230, 250));
    
JLabel title2 = new JLabel("When a slot becomes available:");
title2.setBounds(15, 5, 250, 35);
title2.setForeground(darkText);
title2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));


JButton pro = new JButton("Process Next");
pro.setBounds(90, 200, 120, 30);

panel2.add(pro);
        
        
header2.add(title2);
panel2.add(header2);

panel.add(panel2);

add(panel);

view.setBackground(lavender);
view.setForeground(darkText);
view.setFocusPainted(false);
    

pro.setBackground(lavender);
pro.setForeground(darkText);
pro.setFocusPainted(false);

    }
    
}
