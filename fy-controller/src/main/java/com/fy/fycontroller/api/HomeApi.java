package com.fy.fycontroller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 首页相关API
 * @author: cnc
 * @date: 2019-01-21 0:12
 */
@Controller
@RequestMapping(value = BaseApi.API_HOME)
@Api(value = BaseApi.API_HOME, tags = "Home Api", description = "首页相关")
@Slf4j
public class HomeApi {

    @RequestMapping(value = BaseApi.API_HOME_INDEX, method = RequestMethod.GET)
    @ApiOperation(value="跳转至首页", httpMethod="GET")
    public String toIndex(HttpServletRequest request){
        return "/home/index";
    }
}
