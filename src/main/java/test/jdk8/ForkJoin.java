package test.jdk8;

import java.util.concurrent.RecursiveTask;

public class ForkJoin extends RecursiveTask<Long> {
    private long start;
    private long end;
    // 定义阙值
    private static final long THRESHOLD = 100;
    public ForkJoin() {
    }

    public ForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if(end-start<THRESHOLD){
            long sum = 0;
            for (long x=start;x<end;x++){
                sum += x;
            }
            return sum;
        }else{
            long middle = (end - start) / 2;
            ForkJoin left = new ForkJoin(start, middle);
            //拆分子任务，压入线程队列
            left.fork();
            ForkJoin right = new ForkJoin(middle + 1, end);
            right.fork();
            //合并并返回
            return left.join() + right.join();
        }
    }
}
