package test.pattern;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 尹 on 2018/11/23.
 * 代理模式
 * 分为静态和动态代理两种（动态代理有jdk反射机制（Class.forName()）和cglib继承机制（动态创建子类）的动态代理）
 */
public class Proxy {
    public static void main(String[] args){
        //实例化静态代理类执行方法
        P2  p2 = new P2(new P1());
        p2.a();
        //jdk动态代理
        P p = (P)java.lang.reflect.Proxy.newProxyInstance(P.class.getClassLoader(),new Class[]{P.class},new P3(new P1()));
        p.a();
        //cglib动态代理
        cglib_P c = new cglib_P();
        P1 cg_p = (P1)c.getInstance(new P1());
        p.a();
    }
}

//通用接口
interface P{
    void a();
}

//委托类
class P1 implements P{

    @Override
    public void a() {
        System.out.println("p1");
    }
}

//静态代理类，定义接口类型变量，调用具体委托类的实现
class P2 implements P{
    private P p;
    public P2(){
        this.p = new P1();
    }
    public P2(P p){
        this.p = p;
    }
    @Override
    public void a() {
        p.a();
    }
}

//jdk动态代理
class P3 implements InvocationHandler{
    private Object object;
    public P3(Object o){
        this.object = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object,args);
        return result;
    }
}

//cglib 动态代理
class cglib_P implements MethodInterceptor{
    private Object object;
    public Object getInstance(Object target){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\");
        this.object = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.object.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o,objects);
        //调用invoke 会死循环调用
        //return methodProxy.invoke(o,objects);
    }
}