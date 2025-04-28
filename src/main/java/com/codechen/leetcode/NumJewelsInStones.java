package com.codechen.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：Java陈序员
 * @date：2025-4-28 11:02
 * @description：宝石与石头 https://leetcode.cn/problems/jewels-and-stones/description/
 * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * <p>
 * 示例 1：
 * 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * <p>
 * 示例 2：
 * 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 */
public class NumJewelsInStones {

    public static int numJewelsInStones1(String jewels, String stones) {

        int count = 0;
        for (int i = 0; i < jewels.length(); i++) {
            for (int j = 0; j < stones.length(); j++) {
                if (jewels.charAt(i) == stones.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int numJewelsInStones2(String jewels, String stones) {

        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }

        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) {
                count++;
            }
        }

        return count;
    }

    public static int numJewelsInStones(String jewels, String stones) {

        int count = 0;

        int[] types = new int[256];
        for (int i = 0; i < jewels.length(); i++) {
            // 将所有的宝石值放入到对应数组下标并设置值为1
            types[jewels.charAt(i)] = 1;
        }

        for (int i = 0; i < stones.length(); i++) {
            // 如果能找到就 + 1，找不到就 + 0
            count += types[stones.charAt(i)];
        }

        return count;
    }

    public static void main(String[] args) {
//        String jewels = "aA", stones = "aAAbbbb";
        String jewels = "z", stones = "ZZ";
        int result = numJewelsInStones(jewels, stones);
        System.out.println(result);
    }
}
