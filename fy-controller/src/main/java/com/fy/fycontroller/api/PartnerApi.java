package com.fy.fycontroller.api;

import com.fy.fycommon.constants.AreaCodeEnum;
import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.requests.RegisterRequest;
import com.fy.fyentity.results.ResponseEntry;
import com.fy.fyentity.results.Result;
import com.fy.fyserver.interfaces.PartnerService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @description: 用户相关接口汇总
 * @author: cnc
 * @date: 2019-01-05 21:21
 */
@Controller
@RequestMapping(value = BaseApi.API_USER)
public class PartnerApi {

    @Autowired
    private PartnerService partnerService;

    /**
     * @description: 获取国际区号列表，暂时只支持+86
     * @author: cnc
     * @date: 2019-01-19 00:55:13
     * @param request
     * @return: com.fy.fyentity.results.ResponseEntry<java.util.List<java.lang.String>>
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_PARTNER_GETAREACODE)
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntry;
    }

    /**
     * @description: 跳转至注册页面
     * @author: cnc
     * @date: 2019-01-14 23:53:22
     * @param request
     * @return: java.lang.String
     */
    @RequestMapping(value = BaseApi.API_PARTNER_TOREGISTER, method = RequestMethod.GET)
    public String toRegister(HttpServletRequest request) {
        return "/basic/register";
    }

    /**
     * @description: 跳转至登录页面
     * @author: cnc
     * @date: 2019-01-14 23:53:56
     * @param request
     * @return: java.lang.String
     */
    @RequestMapping(value = BaseApi.API_PARTNER_TOLOGIN, method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {
        return "/basic/login";
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
    @RequestMapping(value = BaseApi.API_PARTNER_REGISTER, method = RequestMethod.POST)
    public ResponseEntry<String> register(HttpServletRequest request, @RequestBody RegisterRequest registerRequest) {
        ResponseEntry<String> responseEntry = new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "", "", null);

        try {
            String type = registerRequest.getType();
            String account = registerRequest.getAccount();
            String password = registerRequest.getPassword();
            String comfirmPassword = registerRequest.getComfirmPassword();

            if (FyUtils.isEmpty(type) && !"2".equals(type)) {
                responseEntry.setMessage("请选择注册方式");
                return responseEntry;
            }

            //账号密码注册必须输入账号+密码+确认密码
            if (FyUtils.isEmpty(account)) {
                responseEntry.setMessage("请输入账号");
                return responseEntry;
            }
            if (FyUtils.isEmpty(password)) {
                responseEntry.setMessage("请输入密码");
                return responseEntry;
            }
            if (FyUtils.isEmpty(comfirmPassword)) {
                responseEntry.setMessage("请输入确认密码");
                return responseEntry;
            }
            if(!comfirmPassword.equals(password)){
                responseEntry.setMessage("两次输入的密码不一致");
                return responseEntry;
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
            //加密方式：前端MD5(password)，服务器端MD5(password, salts)
            partnerUserDto.setPassword(FyUtils.getCertifiedSigned(password, salts));
            partnerUserDto.setCreateTime(new Date());

            Result<Integer> registerResult = partnerService.register(partnerDto, partnerUserDto);
            if(registerResult.isSuccess()){
                responseEntry.setCode(RespCodeEnum.SUCCESS.code());
                responseEntry.setMessage("注册成功");
            }else {
                responseEntry.setMessage(registerResult.getReturnMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntry;
    }
}