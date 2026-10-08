/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.eventmanagementsystem;

    import java.awt.BorderLayout;
    import java.awt.Color;
    import java.awt.Font;
    import java.awt.GridLayout;
    import java.awt.Graphics2D;
    import java.awt.Insets;
    import java.awt.RenderingHints;
    import java.awt.image.BufferedImage;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    import javax.imageio.ImageIO;
    import javax.swing.BorderFactory;
    import javax.swing.ImageIcon;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JOptionPane;
    import javax.swing.JPanel;
    import javax.swing.SwingConstants;


    public class Dashboard extends JFrame implements ActionListener {
        private JPanel sidebar;
        private JPanel mainPanel;
        private JPanel cardPanel;

        private JLabel lblWelcome;
        private JLabel lblSubtitle;

        private JButton btnMenu;
        private JButton btnDashboard;
        private JButton btnEvents;
        private JButton btnAttendees;
        private JButton btnRegistration;
        private JButton btnCheckIn;
        private JButton btnWaitingList;
        private JButton btnReports;
        private JButton btnLogout;

        private JButton cardEvents;
        private JButton cardAttendees;
        private JButton cardRegistration;
        private JButton cardCheckIn;
        private JButton cardWaitingList;
        private JButton cardReports;

        Color NAVY = new Color(41, 54, 80);
        Color LAVENDER = new Color(128, 100, 162);
        Color LIGHT_LAVENDER = new Color(240, 235, 247);
        Color SOFT_LAVENDER = new Color(228, 220, 240);
        Color TEXT = new Color(41, 49, 61);
        Color GRAY = new Color(107, 111, 120);
        Color BORDER = new Color(212, 201, 226);

        Dashboard() {
            setTitle("Event Management System");
            setSize(1000, 600);
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            sidebar = new JPanel();
            sidebar.setPreferredSize(new java.awt.Dimension(220, 0));
            sidebar.setLayout(null);
            sidebar.setBackground(LIGHT_LAVENDER);

            btnDashboard = createSideBarButton(
                    "Dashboard",
                    "dashboard.png",
                    35
            );
            btnEvents = createSideBarButton(
                    "Manage Events",
                    "events.png",
                    90
            );
            btnAttendees = createSideBarButton(
                    "Manage Attendees",
                    "attendees.png",
                    145
            );
            btnRegistration = createSideBarButton(
                    "Registration",
                    "registration.png",
                    200
            );
            btnCheckIn = createSideBarButton(
                    "Check-in",
                    "check-in.png",
                    255
            );
            btnWaitingList = createSideBarButton(
                    "Waiting List",
                    "waiting.png",
                    310
            );

            btnReports = createSideBarButton(
                    "Reports",
                    "reports.png",
                    365
            );
            btnLogout = createSideBarButton(
                    "Logout",
                    "logout.png",
                    500
            );
            
            btnDashboard.setFont(new Font("Arial", Font.BOLD, 14));
            btnDashboard.setBackground(SOFT_LAVENDER);
            btnDashboard.setForeground(NAVY);

            sidebar.add(btnDashboard);
            sidebar.add(btnEvents);
            sidebar.add(btnAttendees);
            sidebar.add(btnRegistration);
            sidebar.add(btnCheckIn);
            sidebar.add(btnWaitingList);
            sidebar.add(btnReports);
            sidebar.add(btnLogout);

            mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(Color.WHITE);

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(Color.WHITE);
            topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 20));

            btnMenu = new JButton();
            btnMenu.setIcon(resizeIcon("menu.png", 35, 35));
            btnMenu.setBackground(Color.WHITE);
            btnMenu.setBorderPainted(false);
            btnMenu.setFocusPainted(false);
            btnMenu.setContentAreaFilled(false);

            btnMenu.addActionListener(e -> toggleSidebar());
            
            topPanel.add(btnMenu, BorderLayout.WEST);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel,javax.swing.BoxLayout.Y_AXIS));
            titlePanel.setBackground(Color.WHITE);
            titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
            
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBackground(Color.WHITE);
            
            lblWelcome = new JLabel("Welcome, Admin!");
            lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
            lblWelcome.setForeground(NAVY);

            lblSubtitle = new JLabel("How can I help you today?");
            lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 14));
            lblSubtitle.setForeground(GRAY);

            titlePanel.add(lblWelcome);
            titlePanel.add(lblSubtitle);

            cardPanel = new JPanel(new GridLayout(2, 3, 20, 20));
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));

            cardEvents = createCardButton(
                    "Manage Events",
                    "events.png"
            );
            cardAttendees = createCardButton(
                    "Manage Attendees",
                    "attendees.png"
            );
            cardRegistration = createCardButton(
                    "Registration",
                    "registration.png"
            );
            cardCheckIn = createCardButton(
                    "Check-in",
                    "check-in.png"
            );
            cardWaitingList = createCardButton(
                    "Waiting List",
                    "waiting.png"
            );
            cardReports = createCardButton(
                    "Reports",
                    "reports.png"
            );

            cardPanel.add(cardEvents);
            cardPanel.add(cardAttendees);
            cardPanel.add(cardRegistration);

            cardPanel.add(cardCheckIn);
            cardPanel.add(cardWaitingList);
            cardPanel.add(cardReports);
            
            contentPanel.add(titlePanel, BorderLayout.NORTH);
            contentPanel.add(cardPanel, BorderLayout.CENTER);

            mainPanel.add(topPanel, BorderLayout.NORTH);
            mainPanel.add(contentPanel, BorderLayout.CENTER);

            add(sidebar, BorderLayout.WEST);
            add(mainPanel, BorderLayout.CENTER);

            sidebar.setVisible(false);

            setVisible(true);
        }

        private void toggleSidebar() {
            sidebar.setVisible(!sidebar.isVisible());

            revalidate();
            repaint();
        }
        
        private void setSelectedButton(JButton selected) {

            JButton[] buttons = {
                btnDashboard,
                btnEvents,
                btnAttendees,
                btnRegistration,
                btnCheckIn,
                btnWaitingList,
                btnReports,
                btnLogout
            };

            for (JButton button : buttons) {
                button.setFont(new Font("Arial", Font.PLAIN, 13));
                button.setBackground(LIGHT_LAVENDER);
            }

            selected.setFont(new Font("Arial", Font.BOLD, 13));
            selected.setBackground(SOFT_LAVENDER);
        }
        
        private JButton createSideBarButton(String text, String iconName, int y){
            JButton button = new JButton(text);
            button.setBounds(10, y, 200, 40);
            button.setFont(new Font("Arial", Font.PLAIN, 13));

            button.setBackground(LIGHT_LAVENDER);
            button.setForeground(NAVY);

            button.setBorderPainted(false);
            button.setFocusPainted(false);

            button.setHorizontalAlignment(SwingConstants.LEFT);

            ImageIcon icon = resizeIcon(iconName, 30, 30);

            button.setIcon(icon);
            button.setIconTextGap(12);

            button.addActionListener(this);

            return button;
        }

        private JButton createCardButton(String text,String iconName){
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 16));

            button.setBackground(LIGHT_LAVENDER);
            button.setForeground(NAVY);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createLineBorder(BORDER));

            ImageIcon icon = resizeIcon(iconName, 55, 55);

            button.setIcon(icon);
            button.setText(text);
            
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setIconTextGap(10);

            button.addActionListener(this);

            return button;
        }

        private ImageIcon resizeIcon(String iconName, int width, int height){
            java.net.URL iconURL = getClass().getResource("/icons/" + iconName);
            if (iconURL == null) {
                System.out.println("Icon not found: " + iconName);
                return null;
            }
            try {
                BufferedImage original = ImageIO.read(iconURL);
                int minX = original.getWidth();
                int minY = original.getHeight();

                int maxX = -1;
                int maxY = -1;

                for (int y = 0; y < original.getHeight(); y++){
                    for (int x = 0; x < original.getWidth(); x++){
                        int alpha = (original.getRGB(x, y) >> 24) & 0xff;
                        if (alpha > 10) {
                            minX = Math.min(minX, x);
                            minY = Math.min(minY, y);

                            maxX = Math.max(maxX, x);
                            maxY = Math.max(maxY, y);
                        }
                    }
                }
                if (maxX == -1) {
                    return null;
                }
                BufferedImage cropped = original.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
                int padding = 2;
                int targetWidth = width - (padding * 2);
                int targetHeight = height - (padding * 2);
                double scale = Math.min((double) targetWidth/cropped.getWidth(),(double) targetHeight/cropped.getHeight());
                int newWidth =(int)(cropped.getWidth()* scale);
                int newHeight =(int)(cropped.getHeight()* scale);
                BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = resized.createGraphics();
                g2.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC
                );
                g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY
                );
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );
                int x = (width - newWidth) / 2;
                int y = (height - newHeight) / 2;
                g2.drawImage(
                        cropped,
                        x,
                        y,
                        newWidth,
                        newHeight,
                        null
                );
                g2.dispose();
                return new ImageIcon(resized);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDashboard) {
                setSelectedButton(btnDashboard);
                JOptionPane.showMessageDialog(
                        this,
                        "Dashboard"
                );
            } 
            else if (e.getSource() == btnEvents || e.getSource() == cardEvents){
                setSelectedButton(btnEvents);
                JOptionPane.showMessageDialog(
                        this,
                        "Manage Events"
                );
            } 
            else if(e.getSource() == btnAttendees || e.getSource() == cardAttendees){
                setSelectedButton(btnAttendees);
                JOptionPane.showMessageDialog(
                        this,
                        "Manage Attendees"
                );
            } 
            else if(e.getSource() == btnRegistration || e.getSource() == cardRegistration){
                setSelectedButton(btnRegistration);
                JOptionPane.showMessageDialog(
                        this,
                        "Registration"
                );
            } 
            else if(e.getSource() == btnCheckIn || e.getSource() == cardCheckIn){
                setSelectedButton(btnCheckIn);
                JOptionPane.showMessageDialog(
                        this,
                        "Check-in"
                );
            }
            else if(e.getSource() == btnWaitingList || e.getSource() == cardWaitingList){
                setSelectedButton(btnWaitingList);
                JOptionPane.showMessageDialog(
                        this,
                        "Waiting List"
                );
            }
            else if(e.getSource() == btnReports || e.getSource() == cardReports){
                setSelectedButton(btnReports);
                JOptionPane.showMessageDialog(
                        this,
                        "Reports"
                );
            }
            else if(e.getSource() == btnLogout) {
                setSelectedButton(btnLogout);
                int confirm =
                    JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dispose();
                    }
            }
        }
    }


