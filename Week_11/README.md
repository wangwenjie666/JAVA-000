# 学习笔记

基于Redis封装分布式数据操作： 

1）在Java中实现一个简单的分布式锁； 

2）在Java中实现一个分布式计数器，模拟减库存。



## docker安装使用

参考地址: https://docs.docker.com/docker-for-windows/install-windows-home/ 

windows家庭版

### 系统要求

Windows 10 Home机器必须满足以下要求才能安装Docker Desktop：

- 安装Windows 10版本1903或更高版本。
- 在Windows上启用WSL 2功能。有关详细说明，请参阅 [Microsoft文档](https://docs.microsoft.com/en-us/windows/wsl/install-win10)。
- 要在Windows 10 Home上成功运行WSL 2，需要满足以下硬件先决条件：
  - 具有[二级地址转换（SLAT）的](https://en.wikipedia.org/wiki/Second_Level_Address_Translation)64位处理器
  - 4GB系统内存
  - 必须在BIOS设置中启用BIOS级硬件虚拟化支持。有关更多信息，请参见 [虚拟化](https://docs.docker.com/docker-for-windows/troubleshoot/#virtualization-must-be-enabled)。
- 下载并安装[Linux内核更新程序包](https://docs.microsoft.com/windows/wsl/wsl2-kernel)。

### docker安装

下载运行hello-world

```powershell
docker pull library/hello-world
docker run hello-world
```

查看docker信息

```shell
docker info
```

### 设置国内仓库

```
{
  "registry-mirrors": [
    "https://rq5uyt7.mirror.aliyuncs.com",
    "https://docker.mirrors.ustc.edu.cn",
    "https://registry.docker-cn.com"
  ]
}
```

### docker安装运行redis

```
拉取最新版
docker pull redis:latest

查看安装镜像
docker images

-p 6379:6379：映射容器服务的 6379 端口到宿主机的 6379 端口。外部可以直接通过宿主机ip:6379 访问到 Redis 的服务。
docker run -itd --name redis-test -p 6379:6379 redis

redis-cli 连接测试使用 redis 服务。
docker exec -it redis-test /bin/bash

进入redis-cli
root@c3fbeec1d237:/data# redis-cli
127.0.0.1:6379>
```





## 分布式锁实现：

参考： https://www.cnblogs.com/happy4java/p/11205993.html 





