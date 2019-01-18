package com.fy.fycommon.exceptions;

/**
 * @description: 自定义异常
 * @author: cnc
 * @date: 2019-01-10 21:44:19
 */
public class AuthorizedException extends Exception {
    public AuthorizedException() {
    }

    public AuthorizedException(String message) {
        super(message);
    }

    public AuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
