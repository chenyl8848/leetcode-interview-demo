package com.codechen.interview;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author：Java陈序员
 * @date：2025-2-7 9:38
 * @description：BigDecimal 大坑
 */
public class BigDecimalBugDemo {

    public static void main(String[] args) {

        // 1、double 类型精度丢失的问题
        // 0.3 - 0.2 = 0.09999999999999998
        doubleDemo();

        // 2、BigDecimal 构造方法
        bigDecimalLoseAccuracy();

        // 3、BigDecimal 比较
        bigDecimalCompare();

        // 4、BigDecimal 除法
        bigDecimalDivide();

        // 5、BigDecimal 科学计数法
        bigDecimalPlainString();

    }

    /**
     * BigDecimal 科学计数法
     */
    private static void bigDecimalPlainString() {
        BigDecimal amount1 = BigDecimal.valueOf(1234567890123456789.3141592631415926);
        // 科学计数法: 1.23456789012345677E+18
        System.out.println(amount1);
        // 科学计数法: 1.23456789012345677E+18
        System.out.println(amount1.toString());
        // 不使用科学计数法: 1234567890123456770
        System.out.println(amount1.toPlainString());

        System.out.println("=====================");
        // 使用 String 构造默认不使用科学计数法
        BigDecimal amount2 = new BigDecimal("1234567890123456789.3141592631415926");
        // 1234567890123456789.3141592631415926
        System.out.println(amount2);
        // 1234567890123456789.3141592631415926
        System.out.println(amount2.toString());
        // 1234567890123456789.3141592631415926
        System.out.println(amount2.toPlainString());
    }

    /**
     * 除法商的结果,需要指定精度
     * BigDecimal 的 8 种 RoundingMode（舍入模式）
     * https://my.oschina.net/u/3644969/blog/4927776
     * <p>
     * ROUND_HALF_UP
     * 向 “最接近” 的数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
     * 如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同；否则舍入行为与 ROUND_DOWN 相同。
     * 这种模式也就是我们常说的我们的 “四舍五入”。
     * <p>
     * ROUND_HALF_DOWN
     * <p>
     * 向 “最接近” 的数字舍入，如果与两个相邻数字的距离相等，则为向下舍入的舍入模式。
     * 如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同；否则舍入行为与 ROUND_DOWN 相同。
     * 这种模式也就是我们常说的我们的 “五舍六入”。
     */
    private static void bigDecimalDivide() {
        BigDecimal amount1 = new BigDecimal("2.0");
        BigDecimal amount2 = new BigDecimal("3.0");

        // 不指定精度会抛出异常
        // java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
//        System.out.println(amount1.divide(amount2));

        System.out.println(amount1.divide(amount2, 2, RoundingMode.HALF_UP));
    }

    /**
     * BigDecimal 比较
     */
    private static void bigDecimalCompare() {
        System.out.println("BigDecimal 比较");
        BigDecimal amount1 = new BigDecimal("0.9");
        BigDecimal amount2 = new BigDecimal("0.90");

        // false 源码：equals 有精度范围比较导致
        System.out.println(amount1.equals(amount2));
        // 0
        System.out.println(amount1.compareTo(amount2));

    }

    /**
     * BigDecimal 精度丢失的问题
     */
    private static void bigDecimalLoseAccuracy() {
        System.out.println("BigDecimal 精度丢失的问题");

        // BigDecimal 使用 double 构造存在精度丢失
        BigDecimal amount1 = new BigDecimal(0.3);
        BigDecimal amount2 = new BigDecimal(0.2);

        // 0.299999999999999988897769753748434595763683319091796875
        System.out.println(amount1);
        // 0.200000000000000011102230246251565404236316680908203125
        System.out.println(amount2);
        // 0.099999999999999977795539507496869191527366638183593750
        System.out.println(amount1.subtract(amount2));

        System.out.println("=========================");

        // 推荐使用 String 进行构造
        BigDecimal amount3 = new BigDecimal("0.3");
        BigDecimal amount4 = new BigDecimal("0.2");

        // 0.3
        System.out.println(amount3);
        // 0.2
        System.out.println(amount4);
        // 0.1
        System.out.println(amount3.subtract(amount4));

        System.out.println("=========================");

        // 或者使用 BigDecimal.valueOf() 底层实现：return new BigDecimal(Double.toString(val));
        BigDecimal amount5 = BigDecimal.valueOf(0.3);
        BigDecimal amount6 = BigDecimal.valueOf(0.2);

        // 0.3
        System.out.println(amount5);
        // 0.2
        System.out.println(amount6);
        // 0.1
        System.out.println(amount5.subtract(amount6));


    }

    /**
     * double 类型精度丢失的问题
     * 0.3 - 0.2 = 0.09999999999999998
     */
    private static void doubleDemo() {
        System.out.println("double 类型精度丢失的问题");
        double amount1 = 0.3;
        double amount2 = 0.2;

        System.out.println(amount1 - amount2);
    }

}
