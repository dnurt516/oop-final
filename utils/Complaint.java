package utils;

import models.users.Employee;

import enums.UrgencyLevel;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

public class Complaint implements Serializable {
    @Serial
    private static final long serialVersionUID = -5926836681097058101L;

    private Employee sender;
    private String text;
    private Date date;
    private UrgencyLevel urgency;
    private boolean isViewed = false;

    public Complaint(Employee sender, String text, UrgencyLevel urgency) {
        this.sender = sender;
        this.text = text;
        this.urgency = urgency;
        this.date = new Date();
    }

    public Employee getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public boolean getViewed() {
        return isViewed;
    }

    public UrgencyLevel getUrgency() {
        return urgency;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUrgency(UrgencyLevel urgency) {
        this.urgency = urgency;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }
}