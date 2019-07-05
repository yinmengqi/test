package test.jdk8;

public interface Interface {
    default  String getName(){
        return "zhangsan";
    }

    static String getName2(){
        return "zhangsan";
    }
}
