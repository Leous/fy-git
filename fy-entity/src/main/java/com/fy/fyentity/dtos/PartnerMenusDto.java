package com.fy.fyentity.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartnerMenusDto extends BasicDto implements Serializable {
    private static final long serialVersionUID = -8628081453137770698L;

    private Integer pmid;

    private Integer parentPmid;

    private String menuType;

    private String menuCode;

    private String menuNameCh;

    private String menuNameEn;

    private String menuUrl;

    private Integer displayOrder;

    private Integer status;

    private Integer roleId;
}