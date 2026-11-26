/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsa;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class WaitingList {

    private PriorityQueue<Attendee> queue;
    private int nextPriority = 1;

    
    
    public WaitingList() {
        queue = new PriorityQueue<>(
            Comparator.comparingInt(Attendee::getPriority)
        );
    }

    public void addAttendee(String id, String name, String email) {
        Attendee attendee = new Attendee(
            nextPriority++, id, name, email
        );

        queue.offer(attendee);
    }

    public List<Attendee> getWaitingAttendees() {
        PriorityQueue<Attendee> copy = new PriorityQueue<>(queue);
        List<Attendee> attendees = new ArrayList<>();

        while (!copy.isEmpty()) {
            attendees.add(copy.poll());
        }

        return attendees;
    }

    public Attendee removeNextAttendee() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public static class Attendee {
        private int priority;
        private String id;
        private String name;
        private String email;

        public Attendee(int priority, String id,
                        String name, String email) {
            this.priority = priority;
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getPriority() {
            return priority;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
