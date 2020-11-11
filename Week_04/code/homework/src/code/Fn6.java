package code;

import dg.Fibo;

import java.util.concurrent.*;

/**
 * 方式6：Callable + Future +
 *
 * @author wangwenjie
 * @date 2020-11-11
 */
public class Fn6 {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(16);
        //10ms后执行
        ScheduledFuture<Integer> schedule = service.schedule(Fibo::sum, 10, TimeUnit.MILLISECONDS);
        try {
            System.out.println("主线程获取：" + schedule.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }
}
