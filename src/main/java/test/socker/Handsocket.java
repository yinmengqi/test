package test.socker;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Handsocket{

    //当前服务器
    private ServerSocket socket;

    //当前客户端

    private Socket thissocket;

    //所有客户端
    private ArrayList<User> userlist;

    //当前用户
    private User user;

    public Handsocket(ServerSocket socket,Socket thissocket,ArrayList<User> userlist,User user){

        this.socket=socket;
        this.thissocket=thissocket;
        this.userlist=userlist;
        this.user=user;
    }

    //群发消息
    public void send_to_all(String s){

        for(int i=0;i<userlist.size();i++){

            if(userlist.get(i)!=user){

                try{

                    OutputStream out = userlist.get(i).socket.getOutputStream();

                    String nnum = ""+user.num;

                    out.write(nnum.getBytes());

                    out.write((" said:" + new String(s)).getBytes());

                    out.write(new String("\r\n").getBytes());
                }
                catch(SocketException e){
                    e.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}


