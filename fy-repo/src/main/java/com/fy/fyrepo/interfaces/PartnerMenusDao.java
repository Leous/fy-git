package com.fy.fyrepo.interfaces;

import com.fy.fyentity.dtos.PartnerMenusDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-22 19:33
 */
@Mapper
public interface PartnerMenusDao {
    /**
     * @description: 获取菜单列表
     * @author: cnc
     * @date: 2019-01-22 19:34:03
     * @param partnerMenusDto
     * @return: java.util.List<com.fy.fyentity.dtos.PartnerMenusDto>
     */
    List<PartnerMenusDto> getPartnerMenusList(PartnerMenusDto partnerMenusDto);
}
