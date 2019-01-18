package com.fy.fycommon.exceptions;

/**
 * @description: 自定义异常
 * @author: cnc
 * @date: 2019-01-10 21:39:37
 */
public class ApiException extends Exception {

    private static final long serialVersionUID = 7429071409775533452L;

    private String errCode;

    private String errMsg;

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
