package com.codechen.interview;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author：Java陈序员
 * @date：2025-2-10 14:49
 * @description：IDEA Debug 技巧
 */
public class IDEADebugDemo {

    private static int x;

    public static void main(String[] args) {
        // 1、基础按钮：
        // 光标回到当前断点行（Alt + F10）、
        // 步过（F8）、
        // 步入（F7）、
        // 强制步入（Alt + Shift + F7）：针对 JDK 源码功能
        // 步出（Shift + F8）、
        // 运行到光标所在行（F7）
        helloDebug();

        // 2、Stream 流
        streamChain();

        // 3、forceReturnAndStop
        // 直接停服务，后续代码仍会执行完，会产生废数据
        forceReturnAndStop();

        // 4、四大断点
        // 4.1 Line Breakpoint

        // 4.2 Field Watchpoint，在变量 Field 上面打断点，默认眼睛图标
        setWatchPoint();

        // 4.3 Method Breakpoints，在接口内定义的方法上面打断点，默认菱形图标
        methodBreakpoints();

        // 4.4 Exception Breakpoints
        // 新增异常断点，点击View Breakpoints，选择异常类型，运行含有 NullPointerException 的代码，自动闪电定位，不用人工打断点
        Integer i = null;
        System.out.println(i.intValue());
    }

    private static void methodBreakpoints() {
        USB phone = new Phone();
        phone.insert();

        USB computer = new Computer();
        computer.insert();
    }

    private static void setWatchPoint() {
        x = 20;
        baseEmbed();
        x = 30;
    }

    private static void forceReturnAndStop() {
        System.out.println("MySQL..............01");
        System.out.println("MySQL..............02");
        baseEmbed();
        System.out.println("MQ.................03");
        System.out.println("MQ.................04");

    }

    private static void streamChain() {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6).filter(item -> item > 3).map(item -> item * 2).collect(Collectors.toList());
        list.forEach(item -> System.out.print(item + " "));
    }

    private static void helloDebug() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        baseEmbed();
        map.put(3, "c");
        map.put(4, "d");
    }

    private static void baseEmbed() {
        System.out.println("baseEmbed---------------1");
        System.out.println("baseEmbed---------------2");
        System.out.println("baseEmbed---------------3");
        System.out.println("baseEmbed---------------4");
    }
}

interface USB {

    void insert();

}

class Phone implements USB {
    @Override
    public void insert() {
        System.out.println("Phone.....USB.....");
    }
}

class Computer implements USB {
    @Override
    public void insert() {
        System.out.println("Computer.....USB.....");
    }
}
