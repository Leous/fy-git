package com.fy.fyentity.requests;

import lombok.Data;

/**
 * @description: 获取菜单dto
 * @author: cnc
 * @date: 2019-01-22 19:02
 */
@Data
public class GetMenusRequest extends BaseRequest {

    private String type;//根据不同情况获取不同类型的菜单

    private String isLogin;//是否是登录情况
}
