# Week02

- [ ] nio总结
- [ ] netty 例子

# NIO

- 端口：区别一台计算机的多个进程
- 压测工具superbenchmarker

安装

```
windows shell执行
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

执行
choco install superbenchmarker
-c 代表并发请求数 -N运行测试的秒数
sb -u http://localhost:8088/api/hello -c 20 -N 60
```

## 1.socket编程

单线程socket

```java
public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                //执行业务，模拟输出http报文头和内容
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,nio");
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
   
```

多线程socket

```java
public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    service(socket);
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```

线程池socket

```java
public static void main(String[] args) throws IOException{
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        final ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
```

压测结果

```powershell
PS C:\Users\Administrator> sb -u http://localhost:8801 -c 40 -N 30
Starting at 2020/10/25 星期日 22:28:21
[Press C to stop the test]
1438    (RPS: 40.5)
---------------Finished!----------------
Finished at 2020/10/25 星期日 22:28:57 (took 00:00:35.5706407)
1474    (RPS: 41.5)                     Status 200:    1474

RPS: 46.6 (requests/second)
Max: 1508ms
Min: 172ms
Avg: 822.9ms
```

```powershell
PS C:\Users\Administrator> sb -u http://localhost:8802 -c 40 -N 30
Starting at 2020/10/25 星期日 22:37:16
[Press C to stop the test]
40741   (RPS: 1197.1)
---------------Finished!----------------
Finished at 2020/10/25 星期日 22:37:51 (took 00:00:34.1069412)
Status 200:    40739
Status 303:    2

RPS: 1311.1 (requests/second)
Max: 302ms
Min: 12ms
Avg: 23.9ms
```

```powershell
PS C:\Users\Administrator> sb -u http://localhost:8803 -c 40 -N 30
Starting at 2020/10/25 星期日 22:38:10
[Press C to stop the test]
48875   (RPS: 1441.4)
---------------Finished!----------------
Finished at 2020/10/25 星期日 22:38:44 (took 00:00:34.0649146)
Status 200:    48876
Status 303:    1

RPS: 1568.6 (requests/second)
Max: 93ms
Min: 16ms
Avg: 21.6ms
```

小结

```
线程池 > 直接创建多线程 > 单线程
```

## 2.深入io通信

socket通信存在两种操作：

- cpu计算
- io操作

服务器通信过程问题分析：

1. 在进行通信过程中，如果是io密集型的，那么cpu的多数资源就处于闲置状态
2. 除了线程cpu的问题，socket操作先经过用户空间（代码运行空间），然后到内核空间（系统底层，操作系统），有数据复制的操作，性能不是最优

- 假如能在socket访问时直接返回结果，不需要在用户和内核空间来回拷贝，引入NIO

## 3.nio模型

同步、异步 （通信模式）

​	同步代码还是占多数。

阻塞、非阻塞 （线程模式）

### 5种IO模型

- 阻塞IO模型（同步阻塞）
  - 阻塞io，while(true)一直等待连接请求
- 非阻塞IO模型（同步非阻塞） 
  - 内核会立即返回，cpu可以做其他的事情，
  - 需要不断询问内核是否处理完成（用户线程负责轮询）
  - 一旦完成，和阻塞io的操作基本一样
- IO复用模型（同步阻塞）<多路复用> （事件驱动IO）
  - 通过 select或者poll轮询所有socket，有数据到达，通知用户进程
  - IO复用同非阻塞IO本质一样，（内核负责轮询）
- 信号驱动IO（同步非阻塞）
  - 线程池 - EDA - SEDA
- 异步IO（异步非阻塞）

# Netty

## 网络应用开发框架

1. 异步
2. 事件驱动
3. 基于NIO

适用于

- 服务端
- 客户端
- TCP/UDP

## 特性

- 高吞吐
- 低延迟
- 低开销
- 零拷贝
- 可扩容



## 基本概念

- Channel 通道，代表一个打开的连接，可以执行IO操作（对Channel的操作都是非阻塞的）
- ChannelFuture
- Event  & Handler 事件和处理器
- Encoder & Decoder 编码器和解码器
- ChannelPipeline 数据处理管道

### Event & Handler



### 压测netty-sever

可以看出结果显示的吞吐量很高

```
PS C:\Users\Administrator> sb -u http://localhost:8808/test -c 20 -N 60
Starting at 2020/11/4 星期三 23:10:59
[Press C to stop the test]
504385  (RPS: 7890.8)
---------------Finished!----------------
Finished at 2020/11/4 星期三 23:12:03 (took 00:01:04.0071737)
Status 200:    504385

RPS: 8257.2 (requests/second)
Max: 99ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 2ms
99.9%   below 8ms
```

