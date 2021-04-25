package com.github.oneyoung.portal.exception;

import com.oneyoung.common.message.ErrorMessage;

/**
 * PortalException
 *
 * @author oneyoung
 * @date 2021/4/25 17:18
 */
public class PortalException extends RuntimeException {

    private static final long serialVersionUID = 3093550613739905915L;

    public PortalException(String key) {
        super(ErrorMessage.code(key, (Object) null).getDisplayErrorMessage());
    }

    public PortalException(String key, Object[] args) {
        super(ErrorMessage.code(key, args).getDisplayErrorMessage());
    }
}
