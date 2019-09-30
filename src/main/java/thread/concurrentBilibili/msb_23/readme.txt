设计模式之单例模式(线程安全)
可以说单例模式是所有设计模式中最简单的一种。

单例模式就是说系统中对于某类的只能有一个对象，不可能出来第二个。

单例模式也是23种设计模式中在面试时少数几个会要求写代码的模式之一。主要考察的是多线程下面单例模式的线程安全性问题。

1.多线程安全单例模式实例一(不使用同步锁)

复制代码
1 public class Singleton {
2     private static Singleton sin=new Singleton();    ///直接初始化一个实例对象
3     private Singleton(){    ///private类型的构造函数，保证其他类对象不能直接new一个该对象的实例
4     }
5     public static Singleton getSin(){    ///该类唯一的一个public方法    
6         return sin;
7     }
8 }
复制代码
　　上述代码中的一个缺点是该类加载的时候就会直接new 一个静态对象出来，当系统中这样的类较多时，会使得启动速度变慢 。现在流行的设计都是讲“延迟加载”，我们可以在第一次使用的时候才初始化第一个该类对象。所以这种适合在小系统。 

2.多线程安全单例模式实例二(使用同步方法)

复制代码
 1 public class Singleton {  
 2      private static Singleton instance;  
 3      private Singleton (){
 4          
 5      }   
 6      public static synchronized Singleton getInstance(){    //对获取实例的方法进行同步
 7        if (instance == null)     
 8          instance = new Singleton(); 
 9        return instance;
10      }
11  }  
复制代码
　　上述代码中的一次锁住了一个方法， 这个粒度有点大 ，改进就是只锁住其中的new语句就OK。就是所谓的“双重锁”机制。

3.多线程安全单例模式实例三(使用双重同步锁)

复制代码
 1 public class Singleton {  
 2      private static Singleton instance;  
 3      private Singleton (){
 4      }   
 5      public static Singleton getInstance(){    //对获取实例的方法进行同步
 6        if (instance == null){
 7            synchronized(Singleton.class){
 8                if (instance == null)
 9                    instance = new Singleton(); 
10            }
11        }
12        return instance;
13      }
14      
15  }
复制代码
分类: 设计模式