package test.pattern;

/**
 * Created by 尹 on 2018/12/7.
 * 门面模式(门面类提供方法，封装一系列的流程实现，给外部更简单的使用)
 */
public class Facade {
    public static void main(String[] args){
        //普通调用，实例化接口实现类，逐个调用实现方法
        impl_facade impl_facade = new impl_facade();
        impl_facade.a("a");impl_facade.b("b");impl_facade.c("c");
        //门面调用，实例化门面类，调用门面提供的方法，传入参数，门面内部调用实现类
        facade_facade ff = new facade_facade();
        ff.f("a","b","c");
    }
}

//门面类
class facade_facade{
    private impl_facade impl_facade = new impl_facade();
    public void f(String a,String b,String c){
        impl_facade.a("a");impl_facade.b("b");impl_facade.c("c");
    }
}

interface i_facade{
    public void a(String a);
    public void b(String a);
    public void c(String a);
}

class impl_facade implements i_facade{

    @Override
    public void a(String a) {
        System.out.println(a);
    }

    @Override
    public void b(String a) {
        System.out.println(a);
    }

    @Override
    public void c(String a) {
        System.out.println(a);
    }
}
