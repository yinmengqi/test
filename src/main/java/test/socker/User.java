package test.socker;

import java.net.Socket;
import java.util.ArrayList;

public class User {

    //输入的顺序编号
    public int num;

    //用户名称
    private String name;

    //用户密码
    private String password;

    //用户连接
    public Socket socket;

    //此人所有消息
    private ArrayList<String> userinforlist = new ArrayList<String>();

    public User(Socket socket, String name, String password){

        this.socket=socket;
        this.name=name;
        this.password=password;

    }

    public User(Socket socket,ArrayList<String> userinbforlist,int num){

        this.socket=socket;
        this.userinforlist=userinforlist;
        this.num=num;
    }

    public void save(String s){

        userinforlist.add(s);
    }

    public String getname(){
        return name;
    }

    public String getpassword(){
        return password;
    }
}


