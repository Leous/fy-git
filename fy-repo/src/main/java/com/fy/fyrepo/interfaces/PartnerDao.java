package com.fy.fyrepo.interfaces;

import com.fy.fyentity.dtos.PartnerDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-07 16:42
 */
@Mapper
public interface PartnerDao {

    /**
     * @description: 新增用户
     * @author: cnc
     * @date: 2019-01-07 17:28:42
     * @param partnerDto
     * @return: java.lang.Integer
     */
    Integer insertPartner(PartnerDto partnerDto);

    /**
     * @description: 查询用户是否存在
     * @author: cnc
     * @date: 2019-01-11 00:19:23
     * @param partnerDto
     * @return: com.fy.fyentity.dtos.PartnerDto
     */
    PartnerDto getPartner(PartnerDto partnerDto);
}
