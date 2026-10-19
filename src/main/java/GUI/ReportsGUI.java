package GUI;

//ali

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class ReportsGUI extends JFrame {

    private static final Color PRIMARY = new Color(0x4A55A2);
    private static final Color PRIMARY_HOVER = new Color(0x5A66B3);
    private static final Color CARD_HEADER = new Color(0xE4E8F5);
    private static final Color WINDOW_BG = new Color(0xEDF0F7);
    private static final Color TABLE_HEADER = new Color(0xF1F3FA);
    private static final Color GRID = new Color(0xE1E5F2);
    private static final Color BORDER = new Color(0xCDD3E8);
    private static final Color TITLE = new Color(0x2F3A6E);
    private static final Color TEXT = new Color(0x2B2F45);
    private static final Color RADIO_BORDER = new Color(0xB3B9D6);
    private static final Color GREEN = new Color(0x1E8E3E);
    private static final Color RED = new Color(0xE0314B);

    private static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BOLD = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font BIG = new Font("Segoe UI", Font.BOLD, 16);

    private JRadioButton attendanceRadio = createRadio("Attendance Report", true);
    private JRadioButton feedbackRadio = createRadio("Feedback Report", false);
    private JRadioButton summaryRadio = createRadio("Event Summary", false);
    private RoundedButton generateBtn = new RoundedButton("Generate");

    private JComboBox<String> eventCombo = new JComboBox<>(new String[]{"Tech Summit (EVT001)"});
    private JLabel totalRegisteredValue = new JLabel("50");
    private JLabel totalCheckedInValue = new JLabel("42");
    private JLabel attendanceRateValue = new JLabel("84%");
    private JLabel waitingListValue = new JLabel("8");

    private DefaultTableModel tableModel;
    private JTable table;

    public ReportsGUI() {
        super("Event Management System");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not load system look and feel");
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(16, 0));
        mainPanel.setBackground(WINDOW_BG);
        mainPanel.setBorder(new EmptyBorder(14, 14, 14, 14));
        mainPanel.add(createLeftSide(), BorderLayout.WEST);
        mainPanel.add(createRightSide(), BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    private JPanel createLeftSide() {
        ButtonGroup group = new ButtonGroup();
        group.add(attendanceRadio);
        group.add(feedbackRadio);
        group.add(summaryRadio);

        generateBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        generateBtn.setPreferredSize(new Dimension(0, 44));
        generateBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.add(attendanceRadio);
        content.add(Box.createVerticalStrut(14));
        content.add(feedbackRadio);
        content.add(Box.createVerticalStrut(14));
        content.add(summaryRadio);
        content.add(Box.createVerticalStrut(30));
        content.add(generateBtn);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(content, BorderLayout.NORTH);

        JPanel card = createCard("Select Report", wrapper, BOLD, 10);
        card.setPreferredSize(new Dimension(300, 0));
        return card;
    }

    private JRadioButton createRadio(String text, boolean selected) {
        JRadioButton radio = new JRadioButton(text, selected);
        radio.setFont(selected ? BOLD : NORMAL);
        radio.setForeground(TEXT);
        radio.setIcon(new RadioIcon());
        radio.setIconTextGap(12);
        radio.setOpaque(false);
        radio.setFocusPainted(false);
        radio.setAlignmentX(Component.LEFT_ALIGNMENT);
        radio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        radio.addChangeListener(e -> radio.setFont(radio.isSelected() ? BOLD : NORMAL));
        return radio;
    }

    private JPanel createRightSide() {
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setBackground(Color.WHITE);
        top.add(createEventRow(), BorderLayout.NORTH);
        top.add(createStatsBox(), BorderLayout.CENTER);

        JPanel body = new JPanel(new BorderLayout(0, 12));
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(14, 14, 14, 14));
        body.add(top, BorderLayout.NORTH);
        body.add(createAttendeeList(), BorderLayout.CENTER);

        return createCard("Attendance Report", body, BIG, 16);
    }

    private JPanel createEventRow() {
        JLabel eventLabel = new JLabel("Event:");
        eventLabel.setFont(NORMAL);
        eventLabel.setForeground(TEXT);
        eventLabel.setPreferredSize(new Dimension(80, 30));

        eventCombo.setFont(NORMAL);
        eventCombo.setBackground(Color.WHITE);
        eventCombo.setPreferredSize(new Dimension(0, 36));

        JPanel row = new JPanel(new BorderLayout(14, 0));
        row.setBackground(Color.WHITE);
        row.add(eventLabel, BorderLayout.WEST);
        row.add(eventCombo, BorderLayout.CENTER);
        return row;
    }

    private JPanel createStatsBox() {
        JPanel box = new JPanel(new GridBagLayout());
        box.setBackground(Color.WHITE);
        box.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(10, 16, 10, 16)));

        addStatRow(box, 0, "Total Registered:", totalRegisteredValue);
        addStatRow(box, 1, "Total Checked-in:", totalCheckedInValue);
        addStatRow(box, 2, "Attendance Rate:", attendanceRateValue);
        addStatRow(box, 3, "Waiting List:", waitingListValue);
        return box;
    }

    private void addStatRow(JPanel panel, int row, String labelText, JLabel valueLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL);
        label.setForeground(TEXT);
        label.setPreferredSize(new Dimension(190, 20));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        valueLabel.setFont(NORMAL);
        valueLabel.setForeground(TEXT);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(valueLabel, gbc);
    }

    private JPanel createAttendeeList() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addRow(new Object[]{"A001", "Carine Ilagan", "carine@email.com", "Checked-in"});
        tableModel.addRow(new Object[]{"A002", "John Santos", "john@email.com", "Checked-in"});
        tableModel.addRow(new Object[]{"A003", "Maria Reyes", "maria@email.com", "Checked-in"});
        tableModel.addRow(new Object[]{"A004", "James Cruz", "james@email.com", "Not Checked-in"});

        table = new JTable(tableModel);
        table.setFont(NORMAL);
        table.setForeground(TEXT);
        table.setRowHeight(34);
        table.setGridColor(GRID);
        table.setSelectionBackground(CARD_HEADER);
        table.setSelectionForeground(TEXT);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(190);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBorder(new EmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 34));
        header.setDefaultRenderer(new HeaderRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(BORDER, 1, true));
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(8, 8, 8, 8));
        body.add(scrollPane, BorderLayout.CENTER);

        return createCard("Attendee List", body, BOLD, 10);
    }

    private JPanel createCard(String title, JComponent body, Font titleFont, int headerPadding) {
        JLabel header = new JLabel(title);
        header.setFont(titleFont);
        header.setForeground(TITLE);
        header.setOpaque(true);
        header.setBackground(CARD_HEADER);
        header.setBorder(new EmptyBorder(headerPadding, 12, headerPadding, 12));

        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(new LineBorder(BORDER, 1));
        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        return card;
    }

    public JRadioButton getAttendanceRadio() {
        return attendanceRadio;
    }

    public JRadioButton getFeedbackRadio() {
        return feedbackRadio;
    }

    public JRadioButton getSummaryRadio() {
        return summaryRadio;
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JComboBox<String> getEventCombo() {
        return eventCombo;
    }

    public JLabel getTotalRegisteredValue() {
        return totalRegisteredValue;
    }

    public JLabel getTotalCheckedInValue() {
        return totalCheckedInValue;
    }

    public JLabel getAttendanceRateValue() {
        return attendanceRateValue;
    }

    public JLabel getWaitingListValue() {
        return waitingListValue;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    private static class StatusRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            label.setBorder(new EmptyBorder(0, 12, 0, 0));
            if (!isSelected) {
                label.setForeground("Checked-in".equals(String.valueOf(value)) ? GREEN : RED);
            }
            return label;
        }
    }

    private static class HeaderRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            label.setFont(BOLD);
            label.setForeground(TEXT);
            label.setBackground(TABLE_HEADER);
            label.setBorder(new CompoundBorder(
                    new MatteBorder(0, 0, 1, 1, GRID),
                    new EmptyBorder(0, 12, 0, 0)));
            return label;
        }
    }

    private static class RadioIcon implements Icon {

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (((AbstractButton) c).isSelected()) {
                g2.setColor(PRIMARY);
                g2.fillOval(x, y, 16, 16);
                g2.setColor(Color.WHITE);
                g2.fillOval(x + 5, y + 5, 6, 6);
            } else {
                g2.setColor(Color.WHITE);
                g2.fillOval(x, y, 16, 16);
                g2.setColor(RADIO_BORDER);
                g2.drawOval(x, y, 15, 15);
            }
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 16;
        }
    }

    private static class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setFont(BOLD);
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color color = getModel().isRollover() ? PRIMARY_HOVER : PRIMARY;
            if (getModel().isPressed()) {
                color = color.darker();
            }

            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}