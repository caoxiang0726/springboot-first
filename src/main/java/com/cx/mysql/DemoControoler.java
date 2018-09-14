package com.cx.mysql;

/**
 * mysql建表
 * auto_increment
 * 等
 */

import com.cx.mysql.po.SysUserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/delbyid/{id}")
    public int delByID(@PathVariable("id") int userid){
        SysUserPo po = new SysUserPo();
        po.setId(userid);
        int count = demoService.delById(po);
        return count;
    }

    @GetMapping(value = "get/{name}/{sex}")
    public List<SysUserPo> getUser(@PathVariable(value = "name",required = false) String name,
                                   @PathVariable(value = "sex",required = false) String sex){
        SysUserPo po = new SysUserPo();
        po.setUserName(name);
//        po.setSex(sex);
        List<SysUserPo> users = demoService.getUser(po);
        return users;

    }



}
