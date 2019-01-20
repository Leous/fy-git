package com.fy.fyentity.requests;

import lombok.Data;

/**
 * @description: 登录请求参数
 * @author: cnc
 * @date: 2019-01-18 21:05
 */
@Data
public class LoginRequest extends BaseRequest {

    private String type;//注册类型，0：后台注册；1：手机号注册；2：账号注册

    private String account;//账号

    private String password;//密码

    private String areaCode;//国家 手机前缀

    private String tel;//手机号注册

    private String verifyCode;//手机验证码
}
