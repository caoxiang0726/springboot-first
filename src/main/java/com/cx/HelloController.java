package com.cx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by caoxiang on 2018/3/17.
 */
@RestController
public class HelloController {
    @Value("${my.name}")
    private String name;
   /* @Value("${my.family.mother}")
    private String mother;*/

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private HelloService helloService;


    @RequestMapping("/")
    public String index() {
        System.out.println(name);
        return "Greetings from Spring Boot!";


    }

    @RequestMapping("/jdbc")
    public String jdbcTest() {
        int update = jdbcTemplate.update("UPDATE  springboot_fast_account SET NAME='cx' ,money=99 WHERE id=9 ");
//        return jdbcTemplate.update("DELETE from TABLE account where id=6");
        return update + "";
    }

    @RequestMapping("/account/add")
    public String addaccount() {
        int count = helloService.add("cx", 999.9);


        return count + "";
    }

    @RequestMapping("/account/find")
    public String findaccount() {
        Account account = helloService.findAccount(1);

        return account.toString();
    }

    @RequestMapping("/transaction")
    public String transaction() {
        helloService.transaction();
        return "transaction";
    }

    @RequestMapping("/cache")
    public String cache() {

        for (int i = 0; i < 10; i++) {
            Account cache = helloService.cache();
            System.out.println("cache="+cache);
        }

        return "testCache";
    }


}
