# 学习笔记

## 0.

查找jdk安装地址

```
jps -v

41488 Jps -Denv.class.path=.;D:\java\jdk8\lib\dt.jar;D:\java\jdk8\lib\tools.jar; -Dapplication.home=D:\java\jdk8 -Xms8m
```



## 1.课堂笔记

字节码、类加载器、虚拟机

### 字节码

- 字节码由单字节指令组成

指令性质分类：

- 栈操作，局部变量交互
- 流程
- 对象
- 算术和类型转换

### 原子操作：

 所谓原子操作是指不会被线程调度机制打断的操作；这种操作一旦开始，就一直运行到结束，中间不会有任何 context switch （切换到另一个线程） 

### jvm内存结构

- 堆：对象，对象有生命周期
- 栈：变量 ，使用完释放



## 2.字节码文件

- 生成字节码文件 

```
javap -c 指令查看
```

1. HelloWorld

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("HelloWorld");
    }
}
```

字节码文件

```java
Compiled from "HelloWorld.java"
public class HelloWorld {
  public HelloWorld();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String HelloWorld
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

```

查看常量池信息

```
javap -c -verbose HelloWorld.class
```

```java
Classfile /F:/opt/geektime/Code1/src/HelloWorld.class
  Last modified 2020-10-23; size 411 bytes
  MD5 checksum f80888b354252323e21df10eba4ca5d7
  Compiled from "HelloWorld.java"
public class HelloWorld
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #6.#15         // java/lang/Object."<init>":()V
   #2 = Fieldref           #16.#17        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #18            // HelloWorld	字符串常量
   #4 = Methodref          #19.#20        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #18            // HelloWorld
   #6 = Class              #21            // java/lang/Object
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               main
  #12 = Utf8               ([Ljava/lang/String;)V
  #13 = Utf8               SourceFile
  #14 = Utf8               HelloWorld.java
  #15 = NameAndType        #7:#8          // "<init>":()V
  #16 = Class              #22            // java/lang/System
  #17 = NameAndType        #23:#24        // out:Ljava/io/PrintStream;
  #18 = Utf8               HelloWorld
  #19 = Class              #25            // java/io/PrintStream
  #20 = NameAndType        #26:#27        // println:(Ljava/lang/String;)V
  #21 = Utf8               java/lang/Object
  #22 = Utf8               java/lang/System
  #23 = Utf8               out
  #24 = Utf8               Ljava/io/PrintStream;
  #25 = Utf8               java/io/PrintStream
  #26 = Utf8               println
  #27 = Utf8               (Ljava/lang/String;)V
{
  public HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String HelloWorld
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 9: 0
        line 10: 8
}
SourceFile: "HelloWorld.java"

```

2. HelloWorld

```java
public class HelloWorld {
    /**
     * 1.编译：执行 javac HelloWorld.java
     * 2.查看字节码文件 javap -c HelloWorld.class
     */
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
    }
}

```

- 该字节码文件内容是

```java
Compiled from "HelloWorld.java"
public class HelloWorld {
    //反编译后的文件能看到有默认的无参构造
  public HelloWorld();	
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class HelloWorld
       3: dup
       4: invokespecial #3                  // Method "<init>":()V
       7: astore_1
       8: return
}
```

- 查看字节码文件的常量池信息 `javap -c -verbose`

```java
D:\项目\geektime\code1\src>javap -c -verbose HelloWorld.class
Classfile /D:/项目/geektime/code1/src/HelloWorld.class
  Last modified 2020-10-17; size 282 bytes
  MD5 checksum 77b7d944d407fbaa8b3d9767b006c158
  Compiled from "HelloWorld.java"
public class HelloWorld
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER //public类  调用super类方法
Constant pool:
   #1 = Methodref          #4.#13         // java/lang/Object."<init>":()V
   #2 = Class              #14            // HelloWorld
   #3 = Methodref          #2.#13         // HelloWorld."<init>":()V
   #4 = Class              #15            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               main
  #10 = Utf8               ([Ljava/lang/String;)V
  #11 = Utf8               SourceFile
  #12 = Utf8               HelloWorld.java
  #13 = NameAndType        #5:#6          // "<init>":()V
  #14 = Utf8               HelloWorld
  #15 = Utf8               java/lang/Object
{
  public HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
    //栈深度，栈局部变量要保留的槽位
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0

  public static void main(java.lang.String[]);
    //方法描述，(表示形参，[表示数组 L对象 后面的String是类名称 ，V表示返回时void类型
    descriptor: ([Ljava/lang/String;)V	
    // 访问标志，public static
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class HelloWorld
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: return
      LineNumberTable:
        line 9: 0
        line 10: 8
}
SourceFile: "HelloWorld.java"

```

3. if + 运算

```java
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0;

    public void submit(double value) {
        this.count++;
        this.sum += value;
    }

    public double getAvg() {
        if (0 == this.count) {
            return sum;
        }
        return this.sum / this.count;
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}
```

```
编译含有中文
javac -encoding UTF-8 MovingAverage.java
```

```java
Compiled from "MovingAverage.java"
public class MovingAverage {
  public MovingAverage();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iconst_0
       6: putfield      #2                  // Field count:I
       9: aload_0
      10: dconst_0
      11: putfield      #3                  // Field sum:D
      14: return

  public void submit(double);
    Code:
       0: aload_0
       1: dup
       2: getfield      #2                  // Field count:I
       5: iconst_1
       6: iadd
       7: putfield      #2                  // Field count:I
      10: aload_0
      11: dup
      12: getfield      #3                  // Field sum:D
      15: dload_1
      16: dadd
      17: putfield      #3                  // Field sum:D
      20: return

  public double getAvg();
    Code:
       0: iconst_0
       1: aload_0
       2: getfield      #2                  // Field count:I
       5: if_icmpne     13
       8: aload_0
       9: getfield      #3                  // Field sum:D
      12: dreturn
      13: aload_0
      14: getfield      #3                  // Field sum:D
      17: aload_0
      18: getfield      #2                  // Field count:I
      21: i2d
      22: ddiv
      23: dreturn

  public static void main(java.lang.String[]);
    Code:
       0: new           #4                  // class MovingAverage
       3: dup
       4: invokespecial #5                  // Method "<init>":()V
       7: astore_1
       8: iconst_1
       9: istore_2
      10: iconst_2
      11: istore_3
      12: aload_1
      13: iload_2
      14: i2d
      15: invokevirtual #6                  // Method submit:(D)V
      18: aload_1
      19: iload_3
      20: i2d
      21: invokevirtual #6                  // Method submit:(D)V
      24: aload_1
      25: invokevirtual #7                  // Method getAvg:()D
      28: dstore        4
      30: return
}

```



## 3.线程栈和字节码执行模型

- jvm是一台基于栈的计算器，每个线程都有自己的线程栈，用于存储栈帧，
- 每次方法调用，jvm都会创建一个栈帧
- 栈帧由`操作数栈`，`局部变量数组`，`class引用`组成
- class引用指向当前方法在常量池中对应的class

#### 局部变量数组（局部变量表）

包含了方法参数，局部变量

局部变量数组在编译期确定大小

#### 动态计算demo

```java
public class Demo {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = (a + b) * 5;
    }
}
```

javap -c

```java
public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_2
       3: istore_2
       4: iload_1
       5: iload_2
       6: iadd
       7: iconst_5
       8: imul
       9: istore_3
      10: return

```

- 0 将数字1加载到栈上
- 1 将1赋值给a存到本地变量表中
- 2 加载2 到栈上
- 3 将2赋值给b存到本地变量表中
- 4 加载数字1到栈
- 5 加载数字2到栈
- 6 在栈上计算两数之和为3
- 7 加载数字5到栈上
- 8 栈上计算 3 * 5
- 9 将栈上执行结果15存回到本地变量表中
- 10 返回

**注：之所以要从本地变量表再load会栈，是因为store指令会删除栈上的值**

## 4.类的生命周期

- 加载
  - 找class文件，找不到会抛出NoClassDefFound
  - jvm与类加载器（classLoader）协作完成

- 校验
- 准备
  - 创建静态字段，初始化为默认值，方法区中分配内存空间

- 解析
- 初始化
  - 首次主动使用初始化
  - 执行：
    - 类的构造方法
    - static静态赋值语句
    - static静态代码块
  - 子类初始化之前先对父类进行初始化
- 使用
- 卸载

## 5.类的加载时机

### 类的初始化触发：

- main方法所在类
- new对象的类
- 静态方法所在类
- 静态字段所在类
- 子类初始化会先初始化父类
- 接口定义了default方法，直接或者间接实现该接口的类初始化，会触发接口的初始化
- 反射api
- 初次调用MethodHandle实例

### 不会触发初始化：

- 子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化
- 定义对象数组，不会触发该类的初始化
- 没有直接引用定义常量的类，不会触发定义常量所在的类
- 通过类名获取class对象，不会触发类的初始化
- 通过Class.forName加载指定类，如果指定参数initialize为false，也不会触发初始化
- 通过ClassLoader默认的loadClass方法，也不会触发初始化动作（加载，但是没有初始化）

## 6.类的加载机制

系统自带的类加载器

- 启动类加载器 BootstrapClassLoader
- 扩展类加载器 ExtClassLoader
- 应用类加载器 AppClassLoader

类加载机制的三个特点：

- 双亲委托：自定义的类加载器加载某个类的时候，会委托父类加载器去加载，一直往上找，如果都没找到则抛出ClassNotFoundException
- 负责依赖
- 缓存加载

## 7.java内存模型 JMM

Java Memory Model

## 8.内存管理和垃圾回收

- 内存管理就是内存的生命周期管理，包括内存的申请、压缩、回收等

- java的内存管理是gc，jvm的gc模块不仅管理内存的回收，也负责内存的分配和压缩管理
- java的程序指令都是运行在jvm上的，程序需要分配和释放内存
- jvm在**`创建java对象`**的时候去分配新内存，并使用GC算法，根据对象的存活时间，在对象不使用之后，自动执行对象内存的垃圾回收



















