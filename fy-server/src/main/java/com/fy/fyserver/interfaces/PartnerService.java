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

    Result<Integer> register(PartnerDto partnerDto, PartnerUserDto partnerUserDto);
}
