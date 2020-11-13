# 并发编程

## 1.多线程基础

1. 线程架构

- SMP架构：对称内存结构（cpu和内存通过数据总线交互）
- NUMA架构：非一致内存访问

2. 线程和进程

线程：cpu的基本调度单位是线程，进程和进程的资源是隔离的

进程：操作系统基本运行单位是进程，进程中可能有多个线程，进程的资源是共享的

3. 线程的状态

## 2.守护线程

- 不能将正在运行的线程设置为守护线程 ，否则抛出 java.lang.IllegalThreadStateException
- 设置守护线程在start之前
- 如果当前线程中只有唯一一个线程是守护线程，jvm会自动退出



- 代码：**[守护线程 lesson1](./code/lesson1)**

## 3.sleep wait

- sleep：释放cpu
- wait：释放锁

## 4.线程安全

### 1.并发性质

- 原子性：原子操作，对基本类型变量的读取和赋值是原子操作，这些操作不能被打断的，要么执行，要么不执行

  - 原子操作

```java
x = 10;//只有这个赋值操作是原子操作
y = x;
x++;
x = x + 1;
```

- 可见性

1. java提供了volatile关键字来保证并发的可见性，当一个共享变量被volatile修饰时，会保证修改的值立即被更新到主内存，当有其他线程需要读取时，它会去内存中读取新值（`volatile`不能保证原子性）
   1. 适用场景：多线程多，单线程写
   2. 会阻止指令重拍（指令屏障）
2. synchronized和lock也能保证可见性，能保证同一时刻只有一个线程获取锁然后执行同步代码，并且在释放锁之前将变量的修改刷新到主内存

### 2.synchronized 的实现

- 对象头标记（判断是否加锁）
- 同步方法，同步代码块

## 5.线程池

### 组成

1. Excutor 执行器
2. ExcutorService 接口api
3. ThreadFatory：线程工厂
4. Excutors：创建线程（工具类）

- newSingleThreadExecutor 创建一个单线程的线程池，只有一个线程工作，相当于串行执行
- newFixedThreadPool 创建固定线程数的线程池，每提交一个任务就创建一个线程，直到达到线程池的最大大小
- newCachedThreadPool 创建一个可缓存的线程池，线程池的大小大于任务线程，则会回收，任务增加时，又可以添加线程执行，线程池大小无限制，依赖于操作系统（或jvm）创建的最大线程大小
- newScheduledThreadPool

### Callable & Runnable

- callable call() 有返回值
- runnable run 没有返回值

### Future

get() 获取执行结果

## 6.java并发包

`java.util.concurrency`

- 锁机制Locks
- 原子操作Atomic
- 线程池Executor
- 信号量工具类
- 并发集合类Collections









- **(参考代码)[https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303]**

- 脑图![](./并发编程.png)