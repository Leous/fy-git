package com.fy.fyrepo.build;

import com.fy.fyrepo.build.PartnerUserDto;

public interface PartnerUserDtoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartnerUserDto record);

    int insertSelective(PartnerUserDto record);

    PartnerUserDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartnerUserDto record);

    int updateByPrimaryKey(PartnerUserDto record);
}