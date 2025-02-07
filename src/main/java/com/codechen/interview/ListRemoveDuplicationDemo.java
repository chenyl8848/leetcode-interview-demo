package com.codechen.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author：Java陈序员
 * @date：2025-2-7 10:17
 * @description：List 元素去重
 */
public class ListRemoveDuplicationDemo {

    public static void main(String[] args) {
        // 方式一
        m1();

        // 方式二：使用 stream 流 API
        m2();

        // 方式三：使用 HashSet 去重
        m3();

        // 方式四：使用集合元素下标去重
        m4();

        // 方式五
        m5();
    }

    private static void m5() {
        List<Integer> initList = Arrays.asList(3, 3, 1, 2, 4, 5, 4, 4, 4, 8, 7, 9, 8, 6, 6, 6, 6, 9, 8, 7);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>(initList);

        for (int i = 0; i < newList.size(); i++) {
            for (int j = newList.size() - 1; j > i; j--) {
                if (newList.get(i).equals(newList.get(j))) {
                    newList.remove(j);
                }

            }
        }

        newList.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    private static void m4() {
        List<Integer> initList = Arrays.asList(3, 3, 1, 2, 4, 5, 4, 4, 4, 8, 7, 9, 8, 6, 6, 6, 6, 9, 8, 7);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>(initList);

        for (Integer element : srcList) {
            if (newList.indexOf(element) != newList.lastIndexOf(element)) {
                newList.remove(newList.lastIndexOf(element));
            }
        }

        newList.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    private static void m3() {
        List<Integer> initList = Arrays.asList(3, 3, 1, 2, 4, 5, 4, 4, 4, 8, 7, 9, 8, 6, 6, 6, 6, 9, 8, 7);
        List<Integer> srcList = new ArrayList<>(initList);

        // HashSet 是无序的
//        HashSet<Integer> set = new HashSet<>(srcList);
        // 如要保持原顺序可使用 LinkedHashSet
        LinkedHashSet<Integer> set = new LinkedHashSet<>(srcList);

        List<Integer> newList = new ArrayList<>(set);
        newList.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    private static void m2() {
        List<Integer> initList = Arrays.asList(3, 3, 1, 2, 4, 5, 4, 4, 4, 8, 7, 9, 8, 6, 6, 6, 6, 9, 8, 7);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>();

        newList = srcList.stream().distinct().collect(Collectors.toList());
        newList.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    private static void m1() {
        List<Integer> initList = Arrays.asList(3, 3, 1, 2, 4, 5, 4, 4, 4, 8, 7, 9, 8, 6, 6, 6, 6, 9, 8, 7);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>();

        for (int i = 0; i < srcList.size(); i++) {
            if (!newList.contains(srcList.get(i))) {
                newList.add(srcList.get(i));
            }
        }

        newList.forEach(item -> System.out.print(item + " "));
        System.out.println();
    }
}
