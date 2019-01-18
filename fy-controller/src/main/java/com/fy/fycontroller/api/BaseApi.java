package com.fy.fycontroller.api;

/**
 * @description: api汇总管理
 * @author: cnc
 * @date: 2019-01-05 21:07:20
 */
public abstract class BaseApi {
    public static final String API_VERSION = "/api/v1";

    //基础接口，共用接口
    protected static final String API_VERIFYCODE = API_VERSION + "/" + "basic";

    //用户接口
    protected static final String API_USER = API_VERSION + "/" + "partner";
    protected static final String API_PARTNER_REGISTER = "register";
    protected static final String API_PARTNER_TOREGISTER = "toRegister";
    protected static final String API_PARTNER_TOLOGIN = "toLogin";
    public static final String API_PARTNER_GETAREACODE = "getAreaCode";
    protected static final String API_PARTNER_INFO = "info";
    protected static final String API_PARTNER_LOGIN = "basic";
}