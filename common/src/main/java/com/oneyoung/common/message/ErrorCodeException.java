//package com.oneyoung.common.message;
//
//import com.oneyoung.common.result.Result;
//import com.oneyoung.common.result.Results;
//import org.apache.commons.lang3.StringUtils;
//
///**
// * 业务中创建自己的异常，继承此类，并创建相应的构造方法
// * @author oneyoung
// */
//public abstract class ErrorCodeException extends RuntimeException {
//
//    private static final long serialVersionUID = 235074941548756859L;
//
//    public static final ErrorMessage DEFAULT_ERROR_MESSAGE = ErrorMessage.of("SYSTEM_ERROR", "System error!", "系统错误！");
//
//    private Object[] params;
//
//    protected ErrorMessage errorMessage;
//
//    public ErrorCodeException() {
//        super();
//    }
//
//    public ErrorCodeException(String message) {
//        this(toErrorMessage(message));
//    }
//
//    public ErrorCodeException(String message, Throwable cause) {
//        this(toErrorMessage(message), cause);
//    }
//
//    public ErrorCodeException(Throwable cause) {
//        super(cause);
//    }
//
//    /*--------------------------------------------自定义ErrorMessage构造方法--------------------------------------------*/
//
//    public ErrorCodeException(String key, Object... args) {
//        super(toMessageString(key, args));
//        this.errorMessage = toErrorMessage(key, args);
//        this.params = args;
//    }
//
//    public ErrorCodeException(ErrorMessage errorMessage) {
//        super(errorMessage.getDisplayErrorMessage());
//        this.errorMessage = errorMessage;
//    }
//
//    public ErrorCodeException(ErrorMessage errorMessage, Throwable cause) {
//        super(errorMessage.getDisplayErrorMessage(), cause);
//        this.errorMessage = errorMessage;
//    }
//
//    /*--------------------------------------------静态工具方法--------------------------------------------*/
//
//    private static String toMessageString(String key, Object... args) {
//        return isErrorMessageKey(key) ? toString(toErrorMessage(key, args)) : key;
//    }
//
//    private static ErrorMessage toErrorMessage(String key, Object... args) {
//        return ErrorMessage.code(key, args);
//    }
//
//    private static boolean isErrorMessageKey(String key) {
//        // 按错误码定义，是5个
//        return key != null && StringUtils.countMatches(key, '-') == 5;
//    }
//
//    private static String toString(ErrorMessage errorMessage) {
//        return errorMessage.getErrorCode() + ":" + errorMessage.getDisplayErrorMessage();
//    }
//
//    /*--------------------------------------------常用类方法--------------------------------------------*/
//
//    public ErrorMessage getError() {
//        if (errorMessage == null) {
//            return DEFAULT_ERROR_MESSAGE;
//        }
//        return errorMessage;
//    }
//
//    public String getErrorCode() {
//        return getError().getErrorCode();
//    }
//
//    public String getErrorMessage() {
//        return getError().getDisplayErrorMessage();
//    }
//
//    public String getErrorMessageCode() {
//        return getErrorCode() + ":" + getErrorMessage();
//    }
//
//    /**
//     * 转换为返回结果
//     */
//    public <T> Result<T> toResult() {
//        return Results.failException(this);
//    }
//
//    public Object[] getParams() {
//        return params;
//    }
//}
