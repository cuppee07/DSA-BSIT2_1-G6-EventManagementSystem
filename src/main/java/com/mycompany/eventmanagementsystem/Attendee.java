/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;
import java.awt.Component;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
 //test

/**
 *
 * @author User
 */
public class Attendee extends JFrame {
     private DefaultListModel<String> listModel;
    private LinkedList <String> linkedlist;
    private JList<String> list;
    private JScrollPane scrollpane;
    private JTextField txtfield;
    private JPanel ad, sa, al;
    private JLabel lblid, lblmail, lblevent, lblname, lblby, lblad, lblsa, lblal;
    private TitledBorder titleB;
    private JButton btnadd, btnupdt, btndlt, btnclr, btnsrch;
    private JComboBox <String> cbevnt, cbby;
    private DefaultTableModel listTable;
            
    
    Attendee(){
        linkedlist = new LinkedList<>();
        listModel = new DefaultListModel<>();
       
        setTitle("Event Management System");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel ad = new JPanel ();
        ad.setBounds(30, 30, 550, 250);
        //TitledBorder titleB = BoderFactory.createTitledFolder(BorderFactory.createEtchedBorder(),"Attendee Details");
        ad.setBorder(BorderFactory.createTitledBorder(""));
        ad.setLayout(null);
        //ad.setBorder(titleB);
        add(ad);
        
        JLabel lblad = new JLabel ("Attendee Details");
        lblad.setBounds(20, 1, 100, 50);
        ad.add(lblad);
        
        JLabel lblid = new JLabel ("ID:");
        lblid.setBounds(20, 40, 70, 30);
        ad.add(lblid);
        
        txtfield = new JTextField();
        txtfield.setBounds(85, 40, 425, 30);
        ad.add(txtfield);
        
        JLabel lblname = new JLabel ("Name:");
        lblname.setBounds(20, 80, 70, 30);
        ad.add(lblname);
        
        txtfield = new JTextField();
        txtfield.setBounds(85, 80, 425, 30);
        ad.add(txtfield);
        
        JLabel lblmail = new JLabel ("Email:");
        lblmail.setBounds(20, 120, 70, 30);
        ad.add(lblmail);
        
        txtfield = new JTextField();
        txtfield.setBounds(85, 120, 425, 30);
        ad.add(txtfield);
        
        JLabel lblevent = new JLabel ("Event:");
        lblevent.setBounds(20, 160, 70, 30);
        ad.add(lblevent);
        
        JComboBox cbevnt = new JComboBox ();
        cbevnt.setBounds(85, 160, 425, 30);
        
        cbevnt.addItem("Select Event");
        cbevnt.addItem("EVENT001");
        cbevnt.addItem("EVENT002");
        cbevnt.addItem("EVENT003");
        ad.add(cbevnt);
        
        btnadd = new JButton("Add");
        btnadd.setBounds(20, 200, 100, 40);
        ad.add(btnadd);
        
        btnupdt = new JButton("Update");
        btnupdt.setBounds(150, 200, 100, 40);
        ad.add(btnupdt);
        
        btndlt = new JButton("Delete");
        btndlt.setBounds(280, 200, 100, 40);
        ad.add(btndlt);
        
        btnclr = new JButton("Clear");
        btnclr.setBounds(410, 200, 100, 40);
        ad.add(btnclr);
        
        
        JPanel sa = new JPanel ();
        sa.setBounds(610, 30, 350, 250);
        sa.setBorder(BorderFactory.createTitledBorder(""));
        add(sa);
        sa.setLayout(null);
        
        JLabel lblsa = new JLabel ("Search Attendee");
        lblsa.setBounds(20, 1, 100, 50);
        sa.add(lblsa);
        
        JLabel lblby = new JLabel ("By:");
        lblby.setBounds(20, 40, 50, 30);
        sa.add(lblby);
        
        JComboBox cbby = new JComboBox ();
        cbby.setBounds(85, 40, 250, 30);
        
        cbby.addItem("Name:");
        cbby.addItem(" ");
        cbby.addItem(" ");
        cbby.addItem(" ");
        sa.add(cbby);
        
        txtfield = new JTextField("Enter Name ");
        txtfield.setBounds(85, 80, 250, 30);
        sa.add(txtfield);
        
        btnsrch = new JButton("Search");
        btnsrch.setBounds(85, 120, 200, 40);
        sa.add(btnsrch);
        
        JPanel al = new JPanel ();
        al.setBounds(30, 290, 930, 280);
        al.setBorder(BorderFactory.createTitledBorder(""));
        al.setLayout(null);
        add(al);
        
        JLabel lblal = new JLabel ("Attendee List");
        lblal.setBounds(20, 1, 100, 50);
        al.add(lblal);
        
        
        String[] columns = {"ID", "Name", "Email", "Event ID"};
        
        listTable = new DefaultTableModel(columns,0);
        JTable attendeeTable = new JTable(listTable);
        
        scrollpane = new JScrollPane(attendeeTable);
        scrollpane.setBounds(20, 50, 870, 200);
        al.add(scrollpane);
        
                
        
        
        
        
        
        
    }
    
}
