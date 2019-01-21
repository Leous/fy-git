package com.fy.fyentity.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PartnerUserDto implements Serializable {

    private static final long serialVersionUID = 7887502682747887334L;

    private Integer id;

    private String partnerId;

    private Integer roleId;

    private String account;

    private String password;

    private String salts;

    private String ip;

    private Date createTime;
}