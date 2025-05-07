package com.codechen.leetcode.string;

/**
 * @author：Java陈序员
 * @date：2025-4-25 9:59
 * @description：找不同 https://leetcode.cn/problems/find-the-difference/description/
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * <p>
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * <p>
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 */
public class FindTheDifference {

    public static char findTheDifference(String s, String t) {
       int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;

            if (count[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }
        }

        return ' ';
    }

    /**
     * 两个字符串的字符都加起来，再相减，差值就是多余的字符
     * @param s
     * @param t
     * @return
     */
    public static char findTheDifference1(String s, String t) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < s.length(); i++) {
            count1 += s.charAt(i);
        }

        for (int i = 0; i < t.length(); i++) {
            count2 += t.charAt(i);
        }

        return (char) (count2 - count1);
    }

    public static void main(String[] args) {
//        String s = "abcd", t = "abcde";
        String s = "abcd", t = "abecd";
//        String s = "", t = "y";
        char theDifference = findTheDifference(s, t);
        System.out.println(theDifference);
    }
}
