package test.socker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TTread extends Thread {

    //服务器
    private ServerSocket serversocket;

    //客户端
    private Socket conn;

    //所有客户端信息
    private ArrayList<User> userlist = new  ArrayList<User>();
    //当前客户端
    private User user;

    public TTread(Socket conn,ServerSocket serversocket,User user,ArrayList<User> userlist) {

        //当前客户端
        this.conn=conn;
        //服务器
        this.serversocket=serversocket;
        //当前客户端所有信息
        this.user=user;
        //所有用户端
        this.userlist=userlist;
    }

    public void run() {

        try {

            InputStream filein = conn.getInputStream();
            OutputStream out = conn.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(filein));

            out.write("hello word！\r\n".getBytes());
            out.flush();

            while (true) {

                Handsocket handle= new Handsocket(serversocket, conn, userlist,user);

                String guestinformation = reader.readLine();

                String type,name,password;

                //~前为类型
                //type=guestinformation.split("~")[0];
                //System.out.println(type);
                //~和&间为名字
                //name=guestinformation.split("~")[1].split("&")[0];
                //&后面为密码
                //password=guestinformation.split("~")[1].split("&")[1];


                //waitcheck wc = new waitcheck(conn,name,password);//创建待检查成员
                //wc.check(type);

                out.write(("you said:" + new String(guestinformation)).getBytes());
                out.write(new String("\r\n").getBytes());


                handle.send_to_all(guestinformation);//发出群聊消息

                //用户信息
                user.save(guestinformation);//把消息存入该用户输入的所有信息中

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

