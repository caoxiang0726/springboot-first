package com.cx.mysql;

import com.cx.mysql.po.SysUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by caoxiang on 2018/9/14.
 */
@Service
public class DemoService {

    @Autowired private DemoDao demoDao;

    public int addUser(SysUserPo po){
        return demoDao.addUser(po);
    }

    public int delById(SysUserPo po){
        return demoDao.delById(po);
    }

    public List<SysUserPo> getUser(SysUserPo po){
        return demoDao.getUser(po.getUserName(), po.getSex());

    }
}
