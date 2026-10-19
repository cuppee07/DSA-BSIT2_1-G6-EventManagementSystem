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

/**
 *
 * @author User
 */
public class Attendee extends JFrame implements ActionListener {

    private DefaultListModel<String> listModel;
    private LinkedList<String> linkedlist;
    private JList<String> list;
    private JScrollPane scrollpane;
    private JTextField txtfield, txtid, txtname, txtmail, txtsearch;
    private JPanel ad, sa, al;
    private JLabel lblid, lblmail, lblevent, lblname, lblby, lblad, lblsa, lblal;
    private TitledBorder titleB;
    private JButton btnadd, btnupdt, btndlt, btnclr, btnsrch;
    private JComboBox<String> cbevnt, cbby;
    private DefaultTableModel listTable;
    private JTable attendeeTable;
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

        txtid = new JTextField();
        txtid.setBounds(85, 40, 425, 30);
        ad.add(txtid);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(20, 80, 70, 30);
        ad.add(lblname);

        txtname = new JTextField();
        txtname.setBounds(85, 80, 425, 30);
        ad.add(txtname);

        JLabel lblmail = new JLabel("Email:");
        lblmail.setBounds(20, 120, 70, 30);
        ad.add(lblmail);

        txtmail = new JTextField();
        txtmail.setBounds(85, 120, 425, 30);
        ad.add(txtmail);

        JLabel lblevent = new JLabel("Event:");
        lblevent.setBounds(20, 160, 70, 30);
        ad.add(lblevent);

        cbevnt = new JComboBox();
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

        cbby = new JComboBox();
        cbby.setBounds(85, 40, 250, 30);

        cbby.addItem("Name:");
        sa.add(cbby);

        txtsearch = new JTextField("");
        txtsearch.setBounds(85, 80, 250, 30);
        sa.add(txtsearch);
        
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
        attendeeTable = new JTable(listTable);

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

         if(e.getSource()==btnadd){
             String id= txtid.getText().trim(); 
             String name= txtname.getText().trim(); 
             String mail= txtmail.getText().trim(); 
             String event = cbevnt.getSelectedItem().toString();
             
              if (id.isEmpty() || name.isEmpty() || mail.isEmpty()
                    || event.equals("Select Event")) {

                JOptionPane.showMessageDialog(this,
                        "Please complete all fields.");

            } else {

                boolean duplicate = false;

                for (int i = 0; i < listTable.getRowCount(); i++) {
                    if (listTable.getValueAt(i, 0).toString().equalsIgnoreCase(id)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    JOptionPane.showMessageDialog(this,
                            "This ID already exists.");
                } else {
                    listTable.addRow(new Object[]{id, name, mail, event});
                    
                    linkedlist.add(id + " - " + name + " - "
                            + mail + " - " + event);
                    
                    JOptionPane.showMessageDialog(this, "Added succesfully");
                    
                    clearFields();
                }
             }
         }
          else if (e.getSource() == btnupdt) {
              int row = attendeeTable.getSelectedRow();
              
              if (row == -1){
              JOptionPane.showMessageDialog(this, "Please select an attendee to update");
              
          }else{
             String id= txtid.getText().trim(); 
             String name= txtname.getText().trim(); 
             String mail= txtmail.getText().trim(); 
             String event = cbevnt.getSelectedItem().toString();
                  
             if (id.isEmpty() || name.isEmpty() || mail.isEmpty()
                    || event.equals("Select Event")) {
                 
                 JOptionPane.showMessageDialog(this, "Please complete all the fields.");
             }else{
                 
                 listTable.setValueAt(id, row, 0);
                 listTable.setValueAt(name, row, 1);
                 listTable.setValueAt(mail, row, 2);
                 listTable.setValueAt(event, row, 3);
                 
                 JOptionPane.showMessageDialog(this, "Updated successfully!");
                 
                 clearFields();  
             }
            }  
          }
         if(e.getSource()== btndlt){
             
             int row = attendeeTable.getSelectedRow();
             
             if (row == -1){
                 JOptionPane.showMessageDialog(this, "Select an attendee to delete.");
             }else{
                 int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete attendee?",
                         "Confirm Delete",
                         JOptionPane.YES_NO_OPTION);
                 
             if (confirm == JOptionPane.YES_OPTION){
                 listTable.removeRow(row);
                 
                 JOptionPane.showMessageDialog(this, "Attendee deleted successfully.");
                 
                 clearFields();
          }         
        }      
      }
         else if (e.getSource()== btnclr){
                  clearFields();
                  attendeeTable.clearSelection();
         }
         
          else if (e.getSource() == btnsrch) {

            String search = txtsearch.getText().trim();
            String searchBy = cbby.getSelectedItem().toString();
            int column = 1;

            if (search.isEmpty() || search.equalsIgnoreCase("Enter Name")) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a name, ID, or email to search.");
                return;
            }

            if (searchBy.equals("ID")) {
                column = 0;
            } else if (searchBy.equals("Email")) {
                column = 2;
            }

            boolean found = false;

            for (int i = 0; i < listTable.getRowCount(); i++) {

                String value = listTable.getValueAt(i, column).toString();

                if (value.equalsIgnoreCase(search)) {

                    String name = listTable.getValueAt(i, 1).toString();

                    JOptionPane.showMessageDialog(this,
                            name + " is here!");

                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this,
                        "Attendee not found.");
            }
        }
    }
    private void clearFields(){
        txtid.setText("");
        txtname.setText("");
        txtmail.setText("");
        cbevnt.setSelectedIndex(0);
    }
    }