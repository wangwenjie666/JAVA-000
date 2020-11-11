package code;

import dg.Fibo;

import java.util.concurrent.*;

/**
 * 方式8：
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn8 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> result = service.submit(() -> {
            //占用资源
            semaphore.acquire();
            int result1 = Fibo.sum();
            //释放资源
            semaphore.release();
            return result1;
        });

        try {
            System.out.println("主线程：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            service.shutdown();
        }


    }
}
