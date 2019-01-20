package com.fy.fyentity.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PartnerDto extends BasicDto implements Serializable {

    private static final long serialVersionUID = -6234880372886737252L;

    private Integer id;

    private String partnerId;

    private String partnerName;

    private Integer puid;

    private Integer age;

    private Date birthday;

    private String areaCode;

    private String tel;

    private String email;

    private Integer type;

    private Date createTime;

    private Integer basicStatus;
}