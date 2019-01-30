package com.fy.fycontroller.api;

import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.requests.RegisterRequest;
import com.fy.fyentity.requests.VerifyAccountRequest;
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
import java.util.Date;

/**
 * @description: 注册相关接口
 * @author: cnc
 * @date: 2019-01-30 16:16:23
 */
@Controller
@RequestMapping(value = BaseApi.API_REGISTER)
@Api(value = BaseApi.API_REGISTER, tags = "Register Api", description = "注册相关接口")
@Slf4j
public class RegisterApi extends BasicApi {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerUserService partnerUserService;

    /**
     * @description: 跳转至注册页面
     * @author: cnc
     * @date: 2019-01-14 23:53:22
     * @param request
     * @return: java.lang.String
     */
    @RequestMapping(value = BaseApi.API_REGISTER_TOREGISTER, method = RequestMethod.GET)
    @ApiOperation(value="跳转至注册页面", httpMethod="GET")
    public String toRegister(HttpServletRequest request) {
        return "/basic/register";
    }

    /**
     * @description: 注册验证账户是否被使用过
     * @author: cnc
     * @date: 2019-01-20 15:08:14
     * @param request
     * @param verifyAccountRequest
     * @return: com.fy.fyentity.results.ResponseEntry
     */
    @RequestMapping(value = BaseApi.API_REGISTER_VERIFYACCOUNT)
    @ResponseBody
    @ApiOperation(value="验证用户名", httpMethod="POST")
    public ResponseEntry verifyAccount(HttpServletRequest request, @Validated @RequestBody VerifyAccountRequest verifyAccountRequest){
        try {
            ResponseEntry responseEntry = verifyParam(request, verifyAccountRequest);
            if(responseEntry != null){
                return responseEntry;
            }

            String account = verifyAccountRequest.getAccount();

            if(FyUtils.isEmpty(account)) {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请输入账号.", "", null);
            }

            PartnerUserDto partnerUserDto = new PartnerUserDto();
            partnerUserDto.setAccount(account);
            Result<PartnerUserDto> findAccountResult = partnerUserService.getPartnerUser(partnerUserDto);
            if(!findAccountResult.isSuccess()){
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), findAccountResult.getReturnMessage(), "", null);
            }else {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), findAccountResult.getReturnMessage(), "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[用户注册事件] ip = [" + FyUtils.getIpAddr(request) + "], 用户 = [" + verifyAccountRequest.getAccount() + "], 验证用户名异常! Exception=" + e);
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }

    /**
     * @param request
     * @param registerRequest
     * @description: 用户注册(只处理账号密码登录情况)，其他通过手机或第三方软件首次注册的用户直接用登录接口实现
     * @author: cnc
     * @date: 2019-01-07 01:23:16
     * @return: com.fy.fyentity.results.ResponseEntry<java.lang.String>
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_REGISTER_DOREGISTER, method = RequestMethod.POST)
    @ApiOperation(value="用户注册", httpMethod="POST")
    public ResponseEntry<String> register(HttpServletRequest request, @Validated @RequestBody RegisterRequest registerRequest) {
        try {
            //验证请求签名
            ResponseEntry responseEntry = verifyParam(request, registerRequest);
            if (responseEntry != null) {
                return responseEntry;
            }

            String type = registerRequest.getType();
            String account = registerRequest.getAccount();
            String password = registerRequest.getPassword();
            String comfirmPassword = registerRequest.getComfirmPassword();

            if (FyUtils.isEmpty(type) && !"2".equals(type)) {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请选择注册方式.", "", null);
            }

            //账号密码注册必须输入账号+密码+确认密码
            if (FyUtils.isEmpty(account)) {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请输入账号.", "", null);
            }
            if (FyUtils.isEmpty(password)) {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请输入密码.", "", null);
            }
            if (FyUtils.isEmpty(comfirmPassword)) {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "请输入确认密码.", "", null);
            }
            if(!comfirmPassword.equals(password)){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "两次输入的密码不一致.", "", null);
            }

            String partnerId = FyUtils.getRandomNum(8);
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setType(Integer.parseInt(type));
            //partnerId：默认6位随机数字
            partnerDto.setPartnerId(partnerId);
            partnerDto.setPartnerName(account);
            partnerDto.setCreateTime(new Date());

            //盐：6位随机字母
            String salts = FyUtils.genRandomNum(6);
            PartnerUserDto partnerUserDto = new PartnerUserDto();
            partnerUserDto.setAccount(account);
            partnerUserDto.setSalts(salts);
            partnerUserDto.setPartnerId(partnerId);
            partnerUserDto.setRoleId(2);
            //加密方式：前端MD5(password)，服务器端MD5(password, salts)
            partnerUserDto.setPassword(FyUtils.getCertifiedSigned(password, salts));
            partnerUserDto.setCreateTime(new Date());
            partnerUserDto.setIp(FyUtils.getIpAddr(request));

            Result<Integer> registerResult = partnerService.register(partnerDto, partnerUserDto);
            if(registerResult.isSuccess()){
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), "注册成功.", "", null);
            }else {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), registerResult.getReturnMessage(), "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[用户注册事件] ip = [" + FyUtils.getIpAddr(request) + "], 用户 = [" + registerRequest.getAccount() + "], 注册异常! Exception=" + e);
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }
}
