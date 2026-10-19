package GUI;

//hi

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;

public class CheckInGUI extends JFrame {

    private static final Color PRIMARY = new Color(0x4A55A2);
    private static final Color PRIMARY_HOVER = new Color(0x5A66B3);
    private static final Color SECONDARY = new Color(0xE9ECF8);
    private static final Color SECONDARY_HOVER = new Color(0xDCE0F2);
    private static final Color CARD_HEADER = new Color(0xE4E8F5);
    private static final Color WINDOW_BG = new Color(0xEDF0F7);
    private static final Color TABLE_HEADER = new Color(0xF1F3FA);
    private static final Color GRID = new Color(0xE1E5F2);
    private static final Color BORDER = new Color(0xCDD3E8);
    private static final Color TITLE = new Color(0x2F3A6E);
    private static final Color TEXT = new Color(0x2B2F45);
    private static final Color HINT = new Color(0x9AA0BC);
    private static final Color RED = new Color(0xE0314B);

    private static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BOLD = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font BIG = new Font("Segoe UI", Font.BOLD, 16);

    private JComboBox<String> searchByCombo = new JComboBox<>(new String[]{"Name", "ID", "Email"});
    private HintTextField searchField = new HintTextField("Enter name...");
    private RoundedButton searchBtn = new RoundedButton("Search", true);
    private RoundedButton checkInBtn = new RoundedButton("Check-in", true);
    private RoundedButton cancelBtn = new RoundedButton("Cancel", false);

    private JLabel idValue = new JLabel("A001");
    private JLabel nameValue = new JLabel("Carine Ilagan");
    private JLabel emailValue = new JLabel("carine@email.com");
    private JLabel eventValue = new JLabel("Tech Summit (EVT001)");
    private JLabel statusValue = new JLabel("Not Checked In");

    private DefaultTableModel tableModel;
    private JTable table;

    public CheckInGUI() {
        super("Event Management System");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not load system look and feel");
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(12, 12, 12, 12));
        content.add(createSearchPanel());
        content.add(Box.createVerticalStrut(14));
        content.add(createInfoCard());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(content, BorderLayout.NORTH);

        JPanel card = createCard("Check-in Attendee", wrapper, BOLD, 10);
        card.setPreferredSize(new Dimension(430, 0));
        return card;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel searchByLabel = new JLabel("Search by:");
        searchByLabel.setFont(NORMAL);
        searchByLabel.setForeground(TEXT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(searchByLabel, gbc);

        searchByCombo.setFont(NORMAL);
        searchByCombo.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(searchByCombo, gbc);

        searchField.setFont(NORMAL);
        searchField.setForeground(TEXT);
        searchField.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 8, 8, 8)));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(searchField, gbc);

        searchBtn.setPreferredSize(new Dimension(175, 38));
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(searchBtn, gbc);

        return panel;
    }

    private JPanel createInfoCard() {
        JPanel body = new JPanel(new GridBagLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(14, 14, 14, 14));

        addInfoRow(body, 0, "ID:", idValue);
        addInfoRow(body, 1, "Name:", nameValue);
        addInfoRow(body, 2, "Email:", emailValue);
        addInfoRow(body, 3, "Event:", eventValue);
        addInfoRow(body, 4, "Status:", statusValue);
        statusValue.setForeground(RED);

        checkInBtn.setPreferredSize(new Dimension(0, 42));
        cancelBtn.setPreferredSize(new Dimension(0, 42));

        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 14, 0));
        buttonRow.setBackground(Color.WHITE);
        buttonRow.add(checkInBtn);
        buttonRow.add(cancelBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(18, 4, 4, 4);
        body.add(buttonRow, gbc);

        return createCard("Attendee Information", body, BOLD, 10);
    }

    private void addInfoRow(JPanel panel, int row, String labelText, JLabel valueLabel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL);
        label.setForeground(TEXT);
        label.setPreferredSize(new Dimension(70, 20));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        valueLabel.setFont(NORMAL);
        if (valueLabel != statusValue) {
            valueLabel.setForeground(TEXT);
        }
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(valueLabel, gbc);
    }

    private JPanel createRightSide() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addRow(new Object[]{"A002", "John Santos", "09:15 AM"});
        tableModel.addRow(new Object[]{"A003", "Maria Reyes", "09:22 AM"});

        table = new JTable(tableModel);
        table.setFont(NORMAL);
        table.setForeground(TEXT);
        table.setRowHeight(36);
        table.setGridColor(GRID);
        table.setSelectionBackground(CARD_HEADER);
        table.setSelectionForeground(TEXT);
        table.setFillsViewportHeight(true);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(110);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBorder(new EmptyBorder(0, 12, 0, 0));
        table.setDefaultRenderer(Object.class, cellRenderer);

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 36));
        header.setDefaultRenderer(new HeaderRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(BORDER, 1, true));
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(10, 10, 14, 10));
        body.add(scrollPane, BorderLayout.CENTER);

        return createCard("Checked-in Attendees", body, BIG, 18);
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
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);
        return card;
    }

    public JComboBox<String> getSearchByCombo() {
        return searchByCombo;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JButton getCheckInBtn() {
        return checkInBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JLabel getIdValue() {
        return idValue;
    }

    public JLabel getNameValue() {
        return nameValue;
    }

    public JLabel getEmailValue() {
        return emailValue;
    }

    public JLabel getEventValue() {
        return eventValue;
    }

    public JLabel getStatusValue() {
        return statusValue;
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
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

    private static class HintTextField extends JTextField {

        private final String hint;

        public HintTextField(String hint) {
            this.hint = hint;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (getText().isEmpty()) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setColor(HINT);
                g2.setFont(getFont());

                FontMetrics metrics = g2.getFontMetrics();
                int x = getInsets().left;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                g2.drawString(hint, x, y);
                g2.dispose();
            }
        }
    }

    private static class RoundedButton extends JButton {

        private final boolean primary;

        public RoundedButton(String text, boolean primary) {
            super(text);
            this.primary = primary;

            setFont(BOLD);
            setForeground(primary ? Color.WHITE : TITLE);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color color;
            if (primary) {
                color = getModel().isRollover() ? PRIMARY_HOVER : PRIMARY;
            } else {
                color = getModel().isRollover() ? SECONDARY_HOVER : SECONDARY;
            }
            if (getModel().isPressed()) {
                color = color.darker();
            }

            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

            if (!primary) {
                g2.setColor(BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}