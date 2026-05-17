package exceptions;

import java.io.Serial;

public class LowHIndexException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4139071056511881797L;

    public LowHIndexException() {
        super("H-Index is too low for this action!");
    }

    public LowHIndexException(String message) {
        super(message);
    }

    public LowHIndexException(int currentHIndex, int requiredHIndex) {
        super(String.format("Action denied. Your H-Index is %d, but a minimum of %d is required. You need %d more.",
                currentHIndex, requiredHIndex, (requiredHIndex - currentHIndex)));
    }

    public LowHIndexException(int currentHIndex, int requiredHIndex, String supervisorName) {
        super(String.format("Cannot assign %s as supervisor. H-Index is %d, but a minimum of %d is required.",
                supervisorName, currentHIndex, requiredHIndex));
    }
}