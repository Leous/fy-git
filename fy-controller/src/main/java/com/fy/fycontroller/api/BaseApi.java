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

    // 登录接口
    protected static final String API_LOGIN = API_VERSION + "/" + "login";//用户登录接口前缀
    protected static final String API_LOGIN_TOLOGIN = "toLogin";//登录页面跳转
    protected static final String API_LOGIN_GETAREACODE = "getAreaCode";//手机号登录时获取国际区号接口
    protected static final String API_LOGIN_DOLOGIN = "doLogin";//执行登录操作

    //注册接口
    protected static final String API_REGISTER = API_VERSION + "/" + "register";//用户注册接口前缀
    protected static final String API_REGISTER_DOREGISTER = "doRegister";//执行注册操作
    protected static final String API_REGISTER_TOREGISTER = "toRegister";//跳转注册页面
    protected static final String API_REGISTER_VERIFYACCOUNT = "verifyAccount";//账户密码注册页面输入用户名异步验证账号有效性接口

    //用户相关接口
    protected static final String API_PARTNER = API_VERSION + "/" + "partner";
    protected static final String API_PARTNER_INFO = "info";

    //用户系统菜单
    protected static final String API_PARTNER_MENUS = API_VERSION +  "/" + "partnerMenus";
    protected static final String API_PARTNER_MENUS_LIST = "list";

    //首页
    protected static final String API_HOME = API_VERSION + "/" + "home";
    protected static final String API_HOME_INDEX = "index";

}