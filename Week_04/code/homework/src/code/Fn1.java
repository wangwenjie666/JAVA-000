package code;

import dg.Fibo;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式一: CountDownLatch + Callable + FutureTask
 *
 * @author wangwenjie
 * @date 2020-11-09
 */
public class Fn1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread1 thread1 = new Thread1(countDownLatch);
        FutureTask<Integer> task = new FutureTask<>(thread1);

        new Thread(task).start();
        //主线程等待
        countDownLatch.await();

        Integer result = task.get();
        System.out.println("result = " + result);

        System.out.println("主线程结束...");
    }

    static class Thread1 implements Callable<Integer> {
        private CountDownLatch latch;

        public Thread1(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public Integer call() throws Exception {

            int result = Fibo.sum();
            latch.countDown();
            System.out.println("子线程计算结束...");
            Thread.sleep(2000);
            return result;
        }
    }
}
