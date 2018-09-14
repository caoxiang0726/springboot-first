package com.cx.mysql;

import com.cx.mysql.po.SysUserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caoxiang on 2018/9/14.
 */
@Service
public class DemoService {

    @Autowired private DemoDao demoDao;

    public int addUser(SysUserPo po){
        int count = demoDao.addUser(po);
        return po.getId();//获取自增值
    }
}
