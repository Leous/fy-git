package com.fy.fyserver.impls;

import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.results.Result;
import com.fy.fyrepo.interfaces.PartnerUserDao;
import com.fy.fyserver.interfaces.PartnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-20 14:24
 */
@Service
public class PartnerUserServiceImpl implements PartnerUserService {

    @Autowired
    private PartnerUserDao partnerUserDao;

    @Override
    public Result<PartnerUserDto> getPartnerUser(PartnerUserDto partnerUserDto) {
        Result<PartnerUserDto> result = new Result<>(false, "用户名允许使用.", "");
        try {
            PartnerUserDto findPartnerUser = partnerUserDao.getPartnerUser(partnerUserDto);
            if(findPartnerUser != null){
                result.setSuccess(true);
                result.setReturnMessage("用户名已被使用，请重新填入.");
                result.setEntry(findPartnerUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
