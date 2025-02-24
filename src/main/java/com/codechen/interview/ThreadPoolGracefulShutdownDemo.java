package com.codechen.interview;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：Java陈序员
 * @date：2025-2-11 17:24
 * @description：线程池优雅停机
 */
public class ThreadPoolGracefulShutdownDemo {

    public static void main(String[] args) {

//        shutdownTest();

//        shutdownNowTest();

//        shutdownAwaitTerminationTest();

//        shutdownNowAwaitTerminationTest();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            finalOKShutdownAndAwaitTermination(threadPool);
        }
    }

    private static void shutdownNowAwaitTerminationTest() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            try {
                threadPool.execute(new Task(i));
            } catch (RejectedExecutionException e) {
                System.out.println("rejected, task-" + i);
            }

            if (i == 5) {
                List<Runnable> tasks = threadPool.shutdownNow();
                for (Runnable task : tasks) {
                    if (task instanceof Task) {
                        System.out.println("waiting task: " + ((Task) task).getName());
                    }
                }
            }
        }

        try {
            boolean isStop = threadPool.awaitTermination(4, TimeUnit.SECONDS);
            System.out.println("is pool isStop: " + isStop);
            System.out.println(Thread.currentThread().getName() + "\t" + "2222222222222222222");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------");
        System.out.println(Thread.currentThread().getName() + "\t" + "all tests finished");
    }

    private static void shutdownAwaitTerminationTest() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 提交 10 个任务，在第 5 个任务提交完，准备提交第 6 个的时候执行 shutdown
        for (int i = 1; i <= 10; i++) {
            System.out.println("第：" + i + " 次提交");
            try {
                threadPool.execute(new Task(i));
            } catch (RejectedExecutionException e) {
                System.out.println("rejected, task-" + i);
            }
            //i等于5的时候shutdown，意味着从第6次开始就不能提交新任务
            if (i == 5) {
                threadPool.shutdown();
                System.out.println("i 等于 5 的时候shutdown，意味着从第 6 次开始就不能提交新任务");
                System.out.println();
            }
        }

        try {
            /**
             * 任务没执行完且没到设定时间，是不会执行最下面两行打印代码的。
             *
             * 现在把等待时间设置为 4 秒，达到设置时间后，就不再阻塞当前线程了，
             * 直接打印了下面两行代码，并且返回了 false 说明线程池没有停止。
             *
             * 有时我们需要主线程等所有子线程执行完毕后再运行，在所有任务提交后，
             * 调用 shutdown 触发 awaitTermination，阻塞主线程，当所有子线程执行完毕后，解除阻塞。
             */
            boolean isStop = threadPool.awaitTermination(4, TimeUnit.SECONDS);
            System.out.println("is pool isStop: " + isStop);
            System.out.println(Thread.currentThread().getName() + "\t" + "111111111111111111111111");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------");
        System.out.println(Thread.currentThread().getName() + "\t" + "mission is over");
        System.out.println();
    }

    /**
     * 调用 shutdownNow 后，第一个任务正在睡眠的时候，触发了 interrupt 中断，
     * 之前等待的任务 2-5 被从队列中清除并返回，之后的任务 6~10 被拒绝
     * but
     * shutdownNow 方法将线程池状态置为 STOP，试图让线程池立刻停止，
     * 但不一定能保证立即停止，要等所有正在执行的任务（不能被 interrupt 中断的任务）执行完才能停止
     */
    private static void shutdownNowTest() {
        //一池一个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            try {
                threadPool.execute(new Task(i));
            } catch (RejectedExecutionException e) {
                System.out.println("rejected, task-" + i);
            }

            if (i == 5) {
                List<Runnable> tasks = threadPool.shutdownNow();
                for (Runnable task : tasks) {
                    if (task instanceof Task) {
                        System.out.println("waiting task: " + ((Task) task).getName());
                    }
                }
            }
        }
    }

    /**
     * 第 6 个任务开始及之后的任务都被拒绝了，1~5号任务正常执行。
     * 所以 shutdown 方法将线程池状态置为 SHUTDOWN，线程池并不会立即停止，要等正在执行和队列里等待的任务执行完才会停止。
     */
    private static void shutdownTest() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            //此处故意不加 try...catch 块，方便效果演示
            threadPool.execute(new Task(i));

            //i == 5 的时候 shutdown, 意味着从第 6 次开始就不能提交新任务

            if (i == 5) {
                threadPool.shutdown();
            }

        }
    }

    static class Task implements Runnable {
        @Getter
        String name = "";

        public Task(int i) {
            name = "task-" + i;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("sleep completed, " + getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupted, " + getName());
                return;
            }
            System.out.println(getName() + " finished");
        }
    }

    /**
     * 参考官网使用，最后的终结，优雅关停，but有点费事
     *
     * @param threadPool
     */
    public static void finalOKShutdownAndAwaitTermination(ExecutorService threadPool) {
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
