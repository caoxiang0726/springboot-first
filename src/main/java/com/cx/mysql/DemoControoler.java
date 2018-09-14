package com.cx.mysql;

/**
 * mysql建表
 * auto_increment
 * 等
 */

import com.cx.mysql.po.SysUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * CREATE TABLE sys_user_test(
 id int NOT NULL AUTO_INCREMENT,
 user_name VARCHAR(20) NOT NULL ,
 birthday DATE,
 sex SET('M','F','N'),
 PRIMARY KEY (id)
 )ENGINE=innodb DEFAULT CHARSET =UTF8;
 */
@RestController
@RequestMapping(value = "/mysql")
public class DemoControoler {
    @Autowired private DemoService demoService;

    @RequestMapping(value = "/add")
    public String addUser(){
        SysUserPo po = new SysUserPo();
        po.setUserName("cx");
        po.setBirthday(new Date());
        po.setSex("M");
        int count = demoService.addUser(po);
        return po.getId()+"";//获取自增值
    }



}
