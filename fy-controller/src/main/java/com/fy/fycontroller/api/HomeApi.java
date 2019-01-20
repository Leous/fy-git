package com.fy.fycontroller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-21 0:12
 */
@Controller
@RequestMapping(value = BaseApi.API_HOME)
@Api(tags = "首页相关API")
public class HomeApi {

    @RequestMapping(value = BaseApi.API_HOME_INDEX, method = RequestMethod.GET)
    @ApiOperation(value="跳转至首页", httpMethod="GET")
    public String toIndex(HttpServletRequest request){
        return "/home/index";
    }
}
