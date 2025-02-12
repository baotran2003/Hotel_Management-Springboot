package mock.project.hotelmanagement.exception;

public class PresentOrFuture extends RuntimeException{
    public PresentOrFuture() {
        super();
    }

    public PresentOrFuture(String message) {
        super(message);
    }

    public PresentOrFuture(String message, Throwable cause) {
        super(message, cause);
    }
}
