package com.xkt.consumer80.feign.fallback;

import com.xkt.consumer80.feign.UserFeign;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author lzx
 * @date 2022/7/4.
 */
@Component
public class UserFallbackService implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable cause) {
        return new UserFeign() {
            @Override
            public String getUser() {
                return "客户端降级了" + cause.getMessage();
            }

            @Override
            public String getUserTimeout() {
                System.out.println("========================================");
                return "客户端降级了 timeout" + cause.getMessage();
            }

            @Override
            public String cookie() {
                return null;
            }
        };
    }
}
