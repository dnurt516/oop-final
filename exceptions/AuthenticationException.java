package exceptions;

import java.io.Serial;

public class AuthenticationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1069038827625762377L;

    public AuthenticationException() {
        super("Authentication failed! Invalid email or password.");
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String email, String reason) {
        super(String.format("Failed to log in for user [%s]. Reason: %s", email, reason));
    }
}