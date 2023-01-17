package winterproject.market.exception;

public class IdNotExistException extends RuntimeException {
    public IdNotExistException() {
        super();
    }

    public IdNotExistException(String message) {
        super(message);
    }

    public IdNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotExistException(Throwable cause) {
        super(cause);
    }
}
