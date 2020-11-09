# 并发编程

## 1.守护线程

- 不能将正在运行的线程设置为守护线程 ，否则抛出 java.lang.IllegalThreadStateException
- 设置守护线程在start之前
- 如果当前线程中只有唯一一个线程是守护线程，jvm会自动退出

## 2.sleep wait

- sleep：释放cpu
- wait：释放锁

## 3.线程安全

### 1.并发性质

- 原子性：原子操作，对基本类型变量的读取和赋值是原子操作，这些操作不能被打断的，要么执行，要么不执行



参考代码：

- **[守护线程 lesson1](./code/lesson1)**















- **(参考代码)[https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303]**

