package code;

import dg.Fibo;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 方式一
 *
 * @author wangwenjie
 * @date 2020-11-09
 */
public class Fn1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

    }

    static class Thread1 implements Callable {
        private CountDownLatch latch;

        public Thread1(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public Object call() throws Exception {
            int result = Fibo.sum();
            latch.countDown();
            return result;
        }
    }
}
