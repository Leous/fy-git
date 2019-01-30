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
import java.util.List;

/**
 * @description: 用户相关接口汇总
 * @author: cnc
 * @date: 2019-01-05 21:21
 */
@Controller
@RequestMapping(value = BaseApi.API_PARTNER)
@Api(value = BaseApi.API_PARTNER, tags = "Partner Api", description = "用户相关")
@Slf4j
public class PartnerApi extends BasicApi {
}