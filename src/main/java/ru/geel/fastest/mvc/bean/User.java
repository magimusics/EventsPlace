package ru.geel.fastest.mvc.bean;

/**
 * Created by ivangeel on 24.01.17.
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return id;
    }

    public void setIdUser(int idUser) {
        this.id = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
