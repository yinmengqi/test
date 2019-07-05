package test.pattern;

/**
 * Created by 尹 on 2018/12/11.
 * 适配器模式(类适配器，对象适配器，接口适配器)
 */
public class Adapter {
    public static void main(String[] args){
        //类适配器
        Ad_target t = new Ad_s();
        Ad_source s = new Ad_s();
        t.gettname();
        s.getname();
        //对象适配器
        Ad_ctarget ad_ctarget = new Ad_s2(new Ad_source());
        ad_ctarget.getname();
    }
}

//源类
class Ad_source{
    public String getname(){
        System.out.println("source");
        return "source";
    }
}

//目标接口
interface Ad_target{
    public void gettname();
}

//继承源类实现目标接口(适配源类到目标接口即为类适配器)
class Ad_s extends Ad_source implements Ad_target{
    @Override
    public void gettname() {
        System.out.println("target-->"+super.getname());
    }
}

//目标类
class Ad_ctarget{
    public String getname(){
        System.out.println("target");
        return "target";
    }
}

//对象适配器(继承目标类，传入源类对象即为对象适配器)
class Ad_s2 extends Ad_ctarget{
    private Ad_source ad_source;
    public Ad_s2(Ad_source ad_source){
        this.ad_source = ad_source;
    }
    public String getname(){
        System.out.println(ad_source.getname());
        return ad_source.getname();
    }
}