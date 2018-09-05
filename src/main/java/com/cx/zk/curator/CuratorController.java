package com.cx.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 同步接口
 * 其实还要异步接口
 */
public class CuratorController {

    public static void createSessionSample() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);//重试策略
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("192.168.179.128:2181", 5000, 3000, retryPolicy);
        client.start();//创建session
        try {
            Thread.sleep(Integer.MAX_VALUE);//当前需要sleep的时间?
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static CuratorFramework createSessionFluent() throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.179.128:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("base")//实现业务之间的隔离  以前需要的 username:passwd中的密码不见了??
                .build();
        client.start();
        return client;
//        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void createNode() throws Exception {
        CuratorFramework client = createSessionFluent();
        /**
         * 持久节点
         */
        client.create().forPath("zk-book");//空节点

        client.create().forPath("zk-book", "123".getBytes());//带有value,  zkclinet不同，curator和原生保持一致
        /**
         * 临时节点
         */

        client.create().withMode(CreateMode.EPHEMERAL).forPath("zk-book", "123".getBytes());
        //自动递归创建父节点 非叶子节点必须是持久的
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/a/bzk-book", "123".getBytes());

    }

    public static void deleteNode() throws Exception {
        CuratorFramework client = createSessionFluent();
        //删除叶子节点
        client.delete().forPath("/zk-book");

        client.delete().deletingChildrenIfNeeded().forPath("/zk-book");

        //删除指定版本
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/a/b");
        client.delete().withVersion(stat.getVersion()).forPath("/a/b");
        //保证强制删除，即使网络异常，即使正在选举中。 使用重试机制
        client.delete().guaranteed().forPath("/a/b");
    }

    /**
     * 读取节点信息
     */
    public static void  getData() throws Exception {
        CuratorFramework client = createSessionFluent();
        byte[] bytes = client.getData().forPath("/a/b");

        //除了获得节点值，传入旧的stat，获取最新节点信息
        Stat stat = new Stat();
        byte[] bytes1 = client.getData().storingStatIn(stat).forPath("/a/b");



    }

    /**
     * 更新信息
     */
    public static void setData() throws Exception {

        CuratorFramework client = createSessionFluent();
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/a");
        int version = client.setData().withVersion(stat.getVersion()).forPath("/a").getVersion();

        client.setData().withVersion(version).forPath("/a");


    }





    public static void main(String[] args) {

    }

}
