package top.oneyoung.portal.exception;

import top.oneyoung.common.message.ErrorCodeException;

/**
 * PortalException
 *
 * @author oneyoung
 * @since 2021/4/25 17:18
 */
public class PortalException extends ErrorCodeException {

    private static final long serialVersionUID = 3093550613739905915L;

    public PortalException(String key, Object... args) {
        super(key, args);
    }
}
