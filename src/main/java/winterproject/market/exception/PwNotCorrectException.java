package winterproject.market.exception;

public class PwNotCorrectException extends RuntimeException {
    public PwNotCorrectException() {
        super();
    }

    public PwNotCorrectException(String message) {
        super(message);
    }

    public PwNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public PwNotCorrectException(Throwable cause) {
        super(cause);
    }
}
