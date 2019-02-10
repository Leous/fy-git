package com.fy.fycontroller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 服务器发生异常显示异常页面
 * @author: cnc
 * @create: 2019/2/2 16:46
 */
@Controller
@RequestMapping(value = BaseApi.API_EXCEPTION)
@Api(value = BaseApi.API_EXCEPTION, tags = "Exception Api", description = "异常页面跳转接口")
@Slf4j
public class ExceptionApi {

    /**
     * @description: 跳转至404页面
     * @author: cnc
     * @date: 2019-01-14 23:53:56
     * @param request
     * @return: java.lang.String
     */
    @RequestMapping(value = BaseApi.API_EXCEPTION_404, method = RequestMethod.GET)
    @ApiOperation(value="跳转至404页面", httpMethod="GET")
    public String exception_404(HttpServletRequest request) {
        return "/exceptions/404";
    }

}