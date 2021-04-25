package com.oneyoung.common.message;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 错误 CODE
 *@author oneyoung
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
    private final static String displayFilePath = "i18n/errors_zh_CN.properties";
    private final static String internalErrorMessageFilePath = "i18n/errors_en_AA.properties";
    private final static String readableErrorCodeFilePath = "i18n/errors_en_XA.properties";
    private static Map<Object, Object> displayErrorCodes;
    // 是否是老模式, 默认置为老模式, 这种模式下不支持国际化, 且如果展示可读错误码则需要增加特殊符号
    private static Map<Object, Object> internalErrorMessage;
    private static Map<Object, Object> readableErrorCode;
    // 解析出来格式不标准的ReadableErrorCode
    private static Map<Object, Object> nonStandardReadableErrorCode = new HashMap<Object, Object>();

    private static Map<String, String> cachedLogMessage = new ConcurrentHashMap<String, String>();

    static {
        init();
    }

    public static void init() {
        internalErrorMessage = extractErrorCodes(internalErrorMessageFilePath);
        readableErrorCode = extractErrorCodes(readableErrorCodeFilePath);
        displayErrorCodes = extractErrorCodes(displayFilePath, true, DEFAULT_DISPLAY_ERROR_MESSAGE);

        processReadableErrorCode();
    }


    // 处理可读错误码，保证没有特殊字符
    private static void processReadableErrorCode() {
        nonStandardReadableErrorCode.clear();
        for (Map.Entry<Object, Object> entry : readableErrorCode.entrySet()) {
            String raw = entry.getValue().toString();
            try {
                String encoded = URLEncoder.encode(raw, "UTF-8");
                if (!raw.equals(encoded)) {
                    nonStandardReadableErrorCode.put(entry.getKey(), raw);
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        for (Map.Entry<Object, Object> entry : nonStandardReadableErrorCode.entrySet()) {
            String raw = entry.getValue().toString();
            try {
                readableErrorCode.put(entry.getKey(), URLEncoder.encode(raw, "UTF-8"));
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
     * @return
     */
    public static String logMessage(@PropertyKey(resourceBundle = ErrorCode.BUNDLE)
                                    String key, Object... params) {
        boolean canNotCache = params != null && params.length > 0;
        if (!canNotCache) {
            String result = cachedLogMessage.get(key);
            if (result != null) {
                return result;
            }
        }
        String logMessage = "log message cannot be retrieved properly";
        try {
            logMessage = searchKeyInAllResourceFile(internalErrorMessage, key, DEFAULT_LOG_ERROR_MESSAGE, params);
        } catch (Exception e) {
            log.error(logMessage, e);
        }
        String result = "{c:" + key + "," + "m:" + logMessage + "}";
        if (!canNotCache) {
            cachedLogMessage.put(key, result);
        }
        return result;
    }

    /**
     * 获取供日志使用的内部错误文案。
     *
     * @param key    error code
     * @param params params to build the error message
     * @return
     */
    private static String logMessageWithoutCode(@PropertyKey(resourceBundle = BUNDLE)
                                                String key, Object... params) {
        boolean canNotCache = params != null && params.length > 0;
        if (!canNotCache) {
            String result = cachedLogMessage.get(key);
            if (result != null) {
                return result;
            }
        }
        String logMessage = "log message cannot be retrieved properly";
        try {
            logMessage = searchKeyInAllResourceFile(internalErrorMessage, key, DEFAULT_LOG_ERROR_MESSAGE, params);
        } catch (Exception e) {
            log.error(logMessage, e);
        }
        String result = logMessage;
        if (!canNotCache) {
            cachedLogMessage.put(key, result);
        }
        return result;
    }

    /**
     * 获取供展示用的中文错误文案。
     *
     * @param key    error code
     * @param params params to build the error message
     * @return
     */
    public static String displayMessage(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return searchKeyInAllResourceFile(displayErrorCodes, key, DEFAULT_DISPLAY_ERROR_MESSAGE, params);
    }


    private static String searchKeyInAllResourceFile(Map<Object, Object> props,
                                                     String key,
                                                     String defaultValue, Object... params) {
        if (!props.containsKey(key)) return defaultValue;

        Object obj = props.get(key);
        String message = buildMessage(obj, params);
        return message!=null&message.length()>0 ? message : defaultValue;
    }


    private static String buildMessage(Object obj, Object[] params) {
        String message = String.valueOf(obj);

        if (params != null && params.length > 0 && message != null && message.indexOf('{') >= 0) {
            message = MessageFormat.format(message, params);
        }

        //可能是编码的问题,有些 bug,上面的 format 可能会失败.
        if (params != null && params.length > 0 && message != null && message.contains("{0}")) {
            message = MessageFormat.format(message, params);
        }

        return message;
    }

    private static Map<Object, Object> extractErrorCodes(String resourceFilePath) {
        return extractErrorCodes(resourceFilePath, false, null);
    }

    /**
     * @param resourceFilePath
     * @param replaceEnglishWords 如果全是英文,是否要替换为默认的中文文案
     * @return
     */
    private static Map<Object, Object> extractErrorCodes(String resourceFilePath,
                                                         boolean replaceEnglishWords,
                                                         String replaceText) {
        Map<Object, Object> props = new HashMap<Object, Object>();
        //props.putAll(LatticeSpiFactory.getInstance().getContainerManager()
        //    .extractPluginErrorCodes(resourceFilePath, replaceEnglishWords, replaceText));

        props.putAll(extractErrorCodes(Thread.currentThread().getContextClassLoader(),
            resourceFilePath,
            replaceEnglishWords,
            replaceText));
        return props;
    }

    public static Map<Object, Object> extractErrorCodes(ClassLoader classLoader,
                                                         String resourceFilePath,
                                                         boolean replaceEnglishWords,
                                                         String replaceText) {
        Map<Object, Object> props = new HashMap<Object, Object>();
        try {
            Enumeration<URL> resources = classLoader.getResources(resourceFilePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                InputStream in = url.openStream();
                Properties prop = new Properties();
                prop.load(new InputStreamReader(in, "UTF-8"));
                if (replaceEnglishWords) {
                    for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                        //如果全是英文,则替换为默认文案
                        if (!hasChineseCharacter(String.valueOf(entry.getValue()))) {
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

    public static final boolean hasChineseCharacter(String chineseStr) {
        char[] charArray = chineseStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
                return true;
            }
        }
        return false;
    }

    private static Map<Object, Object> extractContextErrorCodes(String resourceFilePath,
                                                         boolean replaceEnglishWords,
                                                         String replaceText) {
        Map<Object, Object> props = new HashMap<Object, Object>();
        props.putAll(extractErrorCodes(Thread.currentThread().getContextClassLoader(),
                resourceFilePath,
                replaceEnglishWords,
                replaceText));
        return props;
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
