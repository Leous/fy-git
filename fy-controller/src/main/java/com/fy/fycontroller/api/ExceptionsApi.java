package com.fy.fycontroller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *@description: 出现一场展示页面，比如404
 *@author: cnc
 *@create: 2019/1/31 16:40
 */
@Controller
@RequestMapping(value = BaseApi.API_EXCEPTIONS)
@Api(value = BaseApi.API_EXCEPTIONS, tags = "Exceptions Api", description = "异常页面接口")
@Slf4j
public class ExceptionsApi extends BasicApi {

    @RequestMapping(value = BaseApi.API_EXCEPTIONS_404)
    @ApiOperation(value="展示404页面", httpMethod="GET")
    public String show404(HttpServletRequest request){
        return "/exceptions/404";
    }
}