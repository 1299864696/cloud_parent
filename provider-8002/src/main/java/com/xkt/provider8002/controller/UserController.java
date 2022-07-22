package com.xkt.provider8002.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author lzx
 * @date 2022/6/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/get")
    public String getUser() {
        System.err.println("=====>PORT:" + port);
        return port;
    }

    @GetMapping("/cookie")
    public Cookie[] cookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        return cookies;
    }
}
