package com.fy.fyentity.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 公共属性[分页]
 * @author: cnc
 * @date: 2019-01-07 0:07
 */
@Data
public class BasicDto implements Serializable {

    private static final long serialVersionUID = -7754280406906521152L;

    //每页几条
    private Integer pageSize;

    //页码
    private Integer pageIndex;
}
