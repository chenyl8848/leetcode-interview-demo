package com.codechen.interview;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Java陈序员
 * @date：2025-2-11 16:18
 * @description：ThreadLocal Demo
 * ThreadLocal 提供线程局部变量。
 * 这些变量与正常的变量不同，因为每一个线程在访问 ThreadLocal 实例的时候（通过其 get 或 set 方法）都有自己的、独立初始化的变量副本。
 * ThreadLocal实 例通常是类中的私有静态字段，使用它的目的是希望将状态（例如，用户 ID 或事务 ID）与线程关联起来。
 * <p>
 * 实现每一个线程都有自己专属的本地变量副本(自己用自己的变量不麻烦别人，不和其他人共享，人人有份，人各一份)，
 * <p>
 * 主要解决了让每个线程绑定自己的值，通过使用 get() 和 set() 方法，获取默认值或将其值更改为当前线程所存的副本的值从而避免了线程安全问题。
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {

//        saleSu7();

//        m1();

//        m2();

//        m3();

        m4();
    }

    private static void m4() {
        System.out.println("==========TransmittableThreadLocal==========");

        // 阿里自研，需引入依赖
       /* <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>transmittable-thread-local</artifactId>
            <version>2.14.3</version>
        </dependency>*/
        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();

        transmittableThreadLocal.set(Thread.currentThread().getName() + "-Java");
        // major: main-Java
        System.out.println("major: " + transmittableThreadLocal.get());

        // 为了看到效果，这里创建大小为 1 的线程池方便看到效果,池中只有 1 个线程才有效果，池中只有 1 个线程才有效果
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        // 这里需要用 TtlExecutors.getTtlExecutorService 将原线程池包装下
        threadPool = TtlExecutors.getTtlExecutorService(threadPool);

        threadPool.execute(() -> {
            // major-thread-first: main-Java
            System.out.println("major-thread-first: " + transmittableThreadLocal.get());
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        transmittableThreadLocal.set(Thread.currentThread().getName() + "-Vue");
        // major: main-Vue
        System.out.println("major: " + transmittableThreadLocal.get());

        threadPool.execute(() -> {
            // major-thread-second: main-Vue
            System.out.println("major-thread-second: " + transmittableThreadLocal.get());
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }

    private static void m3() {
        System.out.println("==========InheritableThreadLocal2==========");
        // InheritableThreadLocal：遇到线程池，会有问题
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        inheritableThreadLocal.set(Thread.currentThread().getName() + "-Java");
        // major: main-Java
        System.out.println("major: " + inheritableThreadLocal.get());

        // 为了看到效果，这里创建大小为 1 的线程池方便看到效果,池中只有 1 个线程才有效果，池中只有 1 个线程才有效果
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        threadPool.execute(() -> {
            // major-thread-first: main-Java
            System.out.println("major-thread-first: " + inheritableThreadLocal.get());
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inheritableThreadLocal.set(Thread.currentThread().getName() + "-Vue");
        // major: main-Vue
        System.out.println("major: " + inheritableThreadLocal.get());

        threadPool.execute(() -> {
            // major-thread-second: main-Java
            System.out.println("major-thread-second: " + inheritableThreadLocal.get());
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

    }

    private static void m2() {
        System.out.println("==========InheritableThreadLocal1==========");
        // 使用 InheritableThreadLocal，子线程可以获得父线程 set 进去的值
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        inheritableThreadLocal.set(Thread.currentThread().getName() + "-Java");
        // major: main-Java
        System.out.println("major: " + inheritableThreadLocal.get());

        new Thread(() -> {
            // major-thread1: main-Java
            System.out.println("major-thread1: " + inheritableThreadLocal.get());
            inheritableThreadLocal.set(Thread.currentThread().getName() + "-Vue");
            // major-thread1: thread1-Vue
            System.out.println("major-thread1: " + inheritableThreadLocal.get());
        }, "thread1").start();

        new Thread(() -> {
            // major-thread2: main-Java
            System.out.println("major-thread2: " + inheritableThreadLocal.get());
            inheritableThreadLocal.set(Thread.currentThread().getName() + "-MySQL");
            // major-thread2: thread2-MySQL
            System.out.println("major-thread2: " + inheritableThreadLocal.get());
        }, "thread2").start();
    }

    private static void m1() {
        System.out.println("==========ThreadLocal==========");
        // ThreadLocal 可以在当前线程中共享数据，set/get 需要在同一个线程中执行才行，别人的取不到
        ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(() -> null);

        threadLocal.set(Thread.currentThread().getName() + "-Java");
        // major: main-Java
        System.out.println("major: " + threadLocal.get());

        new Thread(() -> {
            // major-thread1: null
            System.out.println("major-thread1: " + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName() + "-Vue");
            // major-thread1: thread1-Vue
            System.out.println("major-thread1: " + threadLocal.get());
        }, "thread1").start();

        new Thread(() -> {
            // major-thread2: null
            System.out.println("major-thread2: " + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName() + "-MySQL");
            // major-thread2: thread2-MySQL
            System.out.println("major-thread2: " + threadLocal.get());
        }, "thread2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1、3个销售卖保时米 SU7，求 3 个销售本团队总体销售额
     * 2、3个销售卖保时米 SU7，求 3 个销售的各自独立的个体销售额，不参加总和计算
     *
     * @throws InterruptedException
     */
    private static void saleSu7() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Su7 su7 = new Su7();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j < new Random().nextInt(3) + 1; j++) {
                        su7.setSaleTotal();
                        su7.setPersonSale();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t号销售售出：" + su7.personSale.get() + " 辆");
                } finally {
                    countDownLatch.countDown();
                    su7.personSale.remove();
                }
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t销售总额：" + su7.getSaleTotal() + " 辆");
    }
}

class Su7 {

    private int saleTotal;

    ThreadLocal<Integer> personSale = ThreadLocal.withInitial(() -> 0);

    public synchronized void setSaleTotal() {
        saleTotal++;
    }

    public void setPersonSale() {
        personSale.set(personSale.get() + 1);
    }

    public Integer getSaleTotal() {
        return this.saleTotal;
    }

}
