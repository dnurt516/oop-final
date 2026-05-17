package utils;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDateTime;

public class LogEntry implements Serializable {
    @Serial
    private static final long serialVersionUID = 2733559094151314023L;

    private UUID userId;
    private String action;
    private final LocalDateTime timestamp;
    public LogEntry() {
        this.timestamp = LocalDateTime.now();
    }

    public LogEntry(UUID userId, String action) {
        this.userId = userId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getUserId() { return userId; }
    public String getAction() { return action; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] User ID: %s | Action: %s",
                timestamp.toString(), userId, action);
    }
}