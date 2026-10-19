/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttendeeManagement;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
//test

/**
 *
 * @author User
 */
public class Attendee extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel;
    private LinkedList<String> linkedlist;
    private JList<String> list;
    private JScrollPane scrollpane;
    private JTextField txtfield;
    private JPanel ad, sa, al;
    private JLabel lblid, lblmail, lblevent, lblname, lblby, lblad, lblsa, lblal;
    private TitledBorder titleB;
    private JButton btnadd, btnupdt, btndlt, btnclr, btnsrch;
    private JComboBox<String> cbevnt, cbby;
    private DefaultTableModel listTable;

    private Color blue = new Color(49, 70, 130);
    private Color purple = new Color(105, 103, 175);
    private Color lightPurple = new Color(231, 229, 250);
    private Color white = Color.WHITE;
    private Color background = new Color(248, 249, 255);

    Attendee() {

        linkedlist = new LinkedList<>();
        listModel = new DefaultListModel<>();

        setTitle("Event Management System");
        setSize(1000, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(background);

        
        JPanel ad = new JPanel();
        ad.setBounds(30, 30, 550, 250);
        ad.setBorder(BorderFactory.createLineBorder(lightPurple, 2));
        ad.setLayout(null);
        ad.setBackground(white);
        add(ad);

        JLabel lblad = new JLabel("Attendee Details");
        lblad.setBounds(0, 0, 550, 40);
        lblad.setOpaque(true);
        lblad.setBackground(lightPurple);
        lblad.setForeground(blue);
        ad.add(lblad);

        JLabel lblid = new JLabel("ID:");
        lblid.setBounds(20, 40, 70, 30);
        ad.add(lblid);

        txtfield = new JTextField();
        txtfield.setBounds(85, 40, 425, 30);
        ad.add(txtfield);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(20, 80, 70, 30);
        ad.add(lblname);

        txtfield = new JTextField();
        txtfield.setBounds(85, 80, 425, 30);
        ad.add(txtfield);

        JLabel lblmail = new JLabel("Email:");
        lblmail.setBounds(20, 120, 70, 30);
        ad.add(lblmail);

        txtfield = new JTextField();
        txtfield.setBounds(85, 120, 425, 30);
        ad.add(txtfield);

        JLabel lblevent = new JLabel("Event:");
        lblevent.setBounds(20, 160, 70, 30);
        ad.add(lblevent);

        JComboBox cbevnt = new JComboBox();
        cbevnt.setBounds(85, 160, 425, 30);

        cbevnt.addItem("Select Event");
        cbevnt.addItem("EVENT001");
        cbevnt.addItem("EVENT002");
        cbevnt.addItem("EVENT003");
        ad.add(cbevnt);

        
        btnadd = new JButton("Add");
        btnadd.setBounds(20, 200, 100, 40);
        btnadd.setBackground(blue);
        btnadd.setForeground(white);
        btnadd.setOpaque(true);
        btnadd.setContentAreaFilled(true);
        btnadd.setBorderPainted(false);
        ad.add(btnadd);

        
        btnupdt = new JButton("Update");
        btnupdt.setBounds(150, 200, 100, 40);
        btnupdt.setBackground(lightPurple);
        btnupdt.setForeground(blue);
        btnupdt.setOpaque(true);
        btnupdt.setContentAreaFilled(true);
        btnupdt.setBorderPainted(false);
        ad.add(btnupdt);

        
        btndlt = new JButton("Delete");
        btndlt.setBounds(280, 200, 100, 40);
        btndlt.setBackground(lightPurple);
        btndlt.setForeground(blue);
        btndlt.setOpaque(true);
        btndlt.setContentAreaFilled(true);
        btndlt.setBorderPainted(false);
        ad.add(btndlt);

        // Clear button
        btnclr = new JButton("Clear");
        btnclr.setBounds(410, 200, 100, 40);
        btnclr.setBackground(lightPurple);
        btnclr.setForeground(blue);
        btnclr.setOpaque(true);
        btnclr.setContentAreaFilled(true);
        btnclr.setBorderPainted(false);
        ad.add(btnclr);


       
        JPanel sa = new JPanel();
        sa.setBounds(610, 30, 350, 250);
        sa.setBorder(BorderFactory.createLineBorder(lightPurple, 2));
        sa.setBackground(white);
        sa.setLayout(null);
        add(sa);

        JLabel lblsa = new JLabel("Search Attendee");
        lblsa.setBounds(0, 0, 350, 40);
        lblsa.setOpaque(true);
        lblsa.setBackground(lightPurple);
        lblsa.setForeground(blue);
        sa.add(lblsa);

        JLabel lblby = new JLabel("By:");
        lblby.setBounds(20, 40, 50, 30);
        sa.add(lblby);

        JComboBox cbby = new JComboBox();
        cbby.setBounds(85, 40, 250, 30);

        cbby.addItem("Name:");
        cbby.addItem("ID:");
        cbby.addItem("Email:");
        cbby.addItem("");
        sa.add(cbby);

        txtfield = new JTextField("");
        txtfield.setBounds(85, 80, 250, 30);
        sa.add(txtfield);

        
        btnsrch = new JButton("Search");
        btnsrch.setBounds(85, 120, 200, 40);
        btnsrch.setBackground(purple);
        btnsrch.setForeground(white);
        btnsrch.setOpaque(true);
        btnsrch.setContentAreaFilled(true);
        btnsrch.setBorderPainted(false);
        sa.add(btnsrch);


        
        JPanel al = new JPanel();
        al.setBounds(30, 290, 930, 280);
        al.setBorder(BorderFactory.createLineBorder(lightPurple, 2));
        al.setLayout(null);
        al.setBackground(white);
        add(al);

        JLabel lblal = new JLabel("Attendee List");
        lblal.setBounds(0, 0, 930, 40);
        lblal.setOpaque(true);
        lblal.setBackground(lightPurple);
        lblal.setForeground(blue);
        al.add(lblal);

        
        String[] columns = {"ID", "Name", "Email", "Event ID"};

        listTable = new DefaultTableModel(columns, 0);
        JTable attendeeTable = new JTable(listTable);

        attendeeTable.setGridColor(lightPurple);
        attendeeTable.setSelectionBackground(lightPurple);
        attendeeTable.setSelectionForeground(blue);

        attendeeTable.getTableHeader().setBackground(lightPurple);
        attendeeTable.getTableHeader().setForeground(blue);

        scrollpane = new JScrollPane(attendeeTable);
        scrollpane.setBounds(20, 50, 870, 200);
        al.add(scrollpane);

       
        btnadd.addActionListener(this);
        btnupdt.addActionListener(this);
        btndlt.addActionListener(this);
        btnclr.addActionListener(this);
        btnsrch.addActionListener(this);

        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        

    }
}