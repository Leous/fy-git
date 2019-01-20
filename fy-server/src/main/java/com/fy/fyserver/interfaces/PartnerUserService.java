package com.fy.fyserver.interfaces;

import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.results.Result;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-20 14:21
 */
public interface PartnerUserService {

    Result<PartnerUserDto> getPartnerUser(PartnerUserDto partnerUserDto);
}
