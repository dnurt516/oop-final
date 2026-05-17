package utils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import models.users.Employee;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = -2647936543761180503L;

    private final Employee from;
    private final Employee to;
    private final String text;
    private final Date sentAt;

    public Message(Employee from, Employee to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.sentAt = new Date();
    }

    @Override
    public String toString() {
        return String.format("[%s] From: %s | Message: %s", sentAt, from.getEmail(), text);
    }
}