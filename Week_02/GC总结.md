##  不同GC参数

- 串行

```powershell
java -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseSerialGC GCLogAxnalysis
```

- 并行

```powershell
java -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseParallelGC GCLogAxnalysis
```

- CMS

```
java -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC GCLogAxnalysis
```

- G1

```
java -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseG1GC GCLogAxnalysis
```

## 压测工具superbenchmarker

启动（添加不同jvm参数）

 java -jar .\gateway-server-0.0.1-SNAPSHOT.jar

```
sb -u http://localhost:8088/api/hello -c 40 -N 30
```

## 不同GC小结

#### 1.串行GC

serial GC 

- -XX:+UseSerialGC 配置串行 GC
- 串行 GC 对年轻代使用 mark-copy（标记-复制） 算法,对老年代使用 mark-sweep-compact （标记-清除-整理）算法
- 都是单线程GC，不能并行处理，会触发STW
- -XX:+USeParNewGC 改进版本的 Serial GC，可以配合 CMS 使用

**适用场景**

该选项只适合几百 MB 堆内存的 JVM（小堆），而且是单核 CPU 时比较有用

#### 2.并行GC

-XX：+UseParallelGC

-XX：+UseParallelOldGC 

-XX：+UseParallelGC -XX:+UseParallelOldGC

- 年轻代和老年代的垃圾回收都会触发 STW 事件。 在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。
- -XX：ParallelGCThreads=N 来指定 GC 线程数， 其默认值为 CPU 核心数

**适用场景**：

并行垃圾收集器适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到 

更高的吞吐量

#### 3.CMS GC

- -XX:+UseConcMarkSweepGC
- 其对年轻代采用并行 STW 方式的 mark-copy (标记-复制)算法，对老年代主要使用并发 mark-sweep (标记-清除)算法
- 不对老年代进行整理，而是使用空闲列表（free-lists）来管理内存空间的回收,维护空余的内存
- 在 mark-and-sweep （标记-清除） 阶段的大部分工作和应用线程一起并发执行

**使用场景**：

低延迟

#### 4.G1 GC

- -XX:+UseG1GC：启用G1 GC； 

使用场景：

内存堆较大，gc的时间可控