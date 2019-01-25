package com.fy;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @description: 依据alibaba druid连接数据库方式生成加密后的密码
 * @author: cnc
 * @date: 2019-01-25 14:50
 */
public class GenerateSqlSecert {

    public static void main(String[] args) throws Exception {

        String[] keyPairs = ConfigTools.genKeyPair(1024);
        System.out.println(keyPairs[0]);//私钥
        System.out.println(keyPairs[1]);//公钥
        System.out.println(ConfigTools.encrypt(keyPairs[0], "123456"));//加密后的数据库密码
    }
}
