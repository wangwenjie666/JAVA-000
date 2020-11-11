package code;

import dg.Fibo;

import java.util.concurrent.*;

/**
 * 方式三：SingleThreadExecutor(单线程池) + Callable + FutureTask
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn3 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> result = service.submit(Fibo::sum);

        try {
            System.out.println("主线程获取数据：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            //关闭线程池
            service.shutdown();
        }

    }

}
