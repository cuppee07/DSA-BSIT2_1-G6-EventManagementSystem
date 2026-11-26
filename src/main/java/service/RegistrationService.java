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
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Rowena
 */
public class RegistrationService {
    
    private final String URL =
            "jdbc:mysql://localhost:3306/event_db";

    private final String USER = "root";

    private final String PASSWORD = "*Cuppee07*";
    
    
     public static class RegistrationRecord {
        private final String registrationId;
        private final String attendeeName;
        private final String eventName;
        private final String status;

        public RegistrationRecord(String registrationId, String attendeeName, String eventName, String status) {
            this.registrationId = registrationId;
            this.attendeeName = attendeeName;
            this.eventName = eventName;
            this.status = status;
        }

        public String getRegistrationId() {
            return registrationId;
        }

        public String getAttendeeName() {
            return attendeeName;
        }

        public String getEventName() {
            return eventName;
        }

        public String getStatus() {
            return status;
        }
    }

    private final LinkedList<RegistrationRecord> registrations = new LinkedList<>();

    public RegistrationService() {
        loadConfirmedFromDatabase();
    }

    /**
     * Registers an attendee in memory (LinkedList) with "Pending Check-In" status.
     * Not saved to the database until confirmCheckIn() is called.
     */
    public boolean registerAttendee(String registrationId, String attendeeName, String eventName) {
        if (isBlank(registrationId) || isBlank(attendeeName) || isBlank(eventName)) {
            return false;
        }

        String cleanId = registrationId.trim();
        if (findIndexById(cleanId) != -1) {
            return false; // Duplicate registration ID
        }

        registrations.add(new RegistrationRecord(
                cleanId,
                attendeeName.trim(),
                eventName.trim(),
                "Pending Check-In"
        ));
        return true;
    }

    /**
     * Confirms check-in from the Check-In panel and persists the record to MySQL (event_db).
     */
    public boolean confirmCheckIn(String registrationId) {
        if (isBlank(registrationId)) {
            return false;
        }

        int index = findIndexById(registrationId.trim());
        if (index == -1) {
            return false;
        }

        RegistrationRecord current = registrations.get(index);
        if ("Checked-In".equalsIgnoreCase(current.getStatus())) {
            return true; // Already checked in
        }

        boolean saved = saveConfirmedToDatabase(current);
        if (!saved) {
            return false;
        }

        registrations.set(index, new RegistrationRecord(
                current.getRegistrationId(),
                current.getAttendeeName(),
                current.getEventName(),
                "Checked-In"
        ));
        return true;
    }

    /**
     * Updates an existing registration record (and syncs to DB if already Checked-In).
     */
    public boolean updateRegistration(int index, String registrationId, String attendeeName, String eventName, String status) {
        if (index < 0 || index >= registrations.size()) {
            return false;
        }
        if (isBlank(registrationId) || isBlank(attendeeName) || isBlank(eventName) || isBlank(status)) {
            return false;
        }

        String cleanId = registrationId.trim();
        int existingIndex = findIndexById(cleanId);
        if (existingIndex != -1 && existingIndex != index) {
            return false;
        }

        RegistrationRecord updated = new RegistrationRecord(
                cleanId,
                attendeeName.trim(),
                eventName.trim(),
                status.trim()
        );

        if ("Checked-In".equalsIgnoreCase(updated.getStatus())) {
            if (!saveConfirmedToDatabase(updated)) {
                return false;
            }
        }

        registrations.set(index, updated);
        return true;
    }

    /**
     * Deletes a registration from memory (and from DB if it was already Checked-In).
     */
    public boolean deleteRegistration(int index) {
        if (index < 0 || index >= registrations.size()) {
            return false;
        }

        RegistrationRecord target = registrations.get(index);
        if ("Checked-In".equalsIgnoreCase(target.getStatus())) {
            deleteFromDatabase(target.getRegistrationId());
        }

        registrations.remove(index);
        return true;
    }

    /**
     * Returns only registrations waiting for check-in confirmation.
     */
    public List<RegistrationRecord> getPendingRegistrations() {
        List<RegistrationRecord> pending = new ArrayList<>();
        for (RegistrationRecord record : registrations) {
            if ("Pending Check-In".equalsIgnoreCase(record.getStatus())) {
                pending.add(record);
            }
        }
        return pending;
    }

    /**
     * Returns only confirmed check-ins.
     */
    public List<RegistrationRecord> getCheckedInRegistrations() {
        List<RegistrationRecord> checkedIn = new ArrayList<>();
        for (RegistrationRecord record : registrations) {
            if ("Checked-In".equalsIgnoreCase(record.getStatus())) {
                checkedIn.add(record);
            }
        }
        return checkedIn;
    }

    /**
     * Searches registrations by ID, Attendee, Event, or Status.
     */
    public List<RegistrationRecord> searchRegistrations(String searchBy, String query) {
        List<RegistrationRecord> results = new ArrayList<>();
        if (isBlank(query)) {
            return results;
        }

        String target = query.trim().toLowerCase();
        String criterion = searchBy == null ? "Attendee" : searchBy.trim();

        for (RegistrationRecord record : registrations) {
            String field;
            if ("ID".equalsIgnoreCase(criterion)) {
                field = record.getRegistrationId();
            } else if ("Event".equalsIgnoreCase(criterion)) {
                field = record.getEventName();
            } else if ("Status".equalsIgnoreCase(criterion)) {
                field = record.getStatus();
            } else {
                field = record.getAttendeeName();
            }

            if (field.toLowerCase().contains(target)) {
                results.add(record);
            }
        }
        return results;
    }

    public LinkedList<RegistrationRecord> getAllRegistrations() {
        return registrations;
    }

    // --- JDBC Database Methods ---

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private boolean saveConfirmedToDatabase(RegistrationRecord record) {
        String sql = "INSERT INTO registrations (registration_id, attendee_name, event_name, status) "
                   + "VALUES (?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE "
                   + "attendee_name = VALUES(attendee_name), "
                   + "event_name = VALUES(event_name), "
                   + "status = VALUES(status)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, record.getRegistrationId());
            stmt.setString(2, record.getAttendeeName());
            stmt.setString(3, record.getEventName());
            stmt.setString(4, "Checked-In");

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void deleteFromDatabase(String registrationId) {
        String sql = "DELETE FROM registrations WHERE registration_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registrationId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void loadConfirmedFromDatabase() {
        String sql = "SELECT registration_id, attendee_name, event_name, status FROM registrations";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("registration_id");
                if (findIndexById(id) == -1) {
                    registrations.add(new RegistrationRecord(
                            id,
                            rs.getString("attendee_name"),
                            rs.getString("event_name"),
                            rs.getString("status")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int findIndexById(String id) {
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getRegistrationId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}