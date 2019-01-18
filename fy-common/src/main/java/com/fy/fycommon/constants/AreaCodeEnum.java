package com.fy.fycommon.constants;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description: 国际区号枚举类
 * @author: cnc
 * @date: 2019-01-18 23:08
 */
public enum AreaCodeEnum {

    ZH_CN("ZH_CN", "86");

    private String respCode;
    private String respValue;

    AreaCodeEnum(String respCode, String respValue) {
        this.respCode = respCode;
        this.respValue = respValue;
    }

    // 普通方法
    public static String getName(String respName) {
        for (AreaCodeEnum c : AreaCodeEnum.values()) {
            if (respName.equals(c.getRespValue())) {
                return c.respCode;
            }
        }
        return null;
    }

    public static String getAreaCodeEnumValue(String respCode) {
        for (AreaCodeEnum BankCodeEnum : AreaCodeEnum.values()) {
            if (BankCodeEnum.getRespCode().equals(respCode)) {
                return BankCodeEnum.getRespValue();
            }
        }
        return null;
    }

    public static AreaCodeEnum getAreaCodeEnum(String respCode) {
        for (AreaCodeEnum BankCodeEnum : AreaCodeEnum.values()) {
            if (respCode.equals(BankCodeEnum.getRespCode())) {
                return BankCodeEnum;
            }
        }
        return null;
    }

    public static List<String> getAreaCodeList() {
        List<String> list = Lists.newArrayList();
        for (AreaCodeEnum areaCodeEnum : AreaCodeEnum.values()) {
            list.add(areaCodeEnum.getRespValue());
        }
        return list;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespValue() {
        return respValue;
    }

    public void setRespValue(String respValue) {
        this.respValue = respValue;
    }
}
