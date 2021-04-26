package com.oneyoung.common.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取 resources 中的 key
 * @author oneyoung
 */
@NoArgsConstructor
public class ErrorMessage implements Serializable {


    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3017472277626865964L;
    /**
     * 对于没有参数的错误码 cache 起来
     */
    private final static Map<String, ErrorMessage> cachedMessages = new ConcurrentHashMap<String, ErrorMessage>(500);
    /**
     * the error code.
     */
    @Getter
    private String errorCode = null;

    @Getter
    @Setter
    private SeverityType severity = SeverityType.MAJOR;
    /**
     * the error message.
     */
    @Getter
    private String errorMessage = null;
    /**
     * the error message which will be displayed.
     */
    @Getter
    private String displayErrorMessage = null;
    /**
     * display error code
     */
    @Getter
    private String readableErrorCode = null;
    /**
     * 错误跳转TARGET
     */
    @Getter
    @Setter
    private String target;
    /**
     * 错误跳转的URL
     */
    @Getter
    @Setter
    private String url;
    /**
     * 错误所属的Group
     */
    @Getter
    @Setter
    private String group;
    /**
     * the error content create the error.
     */
    @Getter
    @Setter
    private Map<String, Object> contents = new HashMap<String, Object>();
    /**
     * 请使用getReadableErrorCode(),以及新模式
     */
    @Deprecated
    @Getter
    private String oldCode;

    /**
     * 中文的展示文案
     */
    private boolean chineseDisplayMessage;

    /**
     * Whether force throw the exception.
     */
    @Getter
//    @Setter
    private boolean forceThrowException = false;


    private ErrorMessage(String code,
                         String message,
                         String displayMessage,
                         String oldCode,
                         String readableCode,
                         boolean isChineseDisplayMessage) {
        this.errorCode = code;
        this.errorMessage = message;
        this.displayErrorMessage = displayMessage;
        this.oldCode = oldCode;
        this.readableErrorCode = readableCode;
        this.chineseDisplayMessage = isChineseDisplayMessage;

        /**
         * 如果没有在加载的时候就明确全是中文,并且传入的显示错误文案不包含中文,
         * 则将显示的错误文案设置为默认中文文案。
         */
        if (displayMessage ==null || displayMessage.length() ==0 ||
            (!isChineseDisplayMessage
                && ErrorCode.notExistChineseCharacter(displayMessage))) {
            this.displayErrorMessage = ErrorCode.DEFAULT_DISPLAY_ERROR_MESSAGE;
        }

    }

    /**
     * @param errorCode the code.
     * @param message   the message.
     * @return
     */
    public static ErrorMessage of(String errorCode, String message) {
        return of(errorCode, message, null);
    }

//    public static ErrorMessage create(String errorCode, String message, boolean forceThrowException) {
//        return create(errorCode, message, null, forceThrowException);
//    }

    /**
     * 这个构造函数会从当前模块的resources/i18n/errors.properties文件
     * 中读取相关的错误码和错误文案，并且可以通过intellij的代码完成功能展示出相关的错误码。
     *
     * @param key
     * @param params
     * @return
     */
    public static ErrorMessage code(@PropertyKey(resourceBundle = ErrorCode.BUNDLE) String key,
                                    Object... params) {

        if (params != null && params.length > 0) {
            return ErrorCode.toErrorMessage(key, params);
        } else {  //没有参数的才可以cache
            ErrorMessage message = cachedMessages.get(key);
            if (message == null) {
                message = ErrorCode.toErrorMessage(key, params);
                cachedMessages.put(key, message);
            }
            return ErrorMessage.of(message.getErrorCode(), message.getErrorMessage(), message.getDisplayErrorMessage(),
                message.getReadableErrorCode(), message.chineseDisplayMessage);
        }
    }

    public static ErrorMessage code(boolean forceThrowException, @PropertyKey(resourceBundle = ErrorCode.BUNDLE) String key,
                                    Object... params) {

        ErrorMessage errorMessage = code(key, params);
        ErrorMessage output = ErrorMessage.of(errorMessage.getErrorCode(), errorMessage.getErrorMessage(), errorMessage.getDisplayErrorMessage(),
            errorMessage.getReadableErrorCode(), errorMessage.chineseDisplayMessage);
        output.forceThrowException = forceThrowException;
        return output;
    }

    /**
     * @param code           the code.
     * @param message        the message.
     * @param displayMessage the front display message.
     * @return
     */
    public static ErrorMessage of(String code,
                                  String message,
                                  String displayMessage) {
        return of(code, message, displayMessage, "");
    }


    /**
     * @param code           the code.
     * @param message        the message.
     * @param displayMessage the front display message.
     * @return
     */
    public static ErrorMessage of(String code,
                                  String message,
                                  String displayMessage,
                                  String readableCode) {
        return of(code, message, displayMessage, readableCode, false);
    }

    /**
     * @param code           the code.
     * @param message        the message.
     * @param displayMessage the front display message.
     * @return
     */
    public static ErrorMessage of(String code,
                                  String message,
                                  String displayMessage,
                                  String readableCode,
                                  boolean isChineseDisplayMessage) {
        return new ErrorMessage(code, message, displayMessage, readableCode, readableCode, isChineseDisplayMessage);
    }


    @SuppressWarnings("unused")
    public ErrorMessage addErrorContents(Map<String, Object> errorContents) {
        if (errorContents !=null && !errorContents.isEmpty()) {
            contents.putAll(errorContents);
        }
        return this;
    }

    /**
     * toSting 值显示核心内容
     *
     * @return
     */
    @Override
    public String toString() {
        String message = "ErrorMessage{" +
            "c='" + errorCode + '\'' +
            ", rC='" + readableErrorCode + '\'' +
            ", m='" + errorMessage + '\'';
        String content = contentToString();
        if (content!=null&&content.length()>0) {
            message = message +
                " ct=' " + content + '\'';
        }

        return message + "}";

    }


    public String getFullText() {
        return "ErrorMessage{" +
            "errorCode='" + errorCode + '\'' +
            ", severity=" + severity +
            ", errorMessage='" + errorMessage + '\'' +
            ", displayErrorMessage='" + displayErrorMessage + '\'' +
            ", readableErrorCode='" + readableErrorCode + '\'' +
            ", target='" + target + '\'' +
            ", url='" + url + '\'' +
            ", group='" + group + '\'' +
            ", contents=" + contents +
            ", oldCode='" + oldCode + '\'' +
            ", forceThrowException=" + forceThrowException +
            '}';
    }

    public String contentToString() {
        if (contents == null || contents.isEmpty())
            return "";

        StringBuilder buffer = new StringBuilder(128);
        for (Map.Entry<String, Object> p : contents.entrySet()) {
            if (buffer.length() > 0) {
                buffer.append(", ");
            }
            buffer.append(p.getKey()).append(" => ").append(p.getValue());
        }
        return "{" + buffer.toString() + "}";
    }

    public static void clean() {
        cachedMessages.clear();
    }

}
