package argonaut.exceptions;

public class ConfigFileParseException extends RuntimeException {
    protected String message;

    public ConfigFileParseException(String message) {
        this.message = message;
    }

    public ConfigFileParseException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }
}
