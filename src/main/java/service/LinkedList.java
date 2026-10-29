/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Asus TUF
 */
public class LinkedList {
    private java.util.LinkedList<Object[]> eventList;

    public LinkedList() {
        eventList = new java.util.LinkedList<>();
        // Initialize with default data
        eventList.add(new Object[]{"EVT001", "Tech Summit", "11/15/2026", "Technology", 120});
        eventList.add(new Object[]{"EVT002", "Art Workshop", "11/22/2026", "Arts", 85});
        eventList.add(new Object[]{"EVT003", "Business Forum", "11/27/2026", "Business", 50});
    }

    public void addEvent(String id, String name, String date, String category, int attendees) {
        eventList.add(new Object[]{id, name, date, category, attendees});
    }

    public void updateEvent(int index, String id, String name, String date, String category) {
        if (index >= 0 && index < eventList.size()) {
            Object[] existing = eventList.get(index);
            Object attendees = existing.length > 4 ? existing[4] : 0; // Preserve attendees count
            eventList.set(index, new Object[]{id, name, date, category, attendees});
        }
    }

    public void deleteEvent(int index) {
        if (index >= 0 && index < eventList.size()) {
            eventList.remove(index);
        }
    }

    public Object[][] getAllEvents() {
        return eventList.toArray(new Object[0][0]);
    }
}
