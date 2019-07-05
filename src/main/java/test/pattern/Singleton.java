package test.pattern;

/**
 * Created by 尹 on 2018/11/28.
 * 单例模式
 * 私有化构造方法，提供静态方法返回实例（分为饿汉和懒汉）
 * 饿汉--初始化私有实例，方法直接返回（初始占用内存）
 * 懒汉--初始化为null,调用方法通过判断去实例化或者返回（线程安全问题--(代码块现成安全或静态内部类)）
 */
public class Singleton {
    public static void main(String[] args){
        S s = S.getS();
        s.pinfo();
    }
}

class S{
    private S(){
    }
    //饿汉式
    private static S s = new S();
    public  static  S getS(){
        return s;
    }
    //懒汉式
    /*private static S s = null;
    public static S getS(){
        if(s==null){
            synchronized (S.class){
                if(s==null) {
                    s = new S();
                }
            }
        }
        return s;
    }*/
    //静态内部类 调用实例化
    /*private static class Si{
        private static final S s = new S();
    }
    public static S getS(){
        return Si.s;
    }*/
    public void pinfo(){
        System.out.println("S");
    }
}
