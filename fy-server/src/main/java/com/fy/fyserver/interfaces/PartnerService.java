package com.fy.fyserver.interfaces;

import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.results.Result;

/**
 * @description: 用户service
 * @author: cnc
 * @date: 2019-01-07 1:30
 */
public interface PartnerService {

    /**
     * @description: 注册
     * @author: cnc
     * @date: 2019-01-20 18:56:18
     * @param partnerDto
     * @param partnerUserDto
     * @return: com.fy.fyentity.results.Result<java.lang.Integer>
     */
    Result<Integer> register(PartnerDto partnerDto, PartnerUserDto partnerUserDto);

    /**
     * @description: 根据条件获取用户信息
     * @author: cnc
     * @date: 2019-01-20 18:56:30
     * @param partnerDto
     * @return: com.fy.fyentity.results.Result<com.fy.fyentity.dtos.PartnerDto>
     */
    Result<PartnerDto> getPartner(PartnerDto partnerDto);
}
