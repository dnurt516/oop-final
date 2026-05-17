package exceptions;

import java.io.Serial;

public class CreditLimitException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3256039207234747636L;

    public CreditLimitException() {
        super("Credit limit exceeded! Maximum allowed is 21 credits.");
    }

    public CreditLimitException(String message) {
        super(message);
    }
}