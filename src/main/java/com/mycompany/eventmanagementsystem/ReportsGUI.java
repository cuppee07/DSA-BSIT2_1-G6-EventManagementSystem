/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

/**
 *
 * @author ali
 */
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class ReportsGUI extends JFrame {

    // Runs once, before the first ReportsGUI is created
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
    }

    // ====== Colors ======
    private static final Color PURPLE       = new Color(0x7B3F9E);
    private static final Color PURPLE_DARK  = new Color(0x5B1A7C);
    private static final Color HEADER_BG    = new Color(0xF3E1F5);
    private static final Color BORDER_COLOR = new Color(0xD9D9E3);
    private static final Color WINDOW_BG    = new Color(0xF4F4F8);
    private static final Color RED          = new Color(0xE01B1B);
    private static final Color GREEN        = new Color(0x1E8E3E);
    private static final Font  BASE_FONT    = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font  BOLD_FONT    = new Font("Segoe UI", Font.BOLD, 14);

    // ====== Components (public getters at the bottom, for adding logic later) ======
    private JRadioButton attendanceRadio, feedbackRadio, summaryRadio;
    private RoundedButton generateBtn;
    private JComboBox<String> eventCombo;
    private JLabel totalRegisteredValue, totalCheckedInValue, attendanceRateValue, waitingListValue;
    private JTable table;
    private DefaultTableModel tableModel;

    public ReportsGUI() {
        super("Event Management System");
        buildUI();

        // DISPOSE_ON_CLOSE: closing this window won't shut down the whole program
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
    }

    // ====================================================================
    //  UI
    // ====================================================================
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(16, 0));
        root.setBackground(WINDOW_BG);
        root.setBorder(new EmptyBorder(14, 14, 14, 14));

        root.add(buildLeftPanel(), BorderLayout.WEST);
        root.add(buildRightPanel(), BorderLayout.CENTER);

        setContentPane(root);
    }

    // ---------- LEFT SIDE : Select Report ----------
    private JPanel buildLeftPanel() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(20, 20, 20, 20));

        attendanceRadio = createRadio("Attendance Report", true);
        feedbackRadio   = createRadio("Feedback Report", false);
        summaryRadio    = createRadio("Event Summary", false);

        ButtonGroup group = new ButtonGroup();
        group.add(attendanceRadio);
        group.add(feedbackRadio);
        group.add(summaryRadio);

        body.add(attendanceRadio);
        body.add(Box.createVerticalStrut(14));
        body.add(feedbackRadio);
        body.add(Box.createVerticalStrut(14));
        body.add(summaryRadio);
        body.add(Box.createVerticalStrut(30));

        generateBtn = new RoundedButton("Generate");
        generateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateBtn.setPreferredSize(new Dimension(0, 44));
        generateBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        body.add(generateBtn);

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.add(body, BorderLayout.NORTH);

        JPanel card = createCard("Select Report", top);
        card.setPreferredSize(new Dimension(300, 0));
        return card;
    }

    private JRadioButton createRadio(String text, boolean selected) {
        JRadioButton r = new JRadioButton(text, selected);
        r.setFont(selected ? BOLD_FONT : BASE_FONT);
        r.setIcon(new RadioIcon());
        r.setIconTextGap(12);
        r.setOpaque(false);
        r.setFocusPainted(false);
        r.setAlignmentX(Component.LEFT_ALIGNMENT);
        r.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // selected option is bold, like the screenshot
        r.addChangeListener(e -> r.setFont(r.isSelected() ? BOLD_FONT : BASE_FONT));
        return r;
    }

    // ---------- RIGHT SIDE : Attendance Report ----------
    private JPanel buildRightPanel() {
        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(14, 14, 14, 14));

        // ---- Event row + stats box ----
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setOpaque(false);

        JPanel eventRow = new JPanel(new BorderLayout(14, 0));
        eventRow.setOpaque(false);
        JLabel eventLbl = new JLabel("Event:");
        eventLbl.setFont(BASE_FONT);
        eventLbl.setPreferredSize(new Dimension(80, 30));
        eventCombo = new JComboBox<>(new String[]{"Tech Summit (EVT001)"});
        eventCombo.setFont(BASE_FONT);
        eventCombo.setBackground(Color.WHITE);
        eventCombo.setPreferredSize(new Dimension(0, 36));
        eventRow.add(eventLbl, BorderLayout.WEST);
        eventRow.add(eventCombo, BorderLayout.CENTER);
        top.add(eventRow, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridBagLayout());
        stats.setBackground(Color.WHITE);
        stats.setBorder(new CompoundBorder(new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(10, 16, 10, 16)));

        String[] labels = {"Total Registered:", "Total Checked-in:", "Attendance Rate:", "Waiting List:"};
        String[] sample = {"50", "42", "84%", "8"};
        JLabel[] values = new JLabel[4];
        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(6, 4, 6, 4);
        for (int i = 0; i < labels.length; i++) {
            JLabel l = new JLabel(labels[i]);
            l.setFont(BASE_FONT);
            l.setPreferredSize(new Dimension(190, 20));
            g.gridx = 0; g.gridy = i; g.weightx = 0;
            stats.add(l, g);

            values[i] = new JLabel(sample[i]);
            values[i].setFont(BASE_FONT);
            g.gridx = 1; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL;
            stats.add(values[i], g);
            g.fill = GridBagConstraints.NONE;
        }
        totalRegisteredValue = values[0];
        totalCheckedInValue  = values[1];
        attendanceRateValue  = values[2];
        waitingListValue     = values[3];
        top.add(stats, BorderLayout.CENTER);

        body.add(top, BorderLayout.NORTH);

        // ---- Attendee List card ----
        body.add(createCard("Attendee List", buildTableWrapper()), BorderLayout.CENTER);

        return createCard("Attendance Report", body, 16, 18);
    }

    private JComponent buildTableWrapper() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Status"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableModel.addRow(new Object[]{"A001", "Carine Ilagan", "carine@email.com", "Checked-in"});
        tableModel.addRow(new Object[]{"A002", "John Santos",   "john@email.com",   "Checked-in"});
        tableModel.addRow(new Object[]{"A003", "Maria Reyes",   "maria@email.com",  "Checked-in"});
        tableModel.addRow(new Object[]{"A004", "James Cruz",    "james@email.com",  "Not Checked-in"});

        table = new JTable(tableModel);
        table.setFont(BASE_FONT);
        table.setRowHeight(34);
        table.setShowGrid(true);
        table.setGridColor(new Color(0xE3E3EA));
        table.setSelectionBackground(HEADER_BG);
        table.setSelectionForeground(Color.BLACK);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(190);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);

        DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
        cell.setBorder(new EmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cell);

        // Status column: green for "Checked-in", red for "Not Checked-in"
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s,
                                                           boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, c);
                l.setBorder(new EmptyBorder(0, 12, 0, 0));
                if (!s) {
                    l.setForeground("Checked-in".equals(String.valueOf(v)) ? GREEN : RED);
                }
                return l;
            }
        });

        JTableHeader th = table.getTableHeader();
        th.setReorderingAllowed(false);
        th.setPreferredSize(new Dimension(0, 34));
        th.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s,
                                                           boolean f, int r, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, s, f, r, col);
                l.setFont(BOLD_FONT);
                l.setBackground(new Color(0xF1F1F5));
                l.setBorder(new CompoundBorder(
                        new MatteBorder(0, 0, 1, 1, new Color(0xE3E3EA)),
                        new EmptyBorder(0, 12, 0, 0)));
                return l;
            }
        });

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new LineBorder(BORDER_COLOR, 1, true));
        sp.getViewport().setBackground(Color.WHITE);

        JPanel wrap = new JPanel(new BorderLayout());
        wrap.setBackground(Color.WHITE);
        wrap.setBorder(new EmptyBorder(8, 8, 8, 8));
        wrap.add(sp, BorderLayout.CENTER);
        return wrap;
    }

    // ---------- Card helpers (purple-tinted header + bordered body) ----------
    private JPanel createCard(String title, JComponent body) {
        return createCard(title, body, 14, 12);
    }

    private JPanel createCard(String title, JComponent body, int fontSize, int vPad) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(new LineBorder(BORDER_COLOR, 1));

        JLabel header = new JLabel(title);
        header.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        header.setForeground(PURPLE_DARK);
        header.setOpaque(true);
        header.setBackground(HEADER_BG);
        header.setBorder(new EmptyBorder(vPad - 2, 12, vPad - 2, 12));

        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        return card;
    }

    // ====================================================================
    //  Getters (use these later when you add the logic)
    // ====================================================================
    public JRadioButton getAttendanceRadio()    { return attendanceRadio; }
    public JRadioButton getFeedbackRadio()      { return feedbackRadio; }
    public JRadioButton getSummaryRadio()       { return summaryRadio; }
    public JButton getGenerateBtn()             { return generateBtn; }
    public JComboBox<String> getEventCombo()    { return eventCombo; }
    public JLabel getTotalRegisteredValue()     { return totalRegisteredValue; }
    public JLabel getTotalCheckedInValue()      { return totalCheckedInValue; }
    public JLabel getAttendanceRateValue()      { return attendanceRateValue; }
    public JLabel getWaitingListValue()         { return waitingListValue; }
    public JTable getTable()                    { return table; }
    public DefaultTableModel getTableModel()    { return tableModel; }

    // ====================================================================
    //  Custom components
    // ====================================================================
    /** Purple round radio-button icon. */
    private static class RadioIcon implements Icon {
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean selected = ((AbstractButton) c).isSelected();
            if (selected) {
                g2.setColor(PURPLE);
                g2.fillOval(x, y, 16, 16);
                g2.setColor(Color.WHITE);
                g2.fillOval(x + 5, y + 5, 6, 6);
            } else {
                g2.setColor(Color.WHITE);
                g2.fillOval(x, y, 16, 16);
                g2.setColor(new Color(0xB5B5C3));
                g2.drawOval(x, y, 15, 15);
            }
            g2.dispose();
        }
        @Override public int getIconWidth()  { return 16; }
        @Override public int getIconHeight() { return 16; }
    }

    /** Purple rounded button. */
    private static class RoundedButton extends JButton {
        RoundedButton(String text) {
            super(text);
            setFont(BOLD_FONT);
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color bg = PURPLE;
            if (getModel().isPressed())       bg = bg.darker();
            else if (getModel().isRollover()) bg = bg.brighter();
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}