package com.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by caoxiang on 2018/3/18.
 */
@SpringBootApplication
/*@EnableAutoConfiguration
@ComponentScan*/
@EnableCaching
@EnableScheduling
@EnableKafka
public class SpringbootApp {
    public static void main(String[] args) throws Exception {
        System.out.println("boot jar");
        SpringApplication.run(SpringbootApp.class, args);
    }


}
