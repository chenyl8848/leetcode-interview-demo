package com.codechen.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：Java陈序员
 * @date：2025-2-13 10:30
 * @description：线程池异常处理
 */
@Slf4j
public class ThreadPoolExceptionDemo {

    public static void main(String[] args) {

        // 1、默认调用 submit 会吞掉异常
//        defaultSubmit();

        // 2、submit 执行后，如果 get 方法调用想要获得返回值，会抛出异常
//        defaultSubmitAndGet();

        // 3、调用 execute 会抛出异常
//        defaultExecute();

        // 4、自定义线程池
        handleException();
    }


    /**
     * 线程池异常处理
     * 在 SpringBoot 环境中推荐使用 ThreadPoolExecutor
     * ThreadPoolTaskExecutor是 Spring 提供的一个方便的线程池实现，用于异步执行任务或处理并发请求。
     * 在使用 ThreadPoolTaskExecutor 作为 Spring Bean 注册到容器中后，Spring 会负责在应用程序关闭时自动关闭所有注册的线程池，所以不需要手动关闭。
     * 这样不仅可以确保线程池中的线程正确地停止，还可以防止资源泄露和潜在的并发问题。
     */
    public static void handleException() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors() * 2,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100)) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null) {
                    log.error(t.getMessage(), t);
                }

                try {
                    if (t == null && r instanceof Future<?>) {
                        Future<?> future = (Future<?>) r;

                        if (future.isDone()) {
                            future.get();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        threadPool.submit(() -> {
            // 默认 submit 方法（不获取返回值）会吞掉异常，改写后可以抛出异常
            System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...start");
            int age = 20 / 1;
            System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...end");
        });

        System.out.println();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();

        threadPool.execute(() -> {
            // execute 会抛出异常
            System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...start");
            for (int i = 1; i <= 4; i++) {
                if (i == 3) {
                    int age = 10 / 0;
                }
                System.out.println("come in execute " + i);
            }
            System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...end");
        });
    }

    /**
     * 默认调用 execute 会抛出异常
     */
    public static void defaultExecute() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...start");
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        int value = 10 / 0;
                    }

                    System.out.println("come in execute " + i);
                }

                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...end");
            });
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * submit 执行后，如果 get 方法调用想要获得返回值，会抛出异常
     */
    public static void defaultSubmitAndGet() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try {
            Future<?> future = threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...start");
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        int value = 10 / 0;
                    }

                    System.out.println("come in execute " + i);
                }

                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...end");
            });

            // 如果没有这一行，异常被吞
            future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * 默认调用 submit 会吞掉异常
     */
    public static void defaultSubmit() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        try {
            threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...start");
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        int value = 10 / 0;
                    }

                    System.out.println("come in execute " + i);
                }

                System.out.println(Thread.currentThread().getName() + " 进入线程池 submit 方法...end");
            });
        } finally {
            threadPool.shutdown();
        }
    }
}
