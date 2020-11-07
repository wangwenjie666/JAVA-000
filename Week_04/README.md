# 并发编程

1.守护线程

- 不能将正在运行的线程设置为守护线程 ，否则抛出 java.lang.IllegalThreadStateException
- 设置守护线程在start之前
- 如果当前线程中只有唯一一个线程是守护线程，jvm会自动退出

- **(参考代码)[./code/lesson1]**















- **(参考代码)[https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303]**

