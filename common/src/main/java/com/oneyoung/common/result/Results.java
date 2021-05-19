package com.oneyoung.common.result;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 封装并返回Result的工具类
 * @author oneyoung
 */
public class Results implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Results.class);

    private static final long serialVersionUID = 6965363157096051291L;

    public static void initHeader(Result<?> result) {
        try {
            result.setHeader(new Header());
            result.getHeader().setTraceId(RandomStringUtils.randomAlphanumeric(32));
            result.getHeader().setRpcId(RandomStringUtils.randomAlphanumeric(32));
            result.getHeader().setDate(new Date());
        } catch (Exception e) {
            LOGGER.error("初始化Result.Header异常", e);
        }
    }

    public static <T> Result<T> newResult() {
        return new Result<>();
    }

    public static <T> Result<T> success(T data) {
        return success(data, null, null);
    }

    public static <T> Result<T> success(T data, String message) {
        return success(data, null, message);
    }

    public static <T> Result<T> success(T data, String errorCode, String message) {
        Result<T> result = newResult();
        result.setResult(data);
        result.setMessage(message);
        result.setErrorCode(errorCode);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> fail(T data) {
        return fail(data, null, null);
    }

    public static <T> Result<T> fail(T data, String message) {
        return fail(data, null, message);
    }

    public static <T> Result<T> fail(String errorCode, String message) {
        return fail(null, errorCode, message);
    }

    public static <T> Result<T> fail(T data, String errorCode, String message) {
        Result<T> result = newResult();
        result.setResult(data);
        result.setMessage(message);
        result.setErrorCode(errorCode);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> failErrorCode(String errorCode, Object... args) {
//        String message = ErrorCode.displayMessage(errorCode, args);
        return fail(errorCode, null);
    }

    public static <T> Result<T> failIllegalArgument(String message) {
        return failIllegalArgument(null, message);
    }

    public static <T> Result<T> failIllegalArgument(T data, String message) {
        return fail(data, ResultCode.PARAM_ERROR.code, message);
    }

    public static <T> Result<T> failSystemError(String message) {
        return failSystemError(null, message);
    }

    public static <T> Result<T> failSystemError(T data, String message) {
        return fail(data, message, ResultCode.SYSTEM_ERROR.code);
    }

    public static <T> Result<T> failException(Exception exception) {
        return fail(null, ResultCode.SYSTEM_ERROR.code, exception.getMessage());
    }

    public static <T> Result<T> failException(Throwable throwable) {
        return fail(null, ResultCode.SYSTEM_ERROR.code, throwable.getMessage());
    }

    public static <T> Result<T> failValidation(ConstraintViolationException t) {
        String messages = t.getConstraintViolations().stream().map(item -> item.getMessage()).collect(Collectors.joining("; "));
        return Results.failIllegalArgument(messages);
    }

    public static <T> Result<T> failValidationWithField(ConstraintViolationException t) {
        String messages = t.getConstraintViolations().stream().map(item -> ((PathImpl) item.getPropertyPath()).getLeafNode() + item.getMessage()).collect(Collectors.joining("; "));
        return Results.failIllegalArgument(messages);
    }

}
