package com.codechen.interview;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author：Java陈序员
 * @date：2025-2-13 11:11
 * @description：高并发批量任务
 */
public class BatchTaskDemo {

    // 下发优惠卷数量
    public static final Integer COUPON_NUMBER = 50;

    public static void main(String[] args) {
        // 1、先定义线程池
        ThreadPoolTaskExecutor threadPool = initThreadPool();

        // 2、处理业务
        batchTaskAction(threadPool);
    }

    public static void batchTaskAction(ThreadPoolTaskExecutor threadPool) {
        // 1、模拟要下发的 50 条优惠卷
        List<String> coupons = new ArrayList<>(COUPON_NUMBER);
        for (int i = 1; i <= COUPON_NUMBER; i++) {
            coupons.add("优惠卷---" + i);
        }

        // 2、创建 CountDownLatch 构造器参数为任务数量
        CountDownLatch countDownLatch = new CountDownLatch(coupons.size());

        long startTime = System.currentTimeMillis();
        try {
            // 3、将优惠卷集合逐条发送进线程池高并发处理
            for (String coupon : coupons) {
                threadPool.execute(() -> {
                    try {
                        // 4、交个线程池处理的下发业务逻辑
                        System.out.println(String.format("【%s】发送成功", coupon));
                    } finally {
                        // 5、发送一个少一个任务，计数减少一个
                        countDownLatch.countDown();
                    }
                });
            }

            // 6、阻塞当前发送完毕后，方法才能继续向下走
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("----任务处理完毕costTime: " + (endTime - startTime) + " 毫秒");
    }

    /**
     * 在 SpringBoot 环境中推荐使用 ThreadPoolTaskExecutor
     * ThreadPoolTaskExecutor 是 Spring 提供的一个方便的线程池实现，用于异步执行任务或处理并发请求。
     * 在使用 ThreadPoolTaskExecutor 作为 Spring Bean 注册到容器中后，Spring 会负责在应用程序关闭时自动关闭所有注册的线程池，所以不需要手动关闭。
     * 这样不仅可以确保线程池中的线程正确地停止，还可以防止资源泄露和潜在的并发问题。
     */
    public static ThreadPoolTaskExecutor initThreadPool() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        threadPool.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        // 最大可创建的线程数
        threadPool.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        // 线程池维护线程所允许的空闲时间
        threadPool.setKeepAliveSeconds(2);
        // 等待队列最大长度
        threadPool.setQueueCapacity(50);
        // 异步方法内部线程池名称
        threadPool.setThreadNamePrefix("spring-default-thread-pool-");
        // 线程池对拒绝任务（无线程可用)）的处理策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 任务都完成后再关闭线程池
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化
        threadPool.initialize();

        return threadPool;
    }
}
