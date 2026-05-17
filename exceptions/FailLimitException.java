package exceptions;

import java.io.Serial;

public class FailLimitException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4068834813052242954L;

    public FailLimitException() {
        super("Failures limit exceeded! Maximum allowed is 3 failures.");
    }

    public FailLimitException(String message) {
        super(message);
    }
}