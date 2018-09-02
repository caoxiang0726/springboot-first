package com.cx.zk;

import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by caoxiang on 2018/9/2.
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
