# Week02



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

