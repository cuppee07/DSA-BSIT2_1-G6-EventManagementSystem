/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendeeManager {

    private final String URL =
            "jdbc:mysql://localhost:3306/event_db";

    private final String USER = "root";

    private final String PASSWORD = "tisoy062607";

    // Database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }

    // Add attendee
    public boolean addAttendee(
            String id, String name, String email, String event) {

        String sql = "INSERT INTO attendees "
                + "(attendee_id, name, email, event_name) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, event);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all attendees
    public List<String[]> getAllAttendees() {

        List<String[]> attendees = new ArrayList<>();

        String sql = "SELECT * FROM attendees";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                attendees.add(new String[]{
                    rs.getString("attendee_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("event_name")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendees;
    }
    
    
public boolean updateAttendee(
        String oldId, String id, String name,
        String email, String event) {

    String sql = "UPDATE attendees SET attendee_id=?, "
            + "name=?, email=?, event_name=? "
            + "WHERE attendee_id=?";

    try (Connection conn = getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, id);
        pst.setString(2, name);
        pst.setString(3, email);
        pst.setString(4, event);
        pst.setString(5, oldId);

        return pst.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean deleteAttendee(String id) {

    String sql = "DELETE FROM attendees WHERE attendee_id=?";

    try (Connection conn = getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setString(1, id);

        return pst.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
