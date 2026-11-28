package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final String URL =
            "jdbc:mysql://localhost:3306/event_db";

    private final String USER = "root";

    private final String PASSWORD = "*Cuppee07*";

    // Database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }

    // Add event
    public boolean addEvent(
            String id, String name, String date,
            String category, int attendees) {

        String sql = "INSERT INTO events "
                + "(event_id, event_name, event_date, category, attendees) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, date);
            pst.setString(4, category);
            pst.setInt(5, attendees);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all events
    public List<String[]> getAllEvents() {

        List<String[]> events = new ArrayList<>();

        String sql = "SELECT * FROM events";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                events.add(new String[]{
                    rs.getString("event_id"),
                    rs.getString("event_name"),
                    rs.getString("event_date"),
                    rs.getString("category"),
                    rs.getString("attendees")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    // Update event
    public boolean updateEvent(
            String oldId, String id, String name,
            String date, String category) {

        String sql = "UPDATE events SET event_id=?, "
                + "event_name=?, event_date=?, category=? "
                + "WHERE event_id=?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, date);
            pst.setString(4, category);
            pst.setString(5, oldId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete event
    public boolean deleteEvent(String id) {

        String sql = "DELETE FROM events WHERE event_id=?";

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