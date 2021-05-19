package com.github.oneyoung.portal.exception;

import com.oneyoung.common.message.ErrorCodeException;

/**
 * PortalException
 *
 * @author oneyoung
 * @date 2021/4/25 17:18
 */
public class PortalException extends ErrorCodeException {

    private static final long serialVersionUID = 3093550613739905915L;

    public PortalException(String key, Object... args) {
        super(key, args);
    }
}
