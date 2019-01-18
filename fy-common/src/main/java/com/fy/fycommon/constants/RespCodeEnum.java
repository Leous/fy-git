package com.fy.fycommon.constants;

/**
 * @description: api返回码枚举类
 * @author: cnc
 * @date: 2019-01-07 1:15
 */
public enum RespCodeEnum {
    SUCCESS("0000", "请求成功", "SUCCESS"),
    UNKNOWN_ERROR("4000", "未知错误", "UNKNOWN ERROR"),
    BUSINESS_PARAMETER_ERROR("1000", "参数错误", "PARAMETER ERROR"),
    BUSINESS_SIGNATURE_ERROR("1003", "签名错误", "SIGNATURE ERROR"),
    BUSINESS_ERROR("3000", "业务异常", "BUSINESS ERROR"),
    HTTP_BAD_REQUEST("400", "请求错误", "BAD REQUEST"),
    HTTP_NOT_AUTHORIZATION("401", "用户未认证", "AUTHORIZATION"),
    HTTP_METHOD_NOT_ALLOWED("405", "拒绝请求此资源", "METHOD NOT ALLOWED"),
    HTTP_NOT_ACCEPTABLE("406", "响应资源失败", "NOT ACCEPTABLE"),
    HTTP_INTERNAL_SERVER_ERROR("500", "服务不可调用", "INTERNAL SERVER ERROR"),
    SERVER_RUNTIME_EXCEPTION("9000", "服务异常", "SERVER ERROR"),
    SERVER_NULL_POINT_EXCEPTION("9001", "空指针异常", "NULL POINT EXCEPTION"),
    SERVER_CLASS_CAST_EXCEPTION("9002", "类型转换异常", "CLASS CAST EXCEPTION"),
    SERVER_IO_EXCEPTION("9003", "IO异常", "IO EXCEPTION"),
    SERVER_NO_SUCH_METHOD_EXCEPTION("9004", "未知方法异常", "NO SUCH METHOD EXCEPTION"),
    SERVER_INDEX_OUT_BOUNDS_EXCEPTION("9005", "数组越界异常", "INDEX OUT BOUNDS EXCEPTION"),
    SERVER_SOCKET_EXCEPTION("9006", "SOCKET异常", "SOCKET EXCEPTION");

    private final String code;
    private final String reasonCNPhrase;
    private final String reasonPhrase;

    private RespCodeEnum(String code, String reasonCNPhrase, String reasonPhrase) {
        this.code = code;
        this.reasonCNPhrase = reasonCNPhrase;
        this.reasonPhrase = reasonPhrase;
    }

    public String code() {
        return this.code;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String getReasonCNPhrase() {
        return this.reasonCNPhrase;
    }
}
