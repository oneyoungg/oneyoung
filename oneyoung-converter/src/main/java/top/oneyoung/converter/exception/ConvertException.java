package top.oneyoung.converter.exception;

/**
 * ConvertException
 *
 * @author oneyoung
 * @since 2022/4/15 18:31
 */
public class ConvertException extends RuntimeException {
    public ConvertException(String message) {
        super(message);
    }

    public ConvertException() {
        super();
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }

    protected ConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
