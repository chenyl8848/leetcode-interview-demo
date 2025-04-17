package com.codechen.leetcode;

/**
 * @author：Java陈序员
 * @date：2025-4-17 10:13
 * @description：检查字符串是否为数组前缀 https://leetcode.cn/problems/check-if-string-is-a-prefix-of-array/description/
 * 给你一个字符串 s 和一个字符串数组 words ，请你判断 s 是否为 words 的 前缀字符串 。
 * 字符串 s 要成为 words 的 前缀字符串 ，需要满足：s 可以由 words 中的前 k（k 为 正数 ）个字符串按顺序相连得到，且 k 不超过 words.length 。
 * 如果 s 是 words 的 前缀字符串 ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：s = "iloveleetcode", words = ["i","love","leetcode","apples"]
 * 输出：true
 * 解释：
 * s 可以由 "i"、"love" 和 "leetcode" 相连得到。
 * <p>
 * 示例 2：
 * 输入：s = "iloveleetcode", words = ["apples","i","love","leetcode"]
 * 输出：false
 * 解释：
 * 数组的前缀相连无法得到 s 。
 */
public class IsPrefixString {

    public static boolean isPrefixString(String s, String[] words) {
        int k = 0;
        int n = s.length();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (k < n && word.charAt(j) == s.charAt(k)) {
                    k++;
                } else {
                    return false;
                }
            }

            if (k == n) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "iloveleetcode";
        String[] words = new String[]{"i","love","leetcode","apples"};

//        String s = "iloveleetcode";
//        String[] words = new String[]{"i", "love"};

//        String s = "iloveleetcode";
//        String[] words = new String[]{"apples","i","love","leetcode"};
        boolean prefixString = isPrefixString(s, words);
        System.out.println(prefixString);
    }

}
