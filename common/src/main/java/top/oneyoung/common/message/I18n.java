package top.oneyoung.common.message;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ResourceEnum
 *
 * @author oneyoung
 * @date 2021/5/19 11:38
 */
@Slf4j
@Getter
public enum I18n implements Serializable {


    /**
     * 中文
     */
    CHINA(Locale.CHINA, "i18n/errors_zh_CN.properties", "对不起，系统繁忙，请稍候再试。"),

    /**
     * 英文
     */
    US(Locale.US, "i18n/errors_en_US.properties", "Sorry, the system is busy, please try again later."),

    ;


    private static final Map<Locale, I18n> I18N_MAP = Arrays.stream(values()).collect(Collectors.toMap(I18n::getLocale, item -> item));
    private final Locale locale;
    private final String path;
    private final String defaultMessage;
    private final Map<Object, Object> errorMessages;

    I18n(Locale locale, String path, String defaultMessage) {
        this.locale = locale;
        this.defaultMessage = defaultMessage;
        this.path = path;
        this.errorMessages = extractErrorCodes(path);
    }

    /**
     * 获取供日志使用的内部错误文案。
     *
     * @param key    error code
     * @param params params to build the error message
     * @return log Message
     */
    public static String getMessage(I18n i18N,
                                    String key,
                                    Object... params) {
        String defaultMessage = i18N.getDefaultMessage();
        Map<Object, Object> errorMessages = i18N.getErrorMessages();
        return searchKeyInAllResourceFile(errorMessages, key, defaultMessage, params);
    }

    private static String searchKeyInAllResourceFile(Map<Object, Object> props,
                                                     String key,
                                                     String defaultValue, Object... params) {
        if (!props.containsKey(key)) {
            return defaultValue;
        }
        Object obj = props.get(key);
        if (params == null || params.length == 0) {
            return String.valueOf(obj);
        }
        String message = buildMessage(obj, params);
        return message != null && message.length() > 0 ? message : defaultValue;
    }

    private static String buildMessage(Object obj, Object[] params) {
        String value = String.valueOf(obj);
        String message = value;

        try {
            if (params != null && params.length > 0 && message != null && message.indexOf('{') >= 0) {
                message = MessageFormat.format(message, params);
            }

            //可能是编码的问题,有些 bug,上面的 format 可能会失败.
            if (params != null && params.length > 0 && message != null && message.contains("{0}")) {
                message = MessageFormat.format(message, params);
            }
        } catch (Exception e) {
            log.error("log message cannot be retrieved properly", e);
            return value;
        }
        return message;
    }

    /**
     * 这个方法会从当前模块的resources/i18n/errors.properties文件中解析出错误码和错误文案
     *
     * @param key    bundle key
     * @param params 参数，bundle的值采用MessageFormat的格式化方式
     * @return bundle值，如果bundle key不存在，返回特定key丢失格式
     */
    public static Map<Locale, ErrorMessage> toAllErrorMessage(String key, Object... params) {
        return Arrays.stream(values())
                .collect(Collectors.toMap(I18n::getLocale, item -> {
                    String message = getMessage(item, key, params);
                    return ErrorMessage.of(key, message, item.getLocale());
                }));
    }

    public static ErrorMessage toErrorMessage(Locale locale, String key, Object... params) {
        Map<Locale, ErrorMessage> localeErrorMessageMap = toAllErrorMessage(key, params);
        ErrorMessage errorMessage = localeErrorMessageMap.get(locale);
        if (errorMessage == null) {
            I18n i18n = I18N_MAP.get(locale);
            String defaultMessage = i18n.getDefaultMessage();
            return ErrorMessage.of(ErrorMessage.DEFAULT_CODE, defaultMessage, locale);
        }
        return errorMessage;
    }


    /**
     * @param resourceFilePath resource
     * @return map
     */
    private Map<Object, Object> extractErrorCodes(String resourceFilePath) {
        return new HashMap<>(extractErrorCodes(Thread.currentThread().getContextClassLoader(),
                resourceFilePath));
    }

    private Map<Object, Object> extractErrorCodes(ClassLoader classLoader,
                                                  String resourceFilePath) {
        Map<Object, Object> props = new HashMap<>(1024);
        try {
            Enumeration<URL> resources = classLoader.getResources(resourceFilePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties prop;
                try (InputStream in = url.openStream()) {
                    prop = new Properties();
                    prop.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                }
                props.putAll(prop);
            }
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        return props;
    }


}
