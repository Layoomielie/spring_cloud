package com.example.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @author：张鸿建
 * @time：2019/12/9 16:23
 * @desc：
 **/
public class ZookeeperUtils {
    private final static String zookeeperConnectionStr = "10.100.23.85:2181";

    private static CuratorFramework curatorFramework;

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    private static Stat stat = new Stat();

    public static void startCuratorFramework() {
        curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionStr, retryPolicy);
        curatorFramework.start();
    }

    public static CuratorFramework getClient() {
        return curatorFramework = CuratorFrameworkFactory.newClient(zookeeperConnectionStr, retryPolicy);
    }

    // 创建节点
    public static void createNodePath(String node_path, String data) {
        try {
            if (data != null) {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(node_path, "123".getBytes());
            } else {
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(node_path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 删除节点 并删除子节点
    public static void createNodePath(String NODE_PATH) {
        try {
            curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().withVersion(-1).forPath(NODE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取节点信息
    public static void getNodePathInfo(String NODE_PATH) {
        try {
            byte[] result = curatorFramework.getData().storingStatIn(stat).forPath(NODE_PATH);
            System.out.println(new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改节点内容
    public static void updateNodeDataInfo(String NODE_PATH, String newData) {
        try {
            if (newData != null) {
                curatorFramework.setData().forPath(NODE_PATH, newData.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加节点监听
    public static void addNodePathListen(String NODE_PATH) {
        /*设置节点事件监听*/
        final NodeCache nodeCache = new NodeCache(curatorFramework, NODE_PATH);
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                byte[] result = nodeCache.getCurrentData().getData();
                System.out.println("事件监听result: " + new String(result));
            }
        });
    }

    public static void addChildNodePathListen(String NODE_PATH) {
        final PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, NODE_PATH, true);
        try {
            childrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                switch (type) {
                    case CHILD_ADDED:
                        System.out.println("子节点添加");
                        break;
                    case CHILD_UPDATED:
                        System.out.println("子节点修改");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("子节点移除");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static Stat isNodePathExsis(String NODE_PATH) {
        try {
            return curatorFramework.checkExists().forPath(NODE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeClient() {
        curatorFramework.close();
    }
}
