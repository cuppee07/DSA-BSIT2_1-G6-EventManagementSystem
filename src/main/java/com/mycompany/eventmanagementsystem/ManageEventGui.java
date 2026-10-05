/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
public class ManageEventGui extends JFrame{
    private JPanel pnlDetails, pnlSearch, pnlList;
    private JLabel hdrDetails, hdrSearch, hdrList;
    private JLabel lblEventId, lblName, lblDate, lblCategory, lblBy;
    private JTextField txtEventId, txtName, txtDate, txtSearchDate;
    private JComboBox<String> cmbCategory, cmbSearchBy;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch;
    private JTable tblEvents;
    private JScrollPane scpEvents;
 
    private static final String[] categories = {"Select Category", "Technology", "Health", "Arts"};
    private static final String[] searchOptions = {"Date", "Category", "Name"};
    private static final String[] columns = {"Event ID", "Name", "Date", "Category", "Attendees"};
 
    private Color clrPurple = new Color(142, 68, 173);
    private Color clrPink = new Color(243, 224, 247);
    private Color clrText = new Color(110, 30, 130);
    private Color clrLine = new Color(200, 190, 210);
 
    private Font fntPlain = new Font("Segoe UI", Font.PLAIN, 14);
    private Font fntBold = new Font("Segoe UI", Font.BOLD, 14);
 
    public ManageEventGui() {
        setLayout(null);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
        }
 
        //Font for every component
        UIManager.put("Label.font", fntPlain);
        UIManager.put("TextField.font", fntPlain);
        UIManager.put("ComboBox.font", fntPlain);
        UIManager.put("Table.font", fntPlain);
        UIManager.put("TableHeader.font", fntBold);
        UIManager.put("Button.font", fntBold);
 
        setTitle("Event Management System");
        setSize(1000, 650);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 240, 248));
 
        //Event Details panel
        pnlDetails = new JPanel(null);
        //x axis, y axis, width, height
        pnlDetails.setBounds(15, 15, 570, 320);
        pnlDetails.setBackground(Color.WHITE);
        pnlDetails.setBorder(BorderFactory.createLineBorder(clrLine));
        add(pnlDetails);
 
        hdrDetails = new JLabel("  Event Details");
        hdrDetails.setBounds(0, 0, 568, 40);
        hdrDetails.setOpaque(true);
        hdrDetails.setBackground(clrPink);
        hdrDetails.setForeground(clrText);
        hdrDetails.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pnlDetails.add(hdrDetails);
 
        lblEventId = new JLabel("Event ID:");
        lblEventId.setBounds(20, 55, 100, 32);
        pnlDetails.add(lblEventId);
 
        lblName = new JLabel("Name:");
        lblName.setBounds(20, 105, 100, 32);
        pnlDetails.add(lblName);
 
        lblDate = new JLabel("Date:");
        lblDate.setBounds(20, 155, 100, 32);
        pnlDetails.add(lblDate);
 
        lblCategory = new JLabel("Category:");
        lblCategory.setBounds(20, 205, 100, 32);
        pnlDetails.add(lblCategory);
 
        txtEventId = new JTextField();
        txtEventId.setBounds(150, 55, 395, 32);
        pnlDetails.add(txtEventId);
 
        txtName = new JTextField();
        txtName.setBounds(150, 105, 395, 32);
        pnlDetails.add(txtName);
 
        txtDate = new JTextField("mm/dd/yyyy");
        txtDate.setBounds(150, 155, 395, 32);
        txtDate.setForeground(Color.GRAY);
        pnlDetails.add(txtDate);
 
        cmbCategory = new JComboBox<>(categories);
        cmbCategory.setBounds(150, 205, 395, 32);
        pnlDetails.add(cmbCategory);
 
        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 260, 122, 40);
        btnAdd.setOpaque(true);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBackground(clrPurple);
        btnAdd.setForeground(Color.WHITE);
        pnlDetails.add(btnAdd);
 
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(154, 260, 122, 40);
        btnUpdate.setOpaque(true);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setBackground(clrPink);
        btnUpdate.setForeground(new Color(120, 70, 140));
        pnlDetails.add(btnUpdate);
 
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(288, 260, 122, 40);
        btnDelete.setOpaque(true);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setBackground(clrPink);
        btnDelete.setForeground(new Color(120, 70, 140));
        pnlDetails.add(btnDelete);
 
        btnClear = new JButton("Clear");
        btnClear.setBounds(422, 260, 122, 40);
        btnClear.setOpaque(true);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setBackground(clrPink);
        btnClear.setForeground(new Color(120, 70, 140));
        pnlDetails.add(btnClear);
 
        //Search Event panel
        pnlSearch = new JPanel(null);
        pnlSearch.setBounds(600, 15, 370, 320);
        pnlSearch.setBackground(Color.WHITE);
        pnlSearch.setBorder(BorderFactory.createLineBorder(clrLine));
        add(pnlSearch);
 
        hdrSearch = new JLabel("  Search Event");
        hdrSearch.setBounds(0, 0, 368, 40);
        hdrSearch.setOpaque(true);
        hdrSearch.setBackground(clrPink);
        hdrSearch.setForeground(clrText);
        hdrSearch.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pnlSearch.add(hdrSearch);
 
        lblBy = new JLabel("By:");
        lblBy.setBounds(20, 55, 50, 32);
        pnlSearch.add(lblBy);
 
        cmbSearchBy = new JComboBox<>(searchOptions);
        cmbSearchBy.setBounds(80, 55, 270, 32);
        pnlSearch.add(cmbSearchBy);
 
        txtSearchDate = new JTextField("mm/dd/yyyy");
        txtSearchDate.setBounds(80, 105, 270, 32);
        txtSearchDate.setForeground(Color.GRAY);
        pnlSearch.add(txtSearchDate);
 
        btnSearch = new JButton("Search");
        btnSearch.setBounds(80, 155, 220, 40);
        btnSearch.setOpaque(true);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setBackground(clrPurple);
        btnSearch.setForeground(Color.WHITE);
        pnlSearch.add(btnSearch);
 
        //Event List panel
        pnlList = new JPanel(null);
        pnlList.setBounds(15, 350, 955, 230);
        pnlList.setBackground(Color.WHITE);
        pnlList.setBorder(BorderFactory.createLineBorder(clrLine));
        add(pnlList);
 
        hdrList = new JLabel("  Event List");
        hdrList.setBounds(0, 0, 953, 40);
        hdrList.setOpaque(true);
        hdrList.setBackground(clrPink);
        hdrList.setForeground(clrText);
        hdrList.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pnlList.add(hdrList);
 
        Object[][] rows = {
            {"EVT001", "Tech Summit", "2025-10-15", "Technology", 0},
            {"EVT002", "Health & Wellness", "2025-11-05", "Health", 0},
            {"EVT003", "Creative Expo", "2025-12-10", "Arts", 0}
        };
 
        tblEvents = new JTable(rows, columns);
        tblEvents.setRowHeight(28);
        tblEvents.getTableHeader().setBackground(new Color(245, 240, 248));
 
        scpEvents = new JScrollPane(tblEvents);
        scpEvents.setBounds(15, 55, 922, 130);
        pnlList.add(scpEvents);
    }
 
}
 



