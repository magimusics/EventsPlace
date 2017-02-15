package ru.geel.fastest.mvc.bean;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by ivangeel on 24.01.17.
 */
public class User {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean enabled;
    private String description;
    private String country;
    private String city;
    private String occupation;
    private String img;
    private Date bdate;

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {

        this.bdate = bdate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPassword() {
        return password;
    }
}
