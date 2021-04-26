package com.oneyoung.common.message;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 错误 CODE
 *
 * @author oneyoung
 */
@Slf4j
public class ErrorCode {


    /**
     * 默认展示给用户的系统错误文案
     */
    public static final String DEFAULT_DISPLAY_ERROR_MESSAGE = "对不起，系统繁忙，请稍候再试,ERROR KEY NOT FOUND!";
    /**
     * 约定的错误码的路径
     */
    public static final String BUNDLE = "i18n.errors";

    /**
     * 默认展示给用户的系统错误文案
     */
    private static final String DEFAULT_LOG_ERROR_MESSAGE = "ERROR MESSAGE IS MISSING";

    /**
     * file path for displaying error
     */
    private static final String DISPLAY_FILE_PATH = "i18n/errors_zh_CN.properties";
    private static final String INTERNAL_ERROR_MESSAGE_FILE_PATH = "i18n/errors_en_AA.properties";
    private static final String READABLE_ERROR_CODE_FILE_PATH = "i18n/errors_en_XA.properties";
    /**
     * 解析出来格式不标准的ReadableErrorCode
     */
    private static final Map<Object, Object> NON_STANDARD_READABLE_ERROR_CODE = new HashMap<>();
    private static final Map<String, String> CACHED_LOG_MESSAGE = new ConcurrentHashMap<>();
    private static Map<Object, Object> displayErrorCodes;
    /**
     * 是否是老模式, 默认置为老模式, 这种模式下不支持国际化, 且如果展示可读错误码则需要增加特殊符号
     */
    private static Map<Object, Object> internalErrorMessage;
    private static Map<Object, Object> readableErrorCode;

    static {
        init();
    }

    private ErrorCode() {
    }

    public static void init() {
        internalErrorMessage = extractErrorCodes(INTERNAL_ERROR_MESSAGE_FILE_PATH);
        readableErrorCode = extractErrorCodes(READABLE_ERROR_CODE_FILE_PATH);
        displayErrorCodes = extractErrorCodes(DISPLAY_FILE_PATH, true, DEFAULT_DISPLAY_ERROR_MESSAGE);

        processReadableErrorCode();
    }

    /**
     * 处理可读错误码，保证没有特殊字符
     */
    private static void processReadableErrorCode() {
        NON_STANDARD_READABLE_ERROR_CODE.clear();
        for (Map.Entry<Object, Object> entry : readableErrorCode.entrySet()) {
            String raw = entry.getValue().toString();
            try {
                String encoded = URLEncoder.encode(raw, StandardCharsets.UTF_8.displayName());
                if (!raw.equals(encoded)) {
                    NON_STANDARD_READABLE_ERROR_CODE.put(entry.getKey(), raw);
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        for (Map.Entry<Object, Object> entry : NON_STANDARD_READABLE_ERROR_CODE.entrySet()) {
            String raw = entry.getValue().toString();
            try {
                readableErrorCode.put(entry.getKey(), URLEncoder.encode(raw, StandardCharsets.UTF_8.displayName()));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取供日志使用的内部错误文案。 输出内容同时包含 code 和 message
     *
     * @param key    error code
     * @param params params to build the error message
     * @return log Message
     */
    public static String logMessage(@PropertyKey(resourceBundle = ErrorCode.BUNDLE)
                                            String key, Object... params) {
        boolean exitsParams = params != null && params.length > 0;
        if (!exitsParams) {
            String result = CACHED_LOG_MESSAGE.get(key);
            if (result != null) {
                return result;
            }
        }
        String logMessage = searchKeyInAllResourceFile(internalErrorMessage, key, DEFAULT_LOG_ERROR_MESSAGE, params);
        String result = "{c:" + key + "," + "m:" + logMessage + "}";
        if (!exitsParams) {
            CACHED_LOG_MESSAGE.put(key, result);
        }
        return result;
    }

    /**
     * 获取供日志使用的内部错误文案。
     *
     * @param key    error code
     * @param params params to build the error message
     * @return log Message
     */
    private static String logMessageWithoutCode(@PropertyKey(resourceBundle = BUNDLE)
                                                        String key, Object... params) {
        boolean canNotCache = params != null && params.length > 0;
        if (!canNotCache) {
            String result = CACHED_LOG_MESSAGE.get(key);
            if (result != null) {
                return result;
            }
        }
        String result = searchKeyInAllResourceFile(internalErrorMessage, key, DEFAULT_LOG_ERROR_MESSAGE, params);
        if (!canNotCache) {
            CACHED_LOG_MESSAGE.put(key, result);
        }
        return result;
    }

    /**
     * 获取供展示用的中文错误文案。
     *
     * @param key    error code
     * @param params params to build the error message
     * @return message
     */
    public static String displayMessage(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return searchKeyInAllResourceFile(displayErrorCodes, key, DEFAULT_DISPLAY_ERROR_MESSAGE, params);
    }

    private static String searchKeyInAllResourceFile(Map<Object, Object> props,
                                                     String key,
                                                     String defaultValue, Object... params) {
        if (!props.containsKey(key)) {
            return defaultValue;
        }

        Object obj = props.get(key);
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

    private static Map<Object, Object> extractErrorCodes(String resourceFilePath) {
        return extractErrorCodes(resourceFilePath, false, null);
    }

    /**
     * @param resourceFilePath resource
     * @param replaceEnglishWords 如果全是英文,是否要替换为默认的中文文案
     * @return map
     */
    private static Map<Object, Object> extractErrorCodes(String resourceFilePath,
                                                         boolean replaceEnglishWords,
                                                         String replaceText) {
        return new HashMap<>(extractErrorCodes(Thread.currentThread().getContextClassLoader(),
                resourceFilePath,
                replaceEnglishWords,
                replaceText));
    }

    public static Map<Object, Object> extractErrorCodes(ClassLoader classLoader,
                                                        String resourceFilePath,
                                                        boolean replaceEnglishWords,
                                                        String replaceText) {
        Map<Object, Object> props = new HashMap<>();
        try {
            Enumeration<URL> resources = classLoader.getResources(resourceFilePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties prop;
                try (InputStream in = url.openStream()) {
                    prop = new Properties();
                    prop.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                }
                if (replaceEnglishWords) {
                    for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                        //如果全是英文,则替换为默认文案
                        if (ErrorCode.notExistChineseCharacter(String.valueOf(entry.getValue()))) {
                            entry.setValue(replaceText);
                        }
                    }
                }
                props.putAll(prop);
            }
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        return props;
    }

    public static boolean existChineseCharacter(String chineseStr) {
        char[] charArray = chineseStr.toCharArray();
        for (char c : charArray) {
            if ((c >= 0x4e00) && (c <= 0x9fbb)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notExistChineseCharacter(String chineseStr) {
        return !ErrorCode.existChineseCharacter(chineseStr);
    }


    /**
     * 这个方法会从当前模块的resources/i18n/errors.properties文件中解析出错误码和错误文案
     *
     * @param key    bundle key
     * @param params 参数，bundle的值采用MessageFormat的格式化方式
     * @return bundle值，如果bundle key不存在，返回特定key丢失格式
     */
    static ErrorMessage toErrorMessage(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        String logMessage = logMessageWithoutCode(key, params);
        String displayMessage = displayMessage(key, params);
        String readableCode = searchKeyInAllResourceFile(readableErrorCode, key, key);
        return ErrorMessage.of(key, logMessage, displayMessage, readableCode, true);


    }


}
