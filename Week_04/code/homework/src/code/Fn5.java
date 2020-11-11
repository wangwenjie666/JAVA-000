package code;

import dg.Fibo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 方式5：Callable + FutureTask + newCachedThreadPool（缓存线程池，线程数依赖操作系统或jvm）
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn5 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> result = service.submit(Fibo::sum);
        try {
            System.out.println("主线程获取：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
