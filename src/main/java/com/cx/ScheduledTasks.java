package com.cx;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by caoxiang on 2018/3/18.
 */
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
//        System.out.println("当前时间："+System.currentTimeMillis());
    }
}
