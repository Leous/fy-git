package com.fy.fycontroller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: cnc
 * @date: 2019-01-08 0:36
 */
@RestController
@RequestMapping(value = "trys")
public class TestApi {
    @RequestMapping(value = "try1")
    public String trys(HttpServletRequest request) {
        return "hello world!";
    }
}
