package top.oneyoung.common.result;

import lombok.Getter;

/**
 * 错误码枚举类
 *
 * @author oneyoung
 */
@Getter
public enum ResultCode {
    /**
     * SYSTEM ERROR
     */
    SYSTEM_ERROR("SYSTEM ERROR"),

    /**
     * PARAM_ERROR
     */
    PARAM_ERROR("PARAM_ERROR");

    private final String code;

    ResultCode(String code) {
        this.code = code;
    }
}
