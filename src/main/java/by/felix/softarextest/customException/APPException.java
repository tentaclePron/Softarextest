package by.felix.softarextest.customException;


/**
 * Default Exception for this application
 */
public class APPException extends Exception {

    public APPException(String message) {
        super(message);
    }

    public APPException(String message, Object... args) {
        super(String.format(message, args));
    }
}
