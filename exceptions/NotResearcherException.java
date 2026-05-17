package exceptions;

import java.io.Serial;

public class NotResearcherException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8216909444800067019L;

    public NotResearcherException() {
        super("The specified user does not have Researcher status!");
    }

    public NotResearcherException(String message) {
        super(message);
    }

    public NotResearcherException(String email, String action) {
        super(String.format("Action '%s' denied. User with email '%s' is not registered as a Researcher.",
                action, email));
    }

}