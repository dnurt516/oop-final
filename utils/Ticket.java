package utils;

import enums.ResolveStatus;
import models.users.User;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = -3472479221742946093L;

    private User createdBy;
    private String description;
    private ResolveStatus resolved;

    public Ticket() {
        this.resolved = ResolveStatus.PENDING;
    }

    public Ticket(User createdBy, String description) {
        this.createdBy = createdBy;
        this.description = description;
        this.resolved = ResolveStatus.PENDING;
    }

    public User getCreatedBy() { return this.createdBy; }

    public void setCreatedBy(User u) { this.createdBy = u; }

    public String getDescription() { return this.description; }

    public void setDescription(String d) { this.description = d; }

    public ResolveStatus getResolved() { return this.resolved; }

    public void setResolved(ResolveStatus r) { this.resolved = r; }

    @Override
    public String toString() {
        String status = this.resolved.getTextValue();
        String authorName = (createdBy != null) ? createdBy.getFirstName() + " " + createdBy.getLastName() : "Unknown";

        return String.format("Ticket [Status: %s] | From: %s | Description: %s", status, authorName, this.description);
    }
}