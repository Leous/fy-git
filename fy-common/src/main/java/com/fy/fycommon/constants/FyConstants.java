package com.fy.fycommon.constants;

/**
 * @description: 特殊常量
 * @author: cnc
 * @date: 2019-01-07 0:51
 */
public class FyConstants {

    public static final String SECRET_KEY = "591e9a63ebc91d85138ab4e4bd8b671d";

    //timestamp访问限制，只允许差当前时间3分钟的时间戳才有资格访问接口
    public static final Long COUNT_DOWN_TIME = 180000L;
}
