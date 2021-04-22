package com.oneyoung.common;

/**
 *
 * @author yufeng.lds
 * @date 2019-12-04
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
