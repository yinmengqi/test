package test.pattern;

/**
 * Created by 尹 on 2018/11/22.
 * 策略模式
 */
public class Strategy {
    public static void main(String[] args){
        //策略调用者，创建策略上下文并声明要使用的策略
        context _c = new context(new p2());
        _c.a();
    }
}

/**
 * 策略上下文
 * 一般来说包含策略接口的引用，构造函数赋值接口实现来实现不同的策略调用
 * 或者策略上下文根据调用参数，实现不同的策略
 */
class context{
    //策略接口的引用
    p _p;
    public context(p _p){
        this._p = _p;
    }
    public void a(){
        _p.a();
    }
}

//策略接口
interface p{
    void a();
}

//策略实现1
class p1 implements p{
    @Override
    public void a() {
        System.out.println("p1");
    }
}

//策略实现2
class p2 implements p{
    @Override
    public void a() {
        System.out.println("p2");
    }
}