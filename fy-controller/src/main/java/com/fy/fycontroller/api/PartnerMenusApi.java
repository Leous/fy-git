package com.fy.fycontroller.api;

import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerMenusDto;
import com.fy.fyentity.requests.GetMenusRequest;
import com.fy.fyentity.results.ResponseEntry;
import com.fy.fyentity.results.Result;
import com.fy.fyserver.interfaces.PartnerMenusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 用户系统菜单
 * @author: cnc
 * @date: 2019-01-22 16:22
 */
@Controller
@RequestMapping(value = BaseApi.API_PARTNER_MENUS)
@Api(value = BaseApi.API_PARTNER_MENUS, tags = "PartnerMenus Api", description = "用户系统菜单相关")
@Slf4j
public class PartnerMenusApi extends BasicApi {

    @Autowired
    private PartnerMenusService partnerMenusService;

    /**
     * @description: 获取菜单目录
     * @author: cnc
     * @date: 2019-01-22 17:40:36
     * @param request
     * @return: com.fy.fyentity.results.ResponseEntry<java.util.List>
     */
    @ResponseBody
    @ApiOperation(value = "查询菜单目录", httpMethod = "POST")
    @RequestMapping(value = BaseApi.API_PARTNER_MENUS_LIST, method = RequestMethod.POST)
    public ResponseEntry<List> getPartnerMenusList(HttpServletRequest request, @Validated @RequestBody GetMenusRequest getMenusRequest){
        try {
            ResponseEntry responseEntry = verifyParam(request, getMenusRequest);
            if(responseEntry != null){
                return responseEntry;
            }

            String type = getMenusRequest.getType();
            String isLogin = getMenusRequest.getIsLogin();

            if(FyUtils.isEmpty(type)){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "查询菜单失败", "", null);
            }

            PartnerMenusDto partnerMenusDto = new PartnerMenusDto();
            partnerMenusDto.setMenuType(type);
            if("0".equals(isLogin)) {
                //未登录用户
            } else if("1".equals(isLogin)) {
                //有登录用户

            } else {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "查询菜单失败", "", null);
            }
            Result<List<PartnerMenusDto>> menusList = partnerMenusService.getPartnerMenusList(partnerMenusDto);
            if(menusList.isSuccess()) {
                //当查询到菜单时才会覆盖页面的静态菜单，其他查询失败情况都不会影响静态菜单的显示
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), menusList.getReturnMessage(), "", menusList.getEntry());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[用户常规事件] ip = [" + FyUtils.getIpAddr(request) + "], 获取菜单列表异常! Exception=" + e);
        }
        return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "查询菜单失败", "", null);
    }
}
