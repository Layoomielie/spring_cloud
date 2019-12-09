package com.example.zookeeper;

import com.example.util.ZookeeperUtils;

/**
 * @author：张鸿建
 * @time：2019/12/9 14:50
 * @desc：  // 这里封装了zookeeper大部分操作
 **/
public class CuratorThread1 {



    public static void main(String[] args) throws Exception {
        String node_path = "/curator_node";
        ZookeeperUtils.startCuratorFramework();
        /*curatorThread1.getNodePathInfo(node_path);
        curatorThread1.updateNodeDataInfo(node_path,"123456");
        curatorThread1.getNodePathInfo(node_path);*/
        ZookeeperUtils.addNodePathListen(node_path);
        ZookeeperUtils.addChildNodePathListen(node_path);
        while (true){

        }
    }
}
