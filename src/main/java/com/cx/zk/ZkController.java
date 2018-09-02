package com.cx.zk;

import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * zkclient 的简化:
 *  1 异步创建同步化
 *  2 节点内容只支持byte[] -> 传入序列化实现
 *  3 屏蔽watcher参数 且一次注册一直生效，原生的是一次性的
 *  4 支持递归创建、删除节点
 *
 *  Curator：apache顶级项目
 */
@RestController
@RequestMapping(value = "/zk")
public class ZkController {
    @Value("${zkIp}")
    private String zkIp;
    @Value("${zkPort}")
    private String zkPort;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() throws IOException, KeeperException, InterruptedException {


        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("receive event：" + watchedEvent);
            }
        };
        ZooKeeper zooKeeper = new ZooKeeper(zkIp+":"+zkPort, 99999, watcher);

        zooKeeper.addAuthInfo("digest","foo:true".getBytes());//不同应用直接的权限问题
        // foo:true 类似 username:passwd
        //以后的操作就都要带上权限信息
        //所有的非叶子节点必须是持久节点
        String path = zooKeeper.create("/zk-book-java", "456".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        return path;

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() throws IOException, KeeperException, InterruptedException {
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("receive event：" + watchedEvent);
            }
        };
        ZooKeeper zooKeeper = new ZooKeeper(zkIp+":"+zkPort, 99999, watcher);
        byte[] data = zooKeeper.getData("/zk-book", watcher, null);
        String value = new String(data);
        return "zkget:" + value;
    }

}
