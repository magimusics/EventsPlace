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
    private String password;
    private boolean enabled;
    private String description;
    private Date bdate;
    private String email;
    private String img;
    private String country;
    private String city;
    private String occupation;



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

    public boolean isFull()
    {
        if (firstname.equals("") || lastname.equals("") || password.equals("") || description.equals("") || email.equals("") || country.equals("") || city.equals("") || occupation.equals("")){
            return false;
        }
        else return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "bdate=" + bdate +
                ", id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", img='" + img + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (enabled != user.enabled) return false;
        if (!firstname.equals(user.firstname)) return false;
        if (!lastname.equals(user.lastname)) return false;
        if (!password.equals(user.password)) return false;
        if (!description.equals(user.description)) return false;
        if (!bdate.equals(user.bdate)) return false;
        if (!email.equals(user.email)) return false;
        if (!img.equals(user.img)) return false;
        if (!country.equals(user.country)) return false;
        if (!city.equals(user.city)) return false;
        return occupation.equals(user.occupation);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + description.hashCode();
        result = 31 * result + bdate.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + img.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + occupation.hashCode();
        return result;
    }
}
