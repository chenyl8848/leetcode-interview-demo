package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-16 10:10
 * @description：找出字符串钟第一个匹配项的下标 https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * <p>
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 */
public class StrStr {

    public static int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int a = i, b = 0;
            while (b < needle.length() && haystack.charAt(a) == needle.charAt(b)) {
                a++;
                b++;
            }

            if (b == needle.length()) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

//        String haystack = "sadbutsad", needle = "sad";
//        String haystack = "leetcode", needle = "leeto";
        String haystack = "a", needle = "a";

        int index = strStr(haystack, needle);
        System.out.println(index);

    }
}
