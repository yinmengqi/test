package test.pattern;


/**
 * Created by 尹 on 2018/11/30.
 * 工厂模式（简单工厂，工厂方法，抽象工厂）
 */
public class Factory {
    public static void main(String[] args){
        F f1 = Ff.getf(F1.class);
        f1.ff();
        F f2 = Ff.getf(F2.class);
        f2.ff();
        F f3 = Ff.getf(F3.class);
        f3.ff();
    }
}

class Ff{
    public static F getf(Class c){
        F f = null;
        try {
            f = (F) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }
}

interface F{
    public void ff();
}

interface F_F{
    public F create();
}

class F1 implements F{
    @Override
    public void ff() {
        System.out.println("F1");
    }
}

class F2 implements F{
    @Override
    public void ff() {
        System.out.println("F2");
    }
}

class F3 implements F{
    @Override
    public void ff() {
        System.out.println("F3");
    }
}
