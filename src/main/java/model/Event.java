/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Asus TUF
 */
import java.time.LocalDate;

public class Event {
    private String id;
    private String name;
    private LocalDate date;
    private String category;
    private int attendees;
    
    public Event(String id, String name, LocalDate date, String category, int attendees){
        this.id = id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.attendees = attendees;
    }
    
    public String getId() {return id;}
    public String getName() {return name;}
    public LocalDate getDate() {return date;}
    public String getCategory() {return category;}
    public int getAttendees() {return attendees;}
}
