package utils;

import models.users.Employee;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = -3347923826846431635L;

    private String id;
    private Employee sender;
    private String description;
    private Date createdAt;
    private boolean isAccepted;
    private boolean isCompleted;

    public Request() {}

    public Request(Employee sender, String description) {
        this.id = "REQ-" + System.currentTimeMillis();
        this.sender = sender;
        this.description = description;
        this.createdAt = new Date();
        this.isAccepted = false;
        this.isCompleted = false;
    }

    public void accept() { this.isAccepted = true; }
    public void reject() { this.isAccepted = false; }
    public void complete() { this.isCompleted = true; }

    @Override
    public String toString() {
        String status = isCompleted ? "DONE" : (isAccepted ? "IN PROGRESS" : "NEW");
        return String.format("[%s] From: %s | Status: %s | Desc: %s", id, sender.getEmail(), status, description);
    }

    public boolean isNew() { return !isAccepted && !isCompleted; }
}
