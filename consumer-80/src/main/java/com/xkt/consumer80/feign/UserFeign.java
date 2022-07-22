package com.xkt.consumer80.feign;

import com.xkt.consumer80.feign.fallback.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lzx
 * @date 2022/6/22.
 */
@Component
@FeignClient(value = "provider"
,fallbackFactory = UserFallbackService.class
)
public interface UserFeign {

    @GetMapping("/user/get")
    String getUser();

    @GetMapping("/user/get/timeout")
    String getUserTimeout();

    @GetMapping("/user/cookie")
    String cookie();
}
