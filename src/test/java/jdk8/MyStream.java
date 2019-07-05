package jdk8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.jdk8.ForkJoin;
import test.jdk8.Interface;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={MyStream.class})// 指定启动类
public class MyStream {


    @Test
    public void lamada(){
        //匿名内部类
        Comparator<Integer> cpt = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        TreeSet<Integer> set = new TreeSet<>(cpt);

        System.out.println("=========================");
        //使用lambda表达式
        Comparator<Integer> cpt2 = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
    }

    @Test
    public void method(){
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);
        // 方法引用-对象::实例方法
        Consumer<Integer> con2 = System.out::println;
        con2.accept(200);
        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        Integer result = biFun2.apply(100, 200);
        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        Boolean result2 = fun2.apply("hello", "world");
        System.out.println(result2);
    }
    @Test
    public void stream(){
        //重点理解函数式接口

        // 1，校验通过Collection 系列集合提供的stream()或者paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();//普通流
        Stream<String> stream2 = list.parallelStream();//并行流
        //Stream的中间操作
        stream.filter(a->a!="x")
                .limit(10)
                .skip(10)
                // 需要流中的元素重写hashCode和equals方法
                .distinct().forEach(System.out::println);
        //生成新的流 通过map映射
        stream.map(a->a+"test").forEach(System.out::println);
        //排序
        stream.sorted((x1,x2)->x1.compareTo(x2)).forEach(System.out::println);
        //Stream的终止操作
        /*      查找和匹配
        *          allMatch-检查是否匹配所有元素
        *          anyMatch-检查是否至少匹配一个元素
        *          noneMatch-检查是否没有匹配所有元素
        *          findFirst-返回第一个元素
        *          findAny-返回当前流中的任意元素
        *          count-返回流中元素的总个数
        *          max-返回流中最大值
        *          min-返回流中最小值
        */
        stream.allMatch(a->a.equals("test"));
        stream.anyMatch(a->a.equals("test"));
        stream.noneMatch(a->a.equals("test"));
        stream.findFirst();
        stream2.findAny();//适合并行流
        stream.count();
        stream.max((x1,x2)->x1.compareTo(x2));
        stream.min((x1,x2)->x1.compareTo(x2));
        //聚合流 reduce  collect 比较强大
    }

    @Test
    public void data(){
        // 从默认时区的系统时钟获取当前的日期时间。不用考虑时区差
        LocalDateTime date = LocalDateTime.now();
        //2018-07-15T14:22:39.759
        System.out.println(date);
    }

    @Test
    public void test() {
        //开始时间
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, 1000000).parallel()
                .reduce(0, Long :: sum);
        //结束时间
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start, end).getSeconds());
    }
    @Test
    public void test2() {
        //开始时间
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoin(0L,100);
        // 没有返回值     pool.execute();
        long sum = pool.invoke(task);
        //结束时间
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start, end).getSeconds());
    }
    @Test
    public void test3(){
        Interface.getName2();
    }
}
