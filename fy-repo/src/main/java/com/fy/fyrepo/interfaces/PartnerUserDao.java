package com.fy.fyrepo.interfaces;


import com.fy.fyentity.dtos.PartnerUserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-07 15:50
 */
@Mapper
public interface PartnerUserDao {

    /**
     * @description: 插入用户操作员信息
     * @author: cnc
     * @date: 2019-01-11 00:20:10
     * @param partnerUserDto
     * @return: java.lang.Integer
     */
    Integer insertPartnerUser(PartnerUserDto partnerUserDto);

    /**
     * @description: 查询用户操作员信息
     * @author: cnc
     * @date: 2019-01-11 00:20:36
     * @param partnerUserDto
     * @return: com.fy.fyentity.dtos.PartnerUserDto
     */
    PartnerUserDto getPartnerUser(PartnerUserDto partnerUserDto);
}
