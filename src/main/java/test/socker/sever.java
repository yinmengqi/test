package test.socker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//创建服务器类,之后在主函数中用该类声明该类的对象
public class sever {

    //一个服务器
    private ServerSocket serverSocket;

    //private ArrayList<Socket> socketlist = new ArrayList<Socket>();

    //所有客户端
    private ArrayList<User> userlist = new ArrayList<User>();

    //在服务器的对话方法中完成在客户端写下信息,捕捉客户端信息,再次在客户端写下信息的功能
    public void talk() {


        try {

            //选择端口创建服务器
            serverSocket = new ServerSocket(6666);
            int count=0;
            //创建使用者列表
            ArrayList<String> userlist = new ArrayList<String>();

            //循环创建线程
            while(true){
                //连入的用户数+1
                count++;
                //创建连接
                Socket conn = serverSocket.accept();


                //创建该连接对应用户所有消息存储
                ArrayList<String> userinforlist = new ArrayList<String>();
                //创建用户
                User user = new User(conn,userinforlist,count);
                //添加用户
                this.userlist.add(user);
                //为用户创建线程
                TTread thread = new TTread(conn,serverSocket,user,this.userlist);
                //线程运行
                thread.start();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    //创建服务器,让服务器开始对话工作
    public static void main(String[] args){
        sever s = new sever();
        s.talk();
    }
}


