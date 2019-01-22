package com.fy.fyserver.impls;

import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerMenusDto;
import com.fy.fyentity.results.Result;
import com.fy.fyrepo.interfaces.PartnerMenusDao;
import com.fy.fyserver.interfaces.PartnerMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-22 19:31
 */
@Service
public class PartnerMenusServiceImpl implements PartnerMenusService {

    @Autowired
    private PartnerMenusDao partnerMenusDao;

    @Override
    public Result<List<PartnerMenusDto>> getPartnerMenusList(PartnerMenusDto partnerMenusDto) {
        Result<List<PartnerMenusDto>> result = new Result<>(false, "获取菜单列表失败", "");
        try {
            List<PartnerMenusDto> menusListResult = partnerMenusDao.getPartnerMenusList(partnerMenusDto);
            if(FyUtils.isCollectionNull(menusListResult)){
                return result;
            }
            result.setSuccess(true);
            result.setEntry(menusListResult);
            result.setReturnMessage("成功获取菜单列表");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
