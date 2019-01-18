package com.fy.fyentity.requests;

import lombok.Data;

/**
 * @description: 注册请求
 * @author: cnc
 * @date: 2019-01-07 0:59
 */
@Data
public class RegisterRequest extends BaseRequest {

    private String type;//注册类型，0：后台注册；1：手机号注册；2：账号注册

    private String account;//账号

    private String password;//密码

    private String comfirmPassword;//确认密码
}
