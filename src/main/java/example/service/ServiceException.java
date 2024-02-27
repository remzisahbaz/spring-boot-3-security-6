package example.service;

public class ServiceException extends RuntimeException {

    private final String action;

    public ServiceException(String action, String message) {
        super(message);
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
