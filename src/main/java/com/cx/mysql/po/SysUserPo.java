package com.cx.mysql.po;

import java.util.Date;

/**
 * CREATE TABLE sys_user_test(
 * id int NOT NULL AUTO_INCREMENT,
 * user_name VARCHAR(20) NOT NULL ,
 * birthday DATE,
 * sex SET('M','F','N'),
 * PRIMARY KEY (id)
 * )ENGINE=innodb DEFAULT CHARSET =UTF8;
 */
public class SysUserPo {

    private Integer id;
    private String userName;
    private Date birthday;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
