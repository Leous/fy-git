package com.fy.fycontroller.api;

import com.fy.fycommon.constants.AreaCodeEnum;
import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.requests.LoginRequest;
import com.fy.fyentity.results.ResponseEntry;
import com.fy.fyentity.results.Result;
import com.fy.fyserver.interfaces.PartnerService;
import com.fy.fyserver.interfaces.PartnerUserService;
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
 * @description: 登录相关接口
 * @author: cnc
 * @date: 2019-01-30 16:15:52
 */
@Controller
@RequestMapping(value = BaseApi.API_LOGIN)
@Api(value = BaseApi.API_LOGIN, tags = "Login Api", description = "登录相关接口")
@Slf4j
public class LoginApi extends BasicApi {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerUserService partnerUserService;

    /**
     * @description: 获取国际区号列表，暂时只支持+86
     * @author: cnc
     * @date: 2019-01-19 00:55:13
     * @param request
     * @return: com.fy.fyentity.results.ResponseEntry<java.util.List<java.lang.String>>
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_LOGIN_GETAREACODE)
    @ApiOperation(value="获取国际区号列表", httpMethod="POST")
    public ResponseEntry<List<String>> getAreaCodeList(HttpServletRequest request){
        ResponseEntry<List<String>> responseEntry = new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "", "", null);
        try {
            List<String> areaCodeList = AreaCodeEnum.getAreaCodeList();
            if(areaCodeList.size() > 0){
                responseEntry.setCode(RespCodeEnum.SUCCESS.code());
                responseEntry.setMessage("区号列表查询成功");
                responseEntry.setEntry(areaCodeList);
            }else {
                responseEntry.setMessage("无区号列表");
            }
            return responseEntry;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[用户常规事件] ip = [" + FyUtils.getIpAddr(request) + "], 获取区号列表异常! Exception=" + e);
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }

    /**
     * @description: 跳转至登录页面
     * @author: cnc
     * @date: 2019-01-14 23:53:56
     * @param request
     * @return: java.lang.String
     */
    @RequestMapping(value = BaseApi.API_LOGIN_TOLOGIN, method = RequestMethod.GET)
    @ApiOperation(value="跳转至登录页面", httpMethod="GET")
    public String toLogin(HttpServletRequest request) {
        return "/basic/login";
    }

    /**
     * @description: 登录接口
     * @author: cnc
     * @date: 2019-01-20 18:31:29
     * @param loginRequest
     * @return: com.fy.fyentity.results.ResponseEntry
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_LOGIN_DOLOGIN)
    @ApiOperation(value="用户登录", httpMethod="POST")
    public ResponseEntry login(HttpServletRequest request, @Validated @RequestBody LoginRequest loginRequest){
        try {
            ResponseEntry responseEntry = verifyParam(request, loginRequest);
            if(responseEntry != null){
                return responseEntry;
            }

            String type = loginRequest.getType();

            if(FyUtils.isEmpty(type) && "2".equals(type)){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请选择登录方式.", "", null);
            }

            String account = loginRequest.getAccount();
            String password = loginRequest.getPassword();

            if(FyUtils.isEmpty(account) || FyUtils.isEmpty(password)){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请输入账号密码.", "", null);
            }

            PartnerUserDto partnerUserDto = new PartnerUserDto();
            partnerUserDto.setAccount(account);
            Result<PartnerUserDto> findPartnerUser = partnerUserService.getPartnerUser(partnerUserDto);
            if(!findPartnerUser.isSuccess()){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "用户名尚未注册.", "", null);
            }
            //获取查询到的用户信息，供后续使用
            partnerUserDto = findPartnerUser.getEntry();

            if(!FyUtils.getCertifiedSigned(password, partnerUserDto.getSalts()).equals(partnerUserDto.getPassword())){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "用户密码错误.", "", null);
            }

            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setPuid(partnerUserDto.getId());
            partnerDto.setPartnerId(partnerUserDto.getPartnerId());
            Result<PartnerDto> findPartner = partnerService.getPartner(partnerDto);
            if(!findPartner.isSuccess()){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "用户名尚未注册.", "", null);
            }

            partnerDto = findPartner.getEntry();

            if(partnerDto.getBasicStatus() == 0){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "此用户已被封，无法正常登陆.", "", null);
            } else if (partnerDto.getBasicStatus() == 1){
                log.info("[用户登录事件] ip = [" + FyUtils.getIpAddr(request) + "], 用户 = [" + account + "]，登录成功!");
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), "登录成功.", "", null);
            } else if (partnerDto.getBasicStatus() == 2){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "此用户已被注销，无法正常登陆.", "", null);
            } else{
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "用户状态异常，无法正常登陆.", "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[用户登录事件] ip = [" + FyUtils.getIpAddr(request) + "], 用户 = [" + loginRequest.getAccount() + "], 登录异常! Exception=" + e);
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }
}
