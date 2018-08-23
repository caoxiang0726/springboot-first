package com.cx;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by caoxiang on 2018/3/18.
 */
@Service
public class HelloService {
    @Resource
    private  HelloMapper helloMapper;

    public int add(String name, double money) {
        return helloMapper.add(name, money);
    }

    public Account findAccount(int id) {
        return helloMapper.findAccount(id);
    }

    @Transactional
    public void transaction(){
        helloMapper.add("cx1", 20);
        System.out.println("cx1 ok");
        int i = 1/0;
        helloMapper.add("cx2", 30);
    }

    /**
     * 缓存
     * cache=com.cx.Account@2978d8ec
     cache=com.cx.Account@2978d8ec
     cache=com.cx.Account@2978d8ec
         是同一个对象。应用场景是什么呢？
     */

    @Cacheable("myaccount")
    public Account cache(){//要有一个具体的返回值。
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return helloMapper.findAccount(1);

    }

}
