package com.codechen.interview;

/**
 * @author：Java陈序员
 * @date：2025-2-6 9:30
 * @description： 【强制】所有整型包装类对象之间值的比较，全部使用equals方法比较。
 * 说明：对于 Integer var = ? 在 -128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生，会复用已有对
 * 象，这个区间内的 Integer 值可以直接使用 == 进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复
 * 用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。
 */
public class IntegerBugDemo {

    public static void main(String[] args) {
        // new Integer() 在 Java8 之后被弃用，推荐使用 Integer.valueOf()
//        Integer a = new Integer(99);
//
//        Integer b = Integer.valueOf(99);

        Integer a = Integer.valueOf(600);
        Integer b = Integer.valueOf(600);
        int c = 600;

        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true
        System.out.println(b == c); // ture
        System.out.println(b.equals(c)); // true

        System.out.println("================");

        Integer d = Integer.valueOf(99);
        Integer e = Integer.valueOf(99);
        int f = 99;

        System.out.println(d == e); // true
        System.out.println(d.equals(e)); // true
        System.out.println(e == f); // true
        System.out.println(e.equals(f)); // true
    }
}
