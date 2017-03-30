package ru.geel.fastest.mvc.entity;

/**
 * Created by ivangeel on 29.03.17.
 */
public class EventPost {
    private int id;
    private int command;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getCommand() {

        return command;
    }

    @Override
    public String toString() {
        return "EventPost{" +
                "command=" + command +
                ", id=" + id +
                '}';
    }
}
