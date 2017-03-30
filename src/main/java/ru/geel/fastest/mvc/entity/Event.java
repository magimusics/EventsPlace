package ru.geel.fastest.mvc.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by ivangeel on 25.03.17.
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private int id;

    @NotEmpty
    @Column(name = "creator")
    private int creator;

    @NotEmpty
    @Column(name = "eventName")
    private String eventName;

    @Column(name = "description")
    private String description;

    @Column(name = "cover")
    private String cover;

    @Column(name = "ageMIN")
    private int ageMIN;

    @Column(name = "ageMAX")
    private int ageMAX;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private Calendar calendar;

    @Column(name = "place")
    private String place;

    public void setAgeMAX(int ageMAX) {
        this.ageMAX = ageMAX;
    }

    public void setAgeMIN(int ageMIN) {
        this.ageMIN = ageMIN;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAgeMAX() {
        return ageMAX;
    }

    public int getAgeMIN() {
        return ageMIN;
    }

    public int getAmount() {
        return amount;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getCover() {
        return cover;
    }

    public int getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {

        return eventName;
    }

    @Override
    public String toString() {
        return "Event{" +
                "ageMAX=" + ageMAX +
                ", id=" + id +
                ", creator=" + creator +
                ", description='" + description + '\'' +
                ", cover='" + cover + '\'' +
                ", ageMIN=" + ageMIN +
                ", amount=" + amount +
                ", calendar=" + calendar +
                ", place='" + place + '\'' +
                '}';
    }
}
