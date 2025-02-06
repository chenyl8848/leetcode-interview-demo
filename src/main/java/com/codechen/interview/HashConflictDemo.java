package com.codechen.interview;

import java.util.HashSet;

/**
 * @author：Java陈序员
 * @date：2025-2-6 9:11
 * @description：哈希冲突
 */
public class HashConflictDemo {

    static class Person {

    }

    public static void main(String[] args) {
        // 2080
        System.out.println("AA".hashCode());
        // 2112
        System.out.println("BB".hashCode());

        System.out.println("===============");

        // 2112
        System.out.println("Aa".hashCode());
        // 2112
        System.out.println("BB".hashCode());

        // 模拟 Hash 冲突的概率
        HashSet<Integer> set = new HashSet<>();

        for (int i = 1; i <= 15 * 10000; i++) {

            int hashCode = new Person().hashCode();

            if (!set.contains(hashCode)) {
                set.add(hashCode);
            } else {
                // 发生 Hash 冲突，第 105826 次发生 Hash 冲突，Hash 值：2134400190
                // 当超过 100000 次之后，才会发生 Hash 冲突
                System.out.println("发生 Hash 冲突，第 " + i + " 次发生 Hash 冲突，Hash 值：" + hashCode);
            }
        }
    }
}
