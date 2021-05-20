package top.oneyoung.common.result;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础结果类
 * @author oneyoung
 */
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -4383894792491700248L;
    /**
     * 返回结果
     */
    private T result;

    /**
     * 业务结果，默认失败
     */
    private boolean success;

    /**
     * 返回结果Code
     *
     * @see ResultCode
     */
    private String errorCode;

    /**
     * 返回结果描述
     */
    private String message;
    /**
     * 业务自行扩展返回信息
     */
    private Map<String, Object> ext = new HashMap<>();

    /**
     * 字段验证错误信息
     */
    private Map<String, ?> validateErrors = new HashMap<>();

    /**
     * 返回头信息，包含非业务信息
     */
    private Header header;

    public Result() {
        Results.initHeader(this);
    }

    public void putExt(String key, Object value) {
        ext.put(key, value);
    }

    public Object getExt(String key) {
        return ext.get(key);
    }

    /**
     * @see Results
     */
    public static <T> Result<T> success(T t) {
        return Results.success(t);
    }

    /**
     * @see Results
     */
    public static <T> Result<T> success(T t, String message) {
        return Results.success(t, message);
    }

    /**
     * @see Results
     */
    public static <T> Result<T> failure(String errorCode, String message) {
        return Results.fail(errorCode, message);
    }

    /**
     * @see Results
     */
    public static <T> Result<T> failure(T t, String errorCode, String message) {
        return Results.fail(t, errorCode, message);
    }
}
