package test.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 尹 on 2019/2/26.
 * 责任链模式
 * 责任链接口实现类包含 具体执行类集合
 * 运行方法获取责任链内的执行类运行并传当前责任链给执行类
 * 执行类运行完继续执行责任链，开始下一个执行类
 * 直到结束
 */
public class Chain {
    public static void main(String[] args){
        List<chain_xx> xxs = new ArrayList<>();
        xxs.add(new chain_xx1());
        xxs.add(new chain_xx4());
        xxs.add(new chain_xx3());
        xxs.add(new chain_xx2());
        new a_chain_i(xxs).excute();
    }
    public interface a_chain{
        void excute();
    }
    public static class a_chain_i implements a_chain{
        private final List<chain_xx> xxs;
        private int index;
        public a_chain_i(List<chain_xx> xxs) {
            this.xxs = xxs;
        }

        @Override
        public void excute() {
            if (this.index < xxs.size()) {
                chain_xx plugin = xxs.get(this.index++);
                plugin.doexcute(this);
            }else{
                System.out.println("执行完毕");
            }
        }
    }
    public interface chain_xx{
        public void doexcute(a_chain a);
    }
    public static class chain_xx1 implements chain_xx{
        @Override
        public void doexcute(a_chain a) {
            System.out.println("xx1");
            a.excute();
        }
    }
    public static class chain_xx2 implements chain_xx{
        @Override
        public void doexcute(a_chain a) {
            System.out.println("xx2");
            a.excute();
        }
    }
    public static class chain_xx3 implements chain_xx{
        @Override
        public void doexcute(a_chain a) {
            System.out.println("xx3");
            a.excute();
        }
    }
    public static class chain_xx4 implements chain_xx{
        @Override
        public void doexcute(a_chain a) {
            System.out.println("xx4");
            a.excute();
        }
    }
}
