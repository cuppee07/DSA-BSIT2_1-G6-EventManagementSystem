/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * manabat is here!
 */
package GUI;
import dsa.LinkedList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class ManageEventGui extends JFrame implements ActionListener{
    private JPanel pnlDetails, pnlSearch, pnlList;
    private JLabel hdrDetails, hdrSearch, hdrList;
    private JLabel lblEventId, lblName, lblDate, lblCategory, lblBy;
    private JTextField txtEventId, txtName, txtDate, txtSearchDate;
    private JComboBox<String> cmbCategory, cmbSearchBy;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch, btnBack;
    private JTable tblEvents;
    private TableRowSorter<DefaultTableModel> sorter;
    private JScrollPane scpEvents;
    private LinkedList linkedList;
    private DefaultTableModel model; 
    
    private static final String[] categories = {"Select Category", "Technology", "Business", "Arts"};
    private static final String[] searchOptions = {"Date", "Category", "Event ID"};
    private static final String[] columns = {"Event ID", "Name", "Date", "Category", "Attendees"};
 
    private Color color1 = new Color(72, 88, 150);// add and search button
    private Color color2 = new Color(224, 225, 250);// header bars and lighter buttons
    private Color color3 = new Color(50, 55, 120);// for header
    private Color color4 = new Color(200, 204, 232);// panel borders
 
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
UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
        setTitle("Event Management System");
        setSize(1000, 650);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 246, 252));
 
        //Event Details panel
        pnlDetails = new JPanel(null);
        //x axis, y axis, width, height
        pnlDetails.setBounds(15, 15, 570, 320);
        pnlDetails.setBackground(Color.WHITE);
        pnlDetails.setBorder(BorderFactory.createLineBorder(color4));
        add(pnlDetails);
 
        hdrDetails = new JLabel("  Event Details");
        hdrDetails.setBounds(0, 0, 568, 40);
        hdrDetails.setOpaque(true);
        hdrDetails.setBackground(color2);
        hdrDetails.setForeground(color3);
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
        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        if(txtDate.getText().equals("mm/dd/yyyy")){
            txtDate.setText("");
            txtDate.setForeground(Color.BLACK);
        }
    }
});
        pnlDetails.add(txtDate);
 
        cmbCategory = new JComboBox<>(categories);
        cmbCategory.setBounds(150, 205, 395, 32);
        pnlDetails.add(cmbCategory);
 
        btnAdd = new JButton("Add");
        btnAdd.setBounds(20, 260, 122, 40);
        btnAdd.setOpaque(true);
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBackground(color1);
        btnAdd.setForeground(Color.WHITE);
        pnlDetails.add(btnAdd);
 
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(154, 260, 122, 40);
        btnUpdate.setOpaque(true);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setBackground(color2);
        btnUpdate.setForeground(new Color(80, 90, 150));
        pnlDetails.add(btnUpdate);
 
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(288, 260, 122, 40);
        btnDelete.setOpaque(true);
        btnDelete.setFocusPainted(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setBackground(color2);
        btnDelete.setForeground(new Color(80, 90, 150));
        pnlDetails.add(btnDelete);
 
        btnClear = new JButton("Clear");
        btnClear.setBounds(422, 260, 122, 40);
        btnClear.setOpaque(true);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setBackground(color2);
        btnClear.setForeground(new Color(80, 90, 150));
        pnlDetails.add(btnClear);
 
             btnBack = new JButton("Back");
        btnBack.setBounds(15, 550, 85, 40);
        btnBack.setOpaque(true);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setBackground(color2);
        btnBack.setForeground(new Color(80, 90, 150));
        add(btnBack);
        
        
        //Search Event panel
        pnlSearch = new JPanel(null);
        pnlSearch.setBounds(600, 15, 370, 320);
        pnlSearch.setBackground(Color.WHITE);
        pnlSearch.setBorder(BorderFactory.createLineBorder(color4));
        add(pnlSearch);
 
        hdrSearch = new JLabel("  Search Event");
        hdrSearch.setBounds(0, 0, 368, 40);
        hdrSearch.setOpaque(true);
        hdrSearch.setBackground(color2);
        hdrSearch.setForeground(color3);
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
        txtSearchDate.addFocusListener(new java.awt.event.FocusAdapter() {
    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        if(txtSearchDate.getText().equals("mm/dd/yyyy")){
            txtSearchDate.setText("");
            txtSearchDate.setForeground(Color.BLACK);
        }
    }
});
        pnlSearch.add(txtSearchDate);
 
        btnSearch = new JButton("Search");
        btnSearch.setBounds(80, 155, 220, 40);
        btnSearch.setOpaque(true);
        btnSearch.setFocusPainted(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setBackground(color1);
        btnSearch.setForeground(Color.WHITE);
        pnlSearch.add(btnSearch);
 
 
        pnlList = new JPanel(null);
        pnlList.setBounds(15, 350, 955,180);
        pnlList.setBackground(Color.WHITE);
        pnlList.setBorder(BorderFactory.createLineBorder(color4));
        add(pnlList);
 
        hdrList = new JLabel("  Event List");
        hdrList.setBounds(0, 0, 953, 40);
        hdrList.setOpaque(true);
        hdrList.setBackground(color2);
        hdrList.setForeground(color3);
        hdrList.setFont(new Font("Segoe UI", Font.BOLD, 15));
        pnlList.add(hdrList);
 
        linkedList = new LinkedList();

        model = new DefaultTableModel(columns, 0);
        refreshTable();
        tblEvents = new JTable(model);
        sorter = new TableRowSorter<>(model);
    tblEvents.setRowSorter(sorter);
        tblEvents.setFillsViewportHeight(true);
        tblEvents.setFocusable(false); //FIXED: removes the black outline on the clicked cell
        tblEvents.setRowHeight(28);
        tblEvents.setGridColor(Color.LIGHT_GRAY);
        tblEvents.getTableHeader().setBackground(new Color(240, 240, 240));
        tblEvents.getTableHeader().setReorderingAllowed(false);
        tblEvents.getTableHeader().setResizingAllowed(false);
 
                scpEvents = new JScrollPane(tblEvents);
        scpEvents.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.LIGHT_GRAY));
        scpEvents.setBounds(15, 55, 922, 170);
        scpEvents.getViewport().setBackground(Color.WHITE);
        pnlList.add(scpEvents);
        
        
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
    }

    // Copies the events from the linked list into the table
    private void refreshTable() {
        model.setRowCount(0);
        for (Object[] row : linkedList.getAllEvents()) {
            model.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {         
        if(e.getSource() == btnAdd){
            String id = txtEventId.getText().trim();
            String name = txtName.getText().trim();
            String date = txtDate.getText().trim();
            String category = (String) cmbCategory.getSelectedItem();

            if(!id.isEmpty() && !name.isEmpty() && !date.isEmpty() && !date.equals("mm/dd/yyyy") && cmbCategory.getSelectedIndex() != 0){
                linkedList.addEvent(id, name, date, category, 0);
                refreshTable();

                txtEventId.setText("");
                txtName.setText("");
                txtDate.setText("mm/dd/yyyy");
                txtDate.setForeground(Color.GRAY);
                cmbCategory.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if(e.getSource() == btnUpdate){
            int indexSelected = tblEvents.getSelectedRow();

            if(indexSelected != -1){
                String id = txtEventId.getText().trim();
                String name = txtName.getText().trim();
                String date = txtDate.getText().trim();
                String category = (String) cmbCategory.getSelectedItem();

                if(!id.isEmpty() && !name.isEmpty() && !date.isEmpty() && !date.equals("mm/dd/yyyy") && cmbCategory.getSelectedIndex() != 0){
                    int modelIndex = tblEvents.convertRowIndexToModel(indexSelected);
                    linkedList.updateEvent(modelIndex, id, name, date, category);
                    refreshTable();

                    txtEventId.setText("");
                    txtName.setText("");
                    txtDate.setText("mm/dd/yyyy");
                    txtDate.setForeground(Color.GRAY);
                    cmbCategory.setSelectedIndex(0);
                    tblEvents.clearSelection();
                }else{
                    JOptionPane.showMessageDialog(this, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Please select an event first", "Input Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if(e.getSource() == btnDelete){
            int indexSelected = tblEvents.getSelectedRow();

            if(indexSelected != -1){
                int modelIndex = tblEvents.convertRowIndexToModel(indexSelected);
                linkedList.deleteEvent(modelIndex);
                refreshTable();

                txtEventId.setText("");
                txtName.setText("");
                txtDate.setText("mm/dd/yyyy");
                txtDate.setForeground(Color.GRAY);
                cmbCategory.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(this, "Please select an event first", "Input Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if(e.getSource() == btnClear){
            txtEventId.setText("");
            txtName.setText("");
            txtDate.setText("mm/dd/yyyy");
            txtDate.setForeground(Color.GRAY);
            cmbCategory.setSelectedIndex(0);
            tblEvents.clearSelection();
            cmbSearchBy.setSelectedIndex(0);
            txtSearchDate.setText("mm/dd/yyyy");
            txtSearchDate.setForeground(Color.GRAY);
            sorter.setRowFilter(null);
        }else if(e.getSource() == btnSearch){
    String text = txtSearchDate.getText().trim();

  int[] searchColumns = {2, 3, 0};
    int column = searchColumns[cmbSearchBy.getSelectedIndex()];

    if(text.isEmpty() || text.equals("mm/dd/yyyy")){
        sorter.setRowFilter(null); // show all events
    }else{
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, column));

        if(tblEvents.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "No events found", "Search", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    }
            else if(e.getSource()== btnBack){    
            dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        }
    }
}