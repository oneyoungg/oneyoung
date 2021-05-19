package com.oneyoung.common.message;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ErrorMessage
 *
 * @author oneyoung
 * @date 2021/5/19 14:26
 */
@Getter
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 7169459647378586538L;

    public static final String DEFAULT_CODE = "SYSTEM ERROR";
    private static final Map<Locale, Map<String, ErrorMessage>> MESSAGE_CACHE = new ConcurrentHashMap<>();
    private static Locale activeLoc = Locale.getDefault();
    private final String errorCode;

    private final String message;

    private final Locale locale;

    public ErrorMessage(String errorCode, String errorMessages, Locale locale) {
        this.errorCode = errorCode;
        this.message = errorMessages;
        this.locale = locale;
    }

    public static ErrorMessage of(String errorCode, String errorMessage, Locale locale) {
        return new ErrorMessage(errorCode, errorMessage, locale);
    }

    public static ErrorMessage of(Locale locale, String key, Object... params) {
        boolean canCache = params == null || params.length <= 0;
        if (canCache) {
            Map<String, ErrorMessage> messageMap = MESSAGE_CACHE.computeIfAbsent(locale, item -> new HashMap<>(128));
            return messageMap.computeIfAbsent(key, item -> I18n.toErrorMessage(locale, key, params));
        }
        return I18n.toErrorMessage(locale, key, params);
    }

    public static ErrorMessage of(String key, Object... params) {
        return of(activeLoc, key, params);
    }

    public static void configure(Locale locale) {
        activeLoc = locale;
    }


}
