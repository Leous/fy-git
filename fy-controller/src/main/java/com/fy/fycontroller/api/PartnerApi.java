package com.fy.fycontroller.api;

import com.fy.fycommon.constants.AreaCodeEnum;
import com.fy.fycommon.constants.RespCodeEnum;
import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.requests.LoginRequest;
import com.fy.fyentity.requests.RegisterRequest;
import com.fy.fyentity.requests.VerifyAccountRequest;
import com.fy.fyentity.results.ResponseEntry;
import com.fy.fyentity.results.Result;
import com.fy.fyserver.interfaces.PartnerService;
import com.fy.fyserver.interfaces.PartnerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping(value = BaseApi.API_PARTNER)
@Api(value = BaseApi.API_PARTNER, tags = "Partner Api", description = "用户相关")
public class PartnerApi extends BasicApi {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private PartnerUserService partnerUserService;

    /**
     * @description: 注册验证账户是否被使用过
     * @author: cnc
     * @date: 2019-01-20 15:08:14
     * @param request
     * @param verifyAccountRequest
     * @return: com.fy.fyentity.results.ResponseEntry
     */
    @RequestMapping(value = BaseApi.API_PARTNER_VERIFY_ACCOUNT)
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
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }

    /**
     * @description: 获取国际区号列表，暂时只支持+86
     * @author: cnc
     * @date: 2019-01-19 00:55:13
     * @param request
     * @return: com.fy.fyentity.results.ResponseEntry<java.util.List<java.lang.String>>
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_PARTNER_GETAREACODE)
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
    @ApiOperation(value="跳转至注册页面", httpMethod="GET")
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
    @ApiOperation(value="跳转至登录页面", httpMethod="GET")
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
            //加密方式：前端MD5(password)，服务器端MD5(password, salts)
            partnerUserDto.setPassword(FyUtils.getCertifiedSigned(password, salts));
            partnerUserDto.setCreateTime(new Date());

            Result<Integer> registerResult = partnerService.register(partnerDto, partnerUserDto);
            if(registerResult.isSuccess()){
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), "注册成功.", "", null);
            }else {
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), registerResult.getReturnMessage(), "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }

    /**
     * @description: 登录接口
     * @author: cnc
     * @date: 2019-01-20 18:31:29
     * @param loginRequest
     * @return: com.fy.fyentity.results.ResponseEntry
     */
    @ResponseBody
    @RequestMapping(value = BaseApi.API_PARTNER_LOGIN)
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
                return new ResponseEntry(RespCodeEnum.SUCCESS.code(), "登录成功.", "", null);
            } else if (partnerDto.getBasicStatus() == 2){
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "此用户已被注销，无法正常登陆.", "", null);
            } else{
                return new ResponseEntry(RespCodeEnum.BUSINESS_ERROR.code(), "用户状态异常，无法正常登陆.", "", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntry(RespCodeEnum.SERVER_RUNTIME_EXCEPTION.code(), "系统异常，请反馈建议报错.", "", null);
        }
    }
}