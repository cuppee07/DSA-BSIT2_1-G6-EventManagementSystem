/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author Asus TUF
 */
public class ManageEventGui extends JFrame {
     static final Color PURPLE = new Color(142, 68, 173);
    static final Color PINK = new Color(243, 224, 247);
    static final Color TEXT = new Color(110, 30, 130);
    static final Color LINE = new Color(200, 190, 210);
 
    public ManageEventGui() {
        setTitle("Event Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 240, 248));
 
        EventDetailsPanel details = new EventDetailsPanel();
        details.setBounds(15, 15, 570, 320);
        add(details);
 
        SearchPanel search = new SearchPanel();
        search.setBounds(600, 15, 370, 320);
        add(search);
 
        EventListPanel list = new EventListPanel();
        list.setBounds(15, 350, 955, 230);
        add(list);
    }
 
    // pink title bar
    static JLabel header(String text, int width) {
        JLabel label = new JLabel("  " + text);
        label.setOpaque(true);
        label.setBackground(PINK);
        label.setForeground(TEXT);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBounds(0, 0, width, 40);
        return label;
    }
 
    // purple button (dark = filled purple, otherwise light)
    static JButton button(String text, boolean dark, int x, int y, int w, int h) {
        JButton b = new JButton(text);
        b.setBounds(x, y, w, h);
        b.setOpaque(true);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        if (dark) {
            b.setBackground(PURPLE);
            b.setForeground(Color.WHITE);
        } else {
            b.setBackground(PINK);
            b.setForeground(new Color(150, 100, 160));
        }
        return b;
    }
 
    public static void main(String[] args) throws Exception {
        // makes the button colors show on every computer
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
 
        ManageEventGui app = new ManageEventGui();
        app.setVisible(true);
    }
}
 
// Event Details panel
class EventDetailsPanel extends JPanel {
 
    public EventDetailsPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(ManageEventGui.LINE));
 
        add(ManageEventGui.header("Event Details", 568));
 
        JLabel idLabel = new JLabel("Event ID:");
        idLabel.setBounds(20, 55, 100, 32);
        add(idLabel);
        JTextField idField = new JTextField();
        idField.setBounds(150, 55, 395, 32);
        add(idField);
 
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 105, 100, 32);
        add(nameLabel);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 105, 395, 32);
        add(nameField);
 
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(20, 155, 100, 32);
        add(dateLabel);
        JTextField dateField = new JTextField("mm/dd/yyyy");
        dateField.setForeground(Color.GRAY);
        dateField.setBounds(150, 155, 395, 32);
        add(dateField);
 
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 205, 100, 32);
        add(categoryLabel);
        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Select Category", "Technology", "Health", "Arts"});
        categoryBox.setBounds(150, 205, 395, 32);
        add(categoryBox);
 
        add(ManageEventGui.button("Add", true, 20, 260, 122, 40));
        add(ManageEventGui.button("Update", false, 154, 260, 122, 40));
        add(ManageEventGui.button("Delete", false, 288, 260, 122, 40));
        add(ManageEventGui.button("Clear", false, 422, 260, 122, 40));
    }
}
 
// Search panel
class SearchPanel extends JPanel {
 
    public SearchPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(ManageEventGui.LINE));
 
        add(ManageEventGui.header("Search Event", 368));
 
        JLabel byLabel = new JLabel("By:");
        byLabel.setBounds(20, 55, 50, 32);
        add(byLabel);
        JComboBox<String> byBox = new JComboBox<>(new String[]{"Date", "Category", "Name"});
        byBox.setBounds(80, 55, 270, 32);
        add(byBox);
 
        JTextField searchField = new JTextField("mm/dd/yyyy");
        searchField.setForeground(Color.GRAY);
        searchField.setBounds(80, 105, 270, 32);
        add(searchField);
 
        add(ManageEventGui.button("Search", true, 80, 155, 220, 40));
    }
}
 
// Event List panel
class EventListPanel extends JPanel {
 
    public EventListPanel() {
        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(ManageEventGui.LINE));
 
        add(ManageEventGui.header("Event List", 953));
 
        String[] columns = {"Event ID", "Name", "Date", "Category", "Attendees"};
        Object[][] rows = {
            {"EVT001", "Tech Summit", "2025-10-15", "Technology", 0},
            {"EVT002", "Health & Wellness", "2025-11-05", "Health", 0},
            {"EVT003", "Creative Expo", "2025-12-10", "Arts", 0}
        };
 
        JTable table = new JTable(rows, columns);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(new Color(245, 240, 248));
 
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(15, 55, 922, 130);
        add(scroll);
    }
}
 
