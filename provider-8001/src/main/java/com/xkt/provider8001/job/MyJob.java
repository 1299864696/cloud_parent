package com.xkt.provider8001.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lzx
 * @date 2022/7/18.
 */
@Component
public class MyJob {
    private static Logger logger = LoggerFactory.getLogger(MyJob.class);

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("myJob")
    public void myJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            System.out.println("========================>8001");
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
