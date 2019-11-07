package com.example.udp;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author：张鸿建
 * @time：2019/11/6 17:32
 * @desc：
 **/
public class PersonOne {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5051);
        byte[] bytes = new byte[100];
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
        while (true){
            socket.receive(packet);
            String receStr = new String(packet.getData(),0,packet.getLength());
            System.out.println("我收到了："+receStr);
            if (receStr.equals("exit")){
                break;
            }
        }
        socket.close();
    }

}
