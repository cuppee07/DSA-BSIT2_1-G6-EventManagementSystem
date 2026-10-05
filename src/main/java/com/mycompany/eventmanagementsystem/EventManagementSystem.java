/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

/**
 * hello i'm manabat
 *
 * @author Carine llance is here lee is here
 */
import javax.swing.SwingUtilities;

public class EventManagementSystem {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CheckInGUI checkIn = new CheckInGUI();
            ReportsGUI reports = new ReportsGUI();

            checkIn.setVisible(true);
            reports.setVisible(true);
        });
    }
}