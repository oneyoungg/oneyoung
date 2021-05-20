package top.oneyoung.common.message;

import top.oneyoung.common.result.Result;
import top.oneyoung.common.result.Results;

import java.util.Locale;

/**
 * 业务中创建自己的异常，继承此类，并创建相应的构造方法
 *
 * @author oneyoung
 */
public class ErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = -6165816768698217146L;

    public static final ErrorMessage DEFAULT_ERROR_MESSAGE = ErrorMessage.of(ErrorMessage.DEFAULT_CODE, "系统错误！", Locale.CHINA);

    protected final ErrorMessage errorMessage;

    public ErrorCodeException() {
        super();
        this.errorMessage = DEFAULT_ERROR_MESSAGE;
    }

    public ErrorCodeException(String key) {
        this(toErrorMessage(key));
    }

    public ErrorCodeException(String key, Throwable cause) {
        this(toErrorMessage(key), cause);
    }

    public ErrorCodeException(Throwable cause) {
        super(cause);
        this.errorMessage = DEFAULT_ERROR_MESSAGE;
    }

    /*--------------------------------------------自定义ErrorMessage构造方法--------------------------------------------*/

    public ErrorCodeException(String key, Object... args) {
        super(toMessageString(key, args));
        this.errorMessage = toErrorMessage(key, args);
    }

    public ErrorCodeException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorCodeException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage.getMessage(), cause);
        this.errorMessage = errorMessage;
    }

    /*--------------------------------------------静态工具方法--------------------------------------------*/

    private static String toMessageString(String key, Object... args) {
        return toErrorMessage(key, args).getMessage();
    }

    private static ErrorMessage toErrorMessage(String key, Object... args) {
        return ErrorMessage.of(key, args);
    }


    /*--------------------------------------------常用类方法--------------------------------------------*/

    public ErrorMessage getError() {
        if (errorMessage == null) {
            return DEFAULT_ERROR_MESSAGE;
        }
        return errorMessage;
    }

    public String getErrorCode() {
        return getError().getErrorCode();
    }

    public String getErrorMessage() {
        return getError().getMessage();
    }

    public String getErrorMessageCode() {
        return getErrorCode() + ":" + getErrorMessage();
    }

    public <T> Result<T> toResult() {
        return Results.fail(errorMessage.getErrorCode(),errorMessage.getMessage());
    }

}
