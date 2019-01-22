package com.fy.fyserver.interfaces;

import com.fy.fyentity.dtos.PartnerMenusDto;
import com.fy.fyentity.results.Result;

import java.util.List;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-22 19:31
 */
public interface PartnerMenusService {

    /**
     * @description: 获取菜单列表
     * @author: cnc
     * @date: 2019-01-22 19:36:13
     * @param partnerMenusDto
     * @return: com.fy.fyentity.results.Result<java.util.List<com.fy.fyentity.dtos.PartnerMenusDto>>
     */
    Result<List<PartnerMenusDto>> getPartnerMenusList(PartnerMenusDto partnerMenusDto);
}
