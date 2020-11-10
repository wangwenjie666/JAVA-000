package code;

import dg.Fibo;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式二：Callable + FutureTask
 *
 * @author wangwenjie
 * @date 2020-11-09
 */
public class Fn2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread1 thread1 = new Thread1();
        FutureTask<Integer> task = new FutureTask<>(thread1);

        new Thread(task).start();

        Integer result = task.get();
        System.out.println("result = " + result);

        System.out.println("主线程结束...");
    }

    static class Thread1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            int result = Fibo.sum();
            System.out.println("子线程计算结束...");
            return result;
        }
    }
}
