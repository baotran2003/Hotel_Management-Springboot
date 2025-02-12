package mock.project.hotelmanagement.exception;

public class GuestStayPeriodViolation extends RuntimeException {
    public GuestStayPeriodViolation() {
        super();
    }

    public GuestStayPeriodViolation(String message) {
        super(message);
    }

    public GuestStayPeriodViolation(String message, Throwable cause) {
        super(message, cause);
    }
}
