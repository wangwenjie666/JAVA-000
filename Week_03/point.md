高性能

- 高并发
- 高吞吐
- 低延迟



延迟：针对服务器端（服务器响应时间-请求进入服务器时间）

响应时间：针对客户端（客户端响应时间-客户端请求时间）



高性能的副作用

应对策略：（混沌工程）

1. 容量
2. 爆炸半径
3. 工程方面积累与改进

每天86400s * 1000 订单 = 86，400，000（8600w订单）

每秒 1000 左右

双十一 50w左右



netty

- 事件处理机制
- reactor模型



tcp 三次握手

udp



DSL：领域专用语言



线程创建

- Thread.start(java)
- 调用javaThread （jvm）
- 调用系统线程（os）