package com.codechen.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：Java陈序员
 * @date：2025-5-6 15:11
 * @description：字符串中的第一个唯一字符 https://leetcode.cn/problems/first-unique-character-in-a-string/description/
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: 0
 * <p>
 * 示例 2:
 * 输入: s = "loveleetcode"
 * 输出: 2
 * <p>
 * 示例 3:
 * 输入: s = "aabb"
 * 输出: -1
 */
public class FirstUniqChar {

    public static int firstUniqChar1(String s) {

        for (int i = 0; i < s.length(); i++) {
            boolean flag = false;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && i != j) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                return i;
            }

        }

        return -1;

    }

    public static int firstUniqChar2(String s) {
       int[] nums = new int[26];

        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (nums[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static int firstUniqChar(String s) {

        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i) , 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//       String s = "leetcode";
        String s = "loveleetcode";
//        String s = "aabb";
        int firstUniqChar = firstUniqChar(s);
        System.out.println(firstUniqChar);
    }
}
