package com.xkt.consumer80.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xkt.consumer80.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/consumer/user")
//@DefaultProperties(defaultFallback = "paymentTimeOutFallbackMethod")
public class UserController {

    @Autowired
    UserFeign userFeign;

    @GetMapping("/get")
    public String getUser() {
        String user = userFeign.getUser();
        System.err.println("=====>user:" + user);
        return user;
    }

    @GetMapping("/get/timeout")
    //@HystrixCommand(/*fallbackMethod = "paymentTimeOutFallbackMethod",*/ commandProperties = {
            //3秒钟以内就是正常的业务逻辑 这里配置了以这里的为准(配置文件也可配置全局的)
            //@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    //})
    public String getUserTimeout() {
        String user = userFeign.getUserTimeout();
        System.err.println("=====>user:" + user);
        return user;
    }

    /**
     * 兜底方法
     */
    public String paymentTimeOutFallbackMethod() {
        System.out.println("80  降级了");
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }


    /**
     * curl http://127.0.0.1/consumer/user/cookie --cookie "username=zx"
     */
    @GetMapping("/cookie")
    public String cookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String user = userFeign.cookie();
        return user;
    }
}
