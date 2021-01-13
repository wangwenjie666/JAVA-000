# 学习笔记

1. 搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费 
2. 搭建一个 3 节点 Kafka 集群，测试功能和性能；实现 spring kafka 下对 kafka 集群的操作 



## docker安装activemq

安装

```
docker search activemq
docker pull webcenter/activemq
```

启动

```
docker run -d --name activemq -p 61616:61616 -p 8161:8161 webcenter/activemq
```

-  https://www.cnblogs.com/liyiran/p/11523319.html 

## 配置kafka集群

### windows 安装zk

- zookeeper可视化工具：https://issues.apache.org/jira/secure/attachment/12436620/ZooInspector.zip

  ```
  D:\env\ZooInspector\build
   java -jar .\zookeeper-dev-ZooInspector.jar
  ```

  

- zk安装包： https://archive.apache.org/dist/zookeeper/zookeeper-3.3.3/ 

- 解压

- conf目录下copy  zoo_sample.cfg修改名为：zoo.cfg 

  ```
  # The number of milliseconds of each tick
  tickTime=2000
  # The number of ticks that the initial 
  # synchronization phase can take
  initLimit=10
  # The number of ticks that can pass between 
  # sending a request and getting an acknowledgement
  syncLimit=5
  # the directory where the snapshot is stored. 数据存放位置
  dataDir=d:\\zk\\data
  # the port at which the clients will connect
  clientPort=2181
  
  ```

- 运行zk

  ```
  PS D:\env\zookeeper-3.3.3\bin> .\zkServer.cmd
  ```

### 安装kafka

- 下载安装包：  http://kafka.apache.org/downloads 

- 安装 ：  Scala 2.13  - [kafka_2.13-2.7.0.tgz](https://www.apache.org/dyn/closer.cgi?path=/kafka/2.7.0/kafka_2.13-2.7.0.tgz) ([asc](https://www.apache.org/dist/kafka/2.7.0/kafka_2.13-2.7.0.tgz.asc), [sha512](https://www.apache.org/dist/kafka/2.7.0/kafka_2.13-2.7.0.tgz.sha512)) 

- 解压

- 修改配置文件 /config/server.properties

  ```
  打开
  listeners=PLAINTEXT://localhost:9092
  
  log.dirs=d:\\kafka\\logs
  ```

- 运行zk

  ```
  PS D:\env\kafka_2.13-2.7.0> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
  ```

- 运行kafka

  ```
  PS D:\env\kafka_2.13-2.7.0> .\bin\windows\kafka-server-start.bat .\config\server.properties
  ```

- 操作kafka命令

  ```
  PS D:\env\kafka_2.13-2.7.0> .\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --create --topic testk --partitions 4 --replication-factor 1
  ```

  

### 配置集群修改配置文件

- 集群文件三个改动点

```
broker.id=1
listeners=PLAINTEXT://localhost:9001
log.dirs=d:\\env\kafka-logs1
```

- 启动集群

```
 .\bin\windows\kafka-server-start.bat .\config\server9001.properties
 .\bin\windows\kafka-server-start.bat .\config\server9002.properties
 .\bin\windows\kafka-server-start.bat .\config\server9003.properties
```

- 创建topic

```
.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --create --topic test01 --partitions 4 --replication-factor 3
```

