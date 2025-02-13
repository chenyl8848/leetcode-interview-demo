package com.codechen.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：Java陈序员
 * @date：2025-2-13 10:57
 * @description：自定义线程池
 */
@Slf4j
public class CustomThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                // 核心线程数
                Runtime.getRuntime().availableProcessors(),
                // 最大线程数
                Runtime.getRuntime().availableProcessors() * 2,
                // 存活时间
                1L,
                TimeUnit.SECONDS,
                // 阻塞i队列
                new LinkedBlockingDeque<>(100)) {
            // 处理异常，以免在使用线程池时吞掉异常
            @Override
            protected void afterExecute(Runnable runnable, Throwable throwable) {
                // execute 运行
                if (throwable != null) {
                    log.error(throwable.getMessage(), throwable);
                }

                // submit 运行
                try {
                    if (throwable == null && runnable instanceof Future<?>) {
                        Future<?> future = (Future<?>) runnable;
                        future.get();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            // 处理业务逻辑
        } finally {
            // 线程池优雅停机
            threadPoolGracefulShutdown(threadPool);
        }

    }

    private static void threadPoolGracefulShutdown(ExecutorService threadPool) {
        if (threadPool != null && !threadPool.isShutdown()) {
            threadPool.shutdown();
            try {
                if (!threadPool.awaitTermination(120, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow();

                    if (!threadPool.awaitTermination(120, TimeUnit.SECONDS)) {
                        System.out.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                threadPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
