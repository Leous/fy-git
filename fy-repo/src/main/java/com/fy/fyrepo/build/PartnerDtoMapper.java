package com.fy.fyrepo.build;

import com.fy.fyrepo.build.PartnerDto;

public interface PartnerDtoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PartnerDto record);

    int insertSelective(PartnerDto record);

    PartnerDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PartnerDto record);

    int updateByPrimaryKey(PartnerDto record);
}