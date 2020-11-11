package code;

import dg.Fibo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 方式4：Callable + FutureTask + newFixedThreadPool（指定数量线程池）
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn4 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(16);
        Future<Integer> result = service.submit(Fibo::sum);
        try {
            System.out.println("主线程获取数据：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
