package GUI;

//ali

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportsGUI extends JFrame {

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
    private static final Color RADIO_BORDER = new Color(0xB3B9D6);
    private static final Color GREEN = new Color(0x1E8E3E);
    private static final Color RED = new Color(0xE0314B);

    private static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BOLD = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font BIG = new Font("Segoe UI", Font.BOLD, 16);

    private JRadioButton attendanceRadio = createRadio("Attendance Report", true);
    private JRadioButton summaryRadio = createRadio("Event Summary", false);
    private RoundedButton generateBtn = new RoundedButton("Generate");
    private RoundedButton backBtn = new RoundedButton("Back", false);

    private JComboBox<String> eventCombo = new JComboBox<>();
    private JLabel totalRegisteredValue = new JLabel("-");
    private JLabel totalCheckedInValue = new JLabel("-");
    private JLabel attendanceRateValue = new JLabel("-");
    private JLabel waitingListValue = new JLabel("-");

    private static final String ATTENDANCE_VIEW = "Attendance Report";
    private static final String SUMMARY_VIEW = "Event Summary";

    private DefaultTableModel tableModel;      // attendee list
    private JTable table;

    // event dropdown of the Attendance Report (Event Summary has its own: eventCombo)
    private JComboBox<String> attendanceEventCombo = new JComboBox<>();

    private final List<EventInfo> events = new ArrayList<>();
    private boolean selectingEvent; // stops the two dropdowns from triggering each other

    private JLabel attendeeHeader; // title of the Attendee List card
    // event ID -> the attendees registered for that event: {ID, Name, Email, Status}
    private final Map<String, List<Object[]>> attendeesByEvent = new LinkedHashMap<>();

    private JPanel reportCards;  // switches between the two report views
    private JLabel rightHeader;  // title of the right-side card

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

        backBtn.addActionListener(e -> goBack());

        // switch the right side when a report is selected
        attendanceRadio.addActionListener(e -> showReport(ATTENDANCE_VIEW));
        summaryRadio.addActionListener(e -> showReport(SUMMARY_VIEW));
    }

    /** Shows the chosen report on the right side and updates its title. */
    private void showReport(String view) {
        ((CardLayout) reportCards.getLayout()).show(reportCards, view);
        rightHeader.setText(view);
    }

    /** Closes this screen and goes back to the Dashboard. */
    private void goBack() {
        dispose();
        new Dashboard(); // Dashboard shows itself in its constructor
    }

    private JPanel createLeftSide() {
        ButtonGroup group = new ButtonGroup();
        group.add(attendanceRadio);
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
        content.add(summaryRadio);
        content.add(Box.createVerticalStrut(30));
        content.add(generateBtn);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(content, BorderLayout.NORTH);

        backBtn.setPreferredSize(new Dimension(110, 38));
        JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomRow.setBackground(Color.WHITE);
        bottomRow.setBorder(new EmptyBorder(0, 20, 20, 20));
        bottomRow.add(backBtn);
        wrapper.add(bottomRow, BorderLayout.SOUTH);

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
        loadEvents();

        reportCards = new JPanel(new CardLayout());
        reportCards.setBackground(Color.WHITE);
        reportCards.add(createAttendanceView(), ATTENDANCE_VIEW);
        reportCards.add(createSummaryView(), SUMMARY_VIEW);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(14, 14, 14, 14));
        body.add(reportCards, BorderLayout.CENTER);

        JPanel card = createCard(ATTENDANCE_VIEW, body, BIG, 16);
        rightHeader = (JLabel) ((BorderLayout) card.getLayout()).getLayoutComponent(BorderLayout.NORTH);

        // choosing an event in either dropdown updates both reports
        java.awt.event.ActionListener onPick = e -> {
            if (!selectingEvent) {
                selectEvent(((JComboBox<?>) e.getSource()).getSelectedIndex());
            }
        };
        attendanceEventCombo.addActionListener(onPick);
        eventCombo.addActionListener(onPick);
        selectEvent(0); // first event by default

        return card;
    }

    /** Attendance Report: event dropdown on top (like Event Summary), attendee list below. */
    private JPanel createAttendanceView() {
        JPanel view = new JPanel(new BorderLayout(0, 12));
        view.setBackground(Color.WHITE);
        view.add(createEventRow(attendanceEventCombo), BorderLayout.NORTH);
        view.add(createAttendeeList(), BorderLayout.CENTER);
        return view;
    }

    /** Event Summary: event picker and the totals. */
    private JPanel createSummaryView() {
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setBackground(Color.WHITE);
        top.add(createEventRow(eventCombo), BorderLayout.NORTH);
        top.add(createStatsBox(), BorderLayout.CENTER);

        JPanel view = new JPanel(new BorderLayout());
        view.setBackground(Color.WHITE);
        view.add(top, BorderLayout.NORTH);
        return view;
    }

    private JPanel createEventRow(JComboBox<String> eventCombo) {
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
        addStatRow(panel, row, labelText, valueLabel, 190);
    }

    private void addStatRow(JPanel panel, int row, String labelText, JLabel valueLabel, int labelWidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 4, 6, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(labelText);
        label.setFont(NORMAL);
        label.setForeground(TEXT);
        label.setPreferredSize(new Dimension(labelWidth, 20));
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

    /** The events in the dropdown. They start with no participants. */
    private void loadEvents() {
        events.add(new EventInfo("EVT001", "Tech Summit"));
        events.add(new EventInfo("EVT002", "Art Workshop"));
        events.add(new EventInfo("EVT003", "Business Forum"));

        for (EventInfo ev : events) {
            attendeesByEvent.put(ev.id, new ArrayList<>());
            String label = ev.name + " (" + ev.id + ")";
            eventCombo.addItem(label);
            attendanceEventCombo.addItem(label);
        }
    }

    // ================= INPUT (call these to put data in the reports) =================

    /** Adds a new event to both dropdowns. */
    public void addEvent(String id, String name) {
        events.add(new EventInfo(id, name));
        attendeesByEvent.put(id, new ArrayList<>());

        String label = name + " (" + id + ")";
        selectingEvent = true;
        eventCombo.addItem(label);
        attendanceEventCombo.addItem(label);
        selectingEvent = false;

        if (events.size() == 1) {
            selectEvent(0);
        }
    }

    /** Adds a participant to an event, then refreshes the reports. */
    public void addAttendee(String eventId, String id, String name, String email, boolean checkedIn) {
        List<Object[]> list = attendeesByEvent.get(eventId);
        if (list == null) {
            return; // unknown event
        }
        list.add(new Object[]{id, name, email, checkedIn ? "Checked-in" : "Not Checked-in"});
        refreshSelectedEvent();
    }

    /** Sets how many people are on an event's waiting list. */
    public void setWaitingList(String eventId, int count) {
        for (EventInfo ev : events) {
            if (ev.id.equals(eventId)) {
                ev.waiting = count;
            }
        }
        refreshSelectedEvent();
    }

    private void refreshSelectedEvent() {
        selectEvent(attendanceEventCombo.getSelectedIndex());
    }

    /** Shows the chosen event everywhere: attendee list, event details and summary totals. */
    private void selectEvent(int index) {
        if (index < 0 || index >= events.size()) {
            return;
        }
        EventInfo ev = events.get(index);

        selectingEvent = true;
        attendanceEventCombo.setSelectedIndex(index);
        eventCombo.setSelectedIndex(index);
        selectingEvent = false;

        // Attendee List
        List<Object[]> rows = attendeesByEvent.get(ev.id);
        int total = 0;
        int checkedIn = 0;
        tableModel.setRowCount(0);
        if (rows != null) {
            for (Object[] row : rows) {
                tableModel.addRow(row);
                total++;
                if ("Checked-in".equals(row[3])) {
                    checkedIn++;
                }
            }
        }
        attendeeHeader.setText("Attendee List - " + ev.name);

        // Event Summary totals
        totalRegisteredValue.setText(String.valueOf(total));
        totalCheckedInValue.setText(String.valueOf(checkedIn));
        attendanceRateValue.setText(total == 0 ? "0%" : Math.round(100.0 * checkedIn / total) + "%");
        waitingListValue.setText(String.valueOf(ev.waiting));
    }

    private JPanel createAttendeeList() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        styleTable(table, 70, 170, 190, 130);
        table.getColumnModel().getColumn(3).setCellRenderer(new StatusRenderer());

        JPanel card = createTableCard("Attendee List", table);
        attendeeHeader = (JLabel) ((BorderLayout) card.getLayout()).getLayoutComponent(BorderLayout.NORTH);
        return card;
    }

    /** Shared look for both tables. */
    private void styleTable(JTable t, int... columnWidths) {
        t.setFont(NORMAL);
        t.setForeground(TEXT);
        t.setRowHeight(34);
        t.setGridColor(GRID);
        t.setSelectionBackground(CARD_HEADER);
        t.setSelectionForeground(TEXT);
        t.setFillsViewportHeight(true);
        t.setIntercellSpacing(new Dimension(0, 1));
        for (int i = 0; i < columnWidths.length; i++) {
            t.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBorder(new EmptyBorder(0, 12, 0, 0));
        t.setDefaultRenderer(Object.class, cellRenderer);

        JTableHeader header = t.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 34));
        header.setDefaultRenderer(new HeaderRenderer());
    }

    private JPanel createTableCard(String title, JTable t) {
        JScrollPane scrollPane = new JScrollPane(t);
        scrollPane.setBorder(new LineBorder(BORDER, 1, true));
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel body = new JPanel(new BorderLayout());
        body.setBackground(Color.WHITE);
        body.setBorder(new EmptyBorder(8, 8, 8, 8));
        body.add(scrollPane, BorderLayout.CENTER);

        return createCard(title, body, BOLD, 10);
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

    public JRadioButton getSummaryRadio() {
        return summaryRadio;
    }

    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
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

    public JComboBox<String> getAttendanceEventCombo() {
        return attendanceEventCombo;
    }

    private static class EventInfo {

        final String id;
        final String name;
        int waiting = 0; // size of the waiting list

        EventInfo(String id, String name) {
            this.id = id;
            this.name = name;
        }
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

        private final boolean primary;

        public RoundedButton(String text) {
            this(text, true);
        }

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