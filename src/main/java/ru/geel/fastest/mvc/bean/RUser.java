package ru.geel.fastest.mvc.bean;

/**
 * Created by ivangeel on 27.02.17.
 */

public class RUser {


    String firstname;

    String lastname;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public RUser() {
    }

    public RUser(String firstname, String lastname) {

        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "RUser{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
