package code;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-07
 */
public class Thread01 {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println("运行主线程：" + mainThread.getName());//main
        System.out.println("是否为守护线程：" + mainThread.isDaemon()); //false
        //不能将正在运行的线程设置为守护线程 ，否则抛出 java.lang.IllegalThreadStateException
        //        mainThread.setDaemon(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name = Thread.currentThread().getName();
                System.out.println("当前线程：" + name);
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("runnable");
        //设置守护线程在start之前
        //如果当前线程中只有唯一一个线程是守护线程，jvm会自动退出
        thread.setDaemon(true);
        thread.start();
    }
}
