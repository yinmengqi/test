package test.pattern;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 尹 on 2018/11/29.
 * 多例模式
 * 例：连接池
 */
public class Multition {
    public static void main(String[] args){
        for (int i=0;i<10;i++){
            System.out.println(M.getm());
        }
    }
}

class M{
    private int id;
    private String url;
//    public String toString() {
//        return "id:"+this.id+"   url:"+this.url;
//    }
    private M(int id,String url){
        this.id=id;
        this.url=url;
    }
    private static  int max_m = 2;//最大实例数
    private static ArrayList<M> ms = new ArrayList(max_m);//实例集合
    private static int cu_m = 0;//当前实例
    static {
        for(int i=0;i<max_m;i++){
            ms.add(new M(i,"url"));
        }
    }
    private M(){}
    public static M getm(){
        Random random = new Random();
        cu_m = random.nextInt(max_m);
        return (M)ms.get(cu_m);
    }
}
