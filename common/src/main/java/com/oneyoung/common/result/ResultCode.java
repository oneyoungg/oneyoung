package com.oneyoung.common.result;

/**
 *
 * @author oneyoung
 */
public enum ResultCode {
    /**
     * SYSTEM ERROR
     */
    SYSTEM_ERROR("SYSTEM ERROR"),

    /**
     * PARAM_ERROR
     */
    PARAM_ERROR("PARAM_ERROR");

    public String code;

    ResultCode(String code) {
        this.code = code;
    }
}
