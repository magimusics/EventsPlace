package ru.geel.fastest.mvc.bean;

/**
 * Created by ivangeel on 03.03.17.
 */

public class SUser {

    String name;
    String lastname;
    String country;
    String city;
    String occupation;
    int bDay;
    int bMonth;
    int bYear;
    String description;

    public SUser() {
    }

    public SUser(int bDay, int bMonth, int bYear, String city, String country,
                 String description, String lastname, String name,
                 String occupation) {
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.city = city;
        this.country = country;
        this.description = description;
        this.lastname = lastname;
        this.name = name;
        this.occupation = occupation;
    }

    public int getbDay() {
        return bDay;
    }

    public int getbMonth() {
        return bMonth;
    }

    public int getbYear() {
        return bYear;
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

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setbDay(int bDay) {
        this.bDay = bDay;
    }

    public void setbMonth(int bMonth) {
        this.bMonth = bMonth;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
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


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "SUser{" +
                "bDay=" + bDay +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", occupation='" + occupation + '\'' +
                ", bMonth='" + bMonth + '\'' +
                ", bYear=" + bYear +
                ", description='" + description + '\'' +
                '}';
    }
}
