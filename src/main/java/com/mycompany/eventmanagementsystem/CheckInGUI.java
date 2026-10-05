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

public class CheckInGUI extends JFrame {

    // Runs once, before the first CheckInGUI is created
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
    private static final Font  BASE_FONT    = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font  BOLD_FONT    = new Font("Segoe UI", Font.BOLD, 14);

    // ====== Components (public getters at the bottom, for adding logic later) ======
    private JComboBox<String> searchByCombo;
    private HintTextField searchField;
    private RoundedButton searchBtn, checkInBtn, cancelBtn;
    private JLabel idValue, nameValue, emailValue, eventValue, statusValue;
    private JTable table;
    private DefaultTableModel tableModel;

    public CheckInGUI() {
        super("Event Management System");
        buildUI();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    // ---------- LEFT SIDE ----------
    private JPanel buildLeftPanel() {
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setOpaque(false);
        left.setPreferredSize(new Dimension(430, 0));

        // ---- Check-in Attendee card ----
        JPanel searchBody = new JPanel(new GridBagLayout());
        searchBody.setBackground(Color.WHITE);
        searchBody.setBorder(new EmptyBorder(14, 14, 14, 14));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel searchByLbl = new JLabel("Search by:");
        searchByLbl.setFont(BASE_FONT);
        c.gridx = 0; c.gridy = 0; c.weightx = 0;
        searchBody.add(searchByLbl, c);

        searchByCombo = new JComboBox<>(new String[]{"Name", "ID", "Email"});
        searchByCombo.setFont(BASE_FONT);
        searchByCombo.setBackground(Color.WHITE);
        c.gridx = 1; c.gridy = 0; c.weightx = 1;
        searchBody.add(searchByCombo, c);

        searchField = new HintTextField("Enter name...");
        searchField.setFont(BASE_FONT);
        searchField.setBorder(new CompoundBorder(new LineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(8, 8, 8, 8)));
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.weightx = 1;
        searchBody.add(searchField, c);

        searchBtn = new RoundedButton("Search", true);
        searchBtn.setPreferredSize(new Dimension(175, 38));
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; c.weightx = 0;
        c.fill = GridBagConstraints.NONE; c.anchor = GridBagConstraints.EAST;
        searchBody.add(searchBtn, c);

        JPanel searchCard = createCard("Check-in Attendee", searchBody);

        // ---- Attendee Information card ----
        JPanel infoBody = new JPanel(new GridBagLayout());
        infoBody.setBackground(Color.WHITE);
        infoBody.setBorder(new EmptyBorder(14, 14, 14, 14));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 4, 6, 4);
        g.anchor = GridBagConstraints.WEST;

        String[] labels = {"ID:", "Name:", "Email:", "Event:", "Status:"};
        String[] sample = {"A001", "Carine Ilagan", "carine@email.com", "Tech Summit (EVT001)", "Not Checked In"};
        JLabel[] values = new JLabel[5];
        for (int i = 0; i < labels.length; i++) {
            JLabel l = new JLabel(labels[i]);
            l.setFont(BASE_FONT);
            l.setPreferredSize(new Dimension(70, 20));
            g.gridx = 0; g.gridy = i; g.weightx = 0;
            infoBody.add(l, g);

            values[i] = new JLabel(sample[i]);
            values[i].setFont(BASE_FONT);
            g.gridx = 1; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL;
            infoBody.add(values[i], g);
            g.fill = GridBagConstraints.NONE;
        }
        idValue = values[0]; nameValue = values[1]; emailValue = values[2];
        eventValue = values[3]; statusValue = values[4];
        statusValue.setForeground(RED);

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 14, 0));
        btnRow.setOpaque(false);
        checkInBtn = new RoundedButton("Check-in", true);
        cancelBtn  = new RoundedButton("Cancel", false);
        checkInBtn.setPreferredSize(new Dimension(0, 42));
        cancelBtn.setPreferredSize(new Dimension(0, 42));
        btnRow.add(checkInBtn);
        btnRow.add(cancelBtn);

        g.gridx = 0; g.gridy = 5; g.gridwidth = 2; g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(18, 4, 4, 4);
        infoBody.add(btnRow, g);

        JPanel infoCard = createCard("Attendee Information", infoBody);

        left.add(searchCard);
        left.add(Box.createVerticalStrut(14));
        left.add(infoCard);
        left.add(Box.createVerticalGlue());
        return left;
    }

    // ---------- RIGHT SIDE ----------
    private JPanel buildRightPanel() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(BORDER_COLOR, 1));

        JLabel header = new JLabel("Checked-in Attendees");
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setForeground(PURPLE_DARK);
        header.setOpaque(true);
        header.setBackground(HEADER_BG);
        header.setBorder(new EmptyBorder(18, 14, 18, 14));
        card.add(header, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Time"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableModel.addRow(new Object[]{"A002", "John Santos", "09:15 AM"});
        tableModel.addRow(new Object[]{"A003", "Maria Reyes", "09:22 AM"});

        table = new JTable(tableModel);
        table.setFont(BASE_FONT);
        table.setRowHeight(36);
        table.setShowGrid(true);
        table.setGridColor(new Color(0xE3E3EA));
        table.setSelectionBackground(HEADER_BG);
        table.setSelectionForeground(Color.BLACK);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);

        DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
        cell.setBorder(new EmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cell);

        JTableHeader th = table.getTableHeader();
        th.setReorderingAllowed(false);
        th.setPreferredSize(new Dimension(0, 36));
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
        wrap.setBorder(new EmptyBorder(10, 10, 14, 10));
        wrap.add(sp, BorderLayout.CENTER);
        card.add(wrap, BorderLayout.CENTER);

        return card;
    }

    // ---------- Card helper (purple-tinted header + bordered body) ----------
    private JPanel createCard(String title, JComponent body) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(new LineBorder(BORDER_COLOR, 1));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel header = new JLabel(title);
        header.setFont(BOLD_FONT);
        header.setForeground(PURPLE_DARK);
        header.setOpaque(true);
        header.setBackground(HEADER_BG);
        header.setBorder(new EmptyBorder(10, 12, 10, 12));

        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, card.getPreferredSize().height));
        return card;
    }

    // ====================================================================
    //  Getters (use these later when you add the logic)
    // ====================================================================
    public JComboBox<String> getSearchByCombo() { return searchByCombo; }
    public JTextField getSearchField()          { return searchField; }
    public JButton getSearchBtn()               { return searchBtn; }
    public JButton getCheckInBtn()              { return checkInBtn; }
    public JButton getCancelBtn()               { return cancelBtn; }
    public JLabel getIdValue()                  { return idValue; }
    public JLabel getNameValue()                { return nameValue; }
    public JLabel getEmailValue()               { return emailValue; }
    public JLabel getEventValue()               { return eventValue; }
    public JLabel getStatusValue()              { return statusValue; }
    public JTable getTable()                    { return table; }
    public DefaultTableModel getTableModel()    { return tableModel; }

    // ====================================================================
    //  Custom components
    // ====================================================================
    /** Text field that shows grey hint text when empty. */
    private static class HintTextField extends JTextField {
        private final String hint;
        HintTextField(String hint) { this.hint = hint; }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setColor(new Color(0x9A9AA5));
                g2.setFont(getFont());
                Insets in = getInsets();
                g2.drawString(hint, in.left,
                        (getHeight() - g2.getFontMetrics().getHeight()) / 2
                                + g2.getFontMetrics().getAscent());
                g2.dispose();
            }
        }
    }

    /** Rounded button: filled purple (primary) or light grey (secondary). */
    private static class RoundedButton extends JButton {
        private final boolean primary;
        RoundedButton(String text, boolean primary) {
            super(text);
            this.primary = primary;
            setFont(BOLD_FONT);
            setForeground(primary ? Color.WHITE : new Color(0x333333));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color bg = primary ? PURPLE : new Color(0xEEEBF2);
            if (getModel().isPressed())       bg = bg.darker();
            else if (getModel().isRollover()) bg = primary ? PURPLE.brighter() : new Color(0xE2DEE9);
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
            if (!primary) {
                g2.setColor(BORDER_COLOR);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}