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

网络太慢 TODO



## 分布式锁实现：

参考： https://www.cnblogs.com/happy4java/p/11205993.html 





