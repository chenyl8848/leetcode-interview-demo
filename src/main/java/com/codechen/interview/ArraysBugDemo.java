package com.codechen.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author：Java陈序员
 * @date：2025-1-17 9:29
 * @description：Arrays.asList() 把数组转换成集合的大坑
 */
public class ArraysBugDemo {

    public static void main(String[] args) {
        Integer[] arrays = new Integer[]{1, 2, 3, 4, 5};

        List<Integer> list = Arrays.asList(arrays);

        list.forEach(System.out::println);

//        Exception in thread "main" java.lang.UnsupportedOperationException
//        list.remove(5);

//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Integer next = iterator.next();
//            if (next == 5) {
//                iterator.remove();
//            }
//        }
//
//        list.forEach(System.out::println);

//        list.forEach(System.out::println);
//
//        arrays[4] = 99;
//
//        list.forEach(System.out::println);

//        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(arrays));
//
//        list1.forEach(System.out::println);
//
//        list1.add(99);
//
//        list1.forEach(System.out::println);

    }
}
