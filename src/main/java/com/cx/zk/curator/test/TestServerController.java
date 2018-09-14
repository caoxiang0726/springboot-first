package com.cx.zk.curator.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


@Controller
@RequestMapping(value = "/test")
public class TestServerController {

    @RequestMapping("/server")
    public @ResponseBody String testServer() throws Exception {
        String path = "/zookeeper";
        TestingServer server = new TestingServer(2181, new File("D:\\zktest"));//??
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server.getConnectString())
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
        System.out.println("--"+client.getChildren().forPath(path));
        server.close();
        return "--"+client.getChildren().forPath(path);

    }

}
