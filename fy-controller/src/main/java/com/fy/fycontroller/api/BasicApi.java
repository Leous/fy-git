package com.fy.fycontroller.api;

import com.fy.fycommon.constants.FyConstants;
import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.requests.BaseRequest;
import com.fy.fyentity.results.ResponseEntry;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @description: 接口公共方法
 * @author: cnc
 * @date: 2019-01-08 0:36
 */
@Slf4j
public class BasicApi {

    /**
     * @description: 公共参数验证
     * @author: cnc
     * @date: 2019-01-19 16:37:36
     * @param request
     * @param baseRequest
     * @return: com.fy.fyentity.results.ResponseEntry
     */
    public ResponseEntry verifyParam(HttpServletRequest request, BaseRequest baseRequest) {
        try {
            //时间戳必须传，且只能相差服务端当前时间 最多5分钟
            Long timestamp = baseRequest.getTimestamp();

            if(timestamp == null){
                return new ResponseEntry(RespCodeEnum.HTTP_NOT_AUTHORIZATION.code(), RespCodeEnum.HTTP_NOT_AUTHORIZATION.getReasonCNPhrase(), "", null);
            }

            long now = System.currentTimeMillis();
            if((now - Long.valueOf(timestamp)) > FyConstants.COUNT_DOWN_TIME){
                return new ResponseEntry(RespCodeEnum.HTTP_BAD_REQUEST.code(), RespCodeEnum.HTTP_BAD_REQUEST.getReasonCNPhrase(), "", null);
            }

            String sign = baseRequest.getSign();

            //验签
            Map requestMap = createSignMap(baseRequest);
            String paramString = createSignString(requestMap);
            if (FyUtils.MD5(paramString).equals(sign)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntry(RespCodeEnum.HTTP_NOT_AUTHORIZATION.code(), RespCodeEnum.HTTP_NOT_AUTHORIZATION.getReasonCNPhrase(), "", null);
        }
        return new ResponseEntry(RespCodeEnum.HTTP_NOT_AUTHORIZATION.code(), RespCodeEnum.HTTP_NOT_AUTHORIZATION.getReasonCNPhrase(), "", null);
    }

    private static String createSignString(Map<String, Object> params) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        StringBuilder content = new StringBuilder();
        String key = "";
        String value = "";
        for (int i = 0; i < keys.size(); i++) {
            key = (String) keys.get(i);
            if (params.get(key) instanceof org.joda.time.DateTime) {
                value = ((org.joda.time.DateTime) params.get(key)).toDate().getTime() + "";
            } else {
                value = String.valueOf(params.get(key));
            }
            if ("".equals(value) || "null".equals(value) || value == null) {
                continue;
            } else {
                if (key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("signType")) {
                    continue;
                }
                content.append(key).append("=").append(value).append("&");
            }
        }

        return content.length() > 0 ? content.deleteCharAt(content.length() - 1).toString() : "";
    }

    public static <T> Map<String, Object> createSignMap(T target) throws Exception {
        Map<String, Object> params = new HashMap<>(16);

        if (target instanceof Map) {
            params = (Map) target;
        } else {
            Class<?> clazz = target.getClass();
            while (!Object.class.equals(clazz) && clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    params.put(field.getName(), field.get(target));
                }

                clazz = clazz.getSuperclass();
            }
        }

        return params;
    }
}
