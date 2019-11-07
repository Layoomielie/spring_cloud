package com.example.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author：张鸿建
 * @time：2019/11/6 17:36
 * @desc：
 **/
public class PersonTwo {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        //2.指定一个IP
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        int port = 5051;
        //3.准备一个小容器
        byte[] sendBuf;
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("你要发什么东西：");
            String s = scanner.nextLine();
            //4.加入要放的数据
            sendBuf = s.getBytes();
            //5.数据打包
            DatagramPacket packet = new DatagramPacket(sendBuf,sendBuf.length,addr,port);
            //6.发送包
            socket.send(packet);
            if (s.equals("exit")){
                break;
            }
        }
    }
}
