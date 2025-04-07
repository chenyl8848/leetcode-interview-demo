package com.codechen.leetcode.dualpointers;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-7 15:07
 * @description：验证回文串 https://leetcode.cn/problems/valid-palindrome/description/
 * <p>
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。本题中，将空字符串定义为有效的 回文串 。
 * <p>
 * 示例 1：
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * <p>
 * 示例 2：
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * 提示：
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 */
public class IsPalindrome {

    public static boolean isPalindromeSelf(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        s = s.toLowerCase();

        char[] chars = new char[s.length()];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 48 && c <= 57) || (c >= 97) && (c <= 122)) {
                chars[index] = c;
                index++;
            }
        }

        chars = Arrays.copyOf(chars, index);

        if (index > 1) {

            int left = 0;
            int right = chars.length - 1;

            boolean flag = false;
            while (left != right && right > 0) {
                if (chars[left] != chars[right]) {
                    flag = false;
                    break;
                }

                left++;
                right--;
                flag = true;
            }

            return flag;
        } else {
            return true;
        }

    }

    public static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";
//        String s = " ";
//        String s = "aa";
        boolean palindrome = isPalindrome(s);
        System.out.println(palindrome);

    }
}
