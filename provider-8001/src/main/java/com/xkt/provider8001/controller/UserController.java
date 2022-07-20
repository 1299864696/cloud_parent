package com.xkt.provider8001.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lzx
 * @date 2022/6/22.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${server.port}")
    private String port;

    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {
            //3秒钟以内就是正常的业务逻辑
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    @GetMapping("/get")
    public String getUser() throws InterruptedException {
        System.err.println("=====>PORT:" + port);
        //int i = 1/0;
        //Thread.sleep(3100L);
        return port;
    }

    @GetMapping("/get/timeout")
    public String getUserTimeout() throws InterruptedException {
        System.err.println("=====>PORT:" + port + " timeout");
        Thread.sleep(5000L);
        System.err.println("<===== 睡完执行结束 PORT:" + port + " timeout");
        return port + " timeout";
    }

    public String fallBack() {
        System.err.println("=====>PORT:" + port + " 降级了");
        return port + "降级了";
    }
}
