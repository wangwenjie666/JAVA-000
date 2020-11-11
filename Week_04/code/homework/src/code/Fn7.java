package code;

import dg.Fibo;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式7：CyclicBarrier + FutureTask
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn7 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(1);
        FutureTask<Integer> task = new FutureTask<>(new T1(barrier));
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println("currentThread: " + thread.getName());
            System.out.println("主线程获取数据：" + task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class T1 implements Callable<Integer> {

        private CyclicBarrier barrier;

        public T1(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public Integer call() throws Exception {
            synchronized (this) {
                int result = Fibo.sum();
                barrier.await();
                return result;
            }
        }
    }
}
