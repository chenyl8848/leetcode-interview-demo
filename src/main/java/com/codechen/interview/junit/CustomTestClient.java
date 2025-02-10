package com.codechen.interview.junit;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：Java陈序员
 * @date：2025-2-10 16:29
 * @description：自定义测试注解客户端类
 */
@Slf4j
public class CustomTestClient {

    public static void main(String[] args) {
        JunitDemo junitDemo = new JunitDemo();

        int param1 = 10;
        int param2 = 0;

        Method[] methods = junitDemo.getClass().getMethods();
        AtomicInteger bugCount = new AtomicInteger();
        // 要写入的文件路径（如果文件不存在，会创建该文件）
        String filePath = "BugReport" + (DateUtil.format(new Date(), "yyyyMMddHHmmss")) + ".txt";

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.isAnnotationPresent(CustomTest.class)) {
                try {
                    method.invoke(junitDemo, param1, param2);
                } catch (Exception e) {
                    log.info("异常名称:{},异常原因:{}",e.getCause().getClass().getSimpleName(),e.getCause().getMessage());
                    bugCount.getAndIncrement();

                    FileUtil.writeString(methods[i].getName() + "\t" + "出现了异常" + "\n", filePath, "UTF-8");
                    FileUtil.appendString("异常名称:" + e.getCause().getClass().getSimpleName() + "\n", filePath, "UTF-8");
                    FileUtil.appendString("异常原因:" + e.getCause().getMessage() + "\n", filePath, "UTF-8");
                } finally {
                    FileUtil.appendString("异常数:" + bugCount.get() + "\n", filePath, "UTF-8");
                }
            }
        }
    }
}
