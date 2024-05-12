package selfConstructed.SkySalerADS.exception;

public class WrongOldPasswordException extends RuntimeException {
    public WrongOldPasswordException(String message) {
        super(message);
    }
}
