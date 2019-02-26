package com.fy.fyserver.impls;

import com.fy.fycommon.utils.FyUtils;
import com.fy.fyentity.dtos.PartnerDto;
import com.fy.fyentity.dtos.PartnerUserDto;
import com.fy.fyentity.results.Result;
import com.fy.fyrepo.interfaces.PartnerDao;
import com.fy.fyrepo.interfaces.PartnerUserDao;
import com.fy.fyserver.interfaces.PartnerService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @description: partnerService实现类
 * @author: cnc
 * @date: 2019-01-07 1:32
 */
@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerUserDao partnerUserDao;

    @Autowired
    private PartnerDao partnerDao;

    /**
     * @description: 注册新用户
     * @author: cnc
     * @date: 2019-01-07 17:29:08
     * @param partnerDto
     * @param partnerUserDto
     * @return: com.fy.fyentity.results.Result<java.lang.Integer>
     */
    @Override
    @Transactional
    public Result<Integer> register(PartnerDto partnerDto, PartnerUserDto partnerUserDto) {
        Result<Integer> result = new Result<>(false, "", "");
        try{
            //验证账户是否已存在
            Map<String, String> verifyMap = verifyAccount(partnerDto, partnerUserDto);
            if("false".equals(verifyMap.get("verifyStatus"))){
                //已存在相同账户
                result.setReturnMessage(verifyMap.get("verifyMessage").toString());
                return result;
            }

            String partnerId = FyUtils.getRandomNum(8);
            //partnerId：默认6位随机数字
            partnerDto.setPartnerId(partnerId);
            partnerDto.setCreateTime(new Date());

            //盐：6位随机字母
            String salts = FyUtils.genRandomNum(6);
            partnerUserDto.setSalts(salts);
            partnerUserDto.setPartnerId(partnerId);
            partnerUserDto.setRoleId(2);
            //加密方式：前端MD5(password)，服务器端MD5(password, salts)
            partnerUserDto.setPassword(FyUtils.getCertifiedSigned(partnerUserDto.getPassword(), salts));
            partnerUserDto.setCreateTime(new Date());

            //插入用户操作员表，并获取puid
            partnerUserDao.insertPartnerUser(partnerUserDto);

            //插入用户表
            //获取puid
            partnerDto.setPuid(partnerUserDto.getId());
            partnerDao.insertPartner(partnerDto);

            result.setSuccess(true);
            result.setReturnMessage("注册成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Transactional rollback runtimeException");
        }
    }

    @Override
    public Result<PartnerDto> getPartner(PartnerDto partnerDto) {
        Result<PartnerDto> result = new Result<>(false, "查询失败", "");
        try {
            PartnerDto findPartner = partnerDao.getPartner(partnerDto);
            if(findPartner != null){
                result.setSuccess(true);
                result.setReturnMessage("查询成功");
                result.setEntry(findPartner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description: 验证注册账户公共方法
     * @author: cnc
     * @date: 2019-01-10 23:10:04
     * @param partnerDto
     * @param partnerUserDto
     * @return: boolean
     */
    private Map<String, String> verifyAccount(PartnerDto partnerDto, PartnerUserDto partnerUserDto) {
        Map<String, String> verifyMap = Maps.newHashMap();
        String verifyStatus = "true";
        verifyMap.put("verifyStatus", verifyStatus);
        try{
            PartnerUserDto resultUser = null;
            PartnerDto resultPartner = null;
            switch (partnerDto.getType()) {
                case 0:
                    //后台注册的最好也确认一下是否有重复的账号
                    resultUser = partnerUserDao.getPartnerUser(partnerUserDto);
                    if(resultUser != null){
                        verifyStatus = "false";
                        verifyMap.put("verifyStatus", verifyStatus);
                        verifyMap.put("verifyMessage", "用户名已存在");
                    }
                    break;
                case 1:
                    //手机号注册必须要有手机号+验证码
                    resultPartner = partnerDao.getPartner(partnerDto);
                    if(resultPartner != null){
                        verifyStatus = "false";
                        verifyMap.put("verifyStatus", verifyStatus);
                        verifyMap.put("verifyMessage", "手机号已存在");
                    }
                    break;
                case 2:
                    //账号密码注册必须输入账号+密码+确认密码
                    resultUser = partnerUserDao.getPartnerUser(partnerUserDto);
                    if(resultUser != null){
                        verifyStatus = "false";
                        verifyMap.put("verifyStatus", verifyStatus);
                        verifyMap.put("verifyMessage", "用户名已存在");
                    }
                    break;
                //case 3:
                //后续加入第三方注册登录
                //      break;
                default:
                    //没有选择注册方式无法注册，并提示用户
                    verifyStatus = "false";
                    verifyMap.put("verifyStatus", verifyStatus);
                    verifyMap.put("verifyMessage", "请选择注册方式");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifyMap;
    }
}
