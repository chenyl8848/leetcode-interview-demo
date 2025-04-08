package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-8 9:24
 * @description：仅仅反转字母 https://leetcode.cn/problems/reverse-only-letters/description/
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * 示例 1：
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * <p>
 * 示例 2：
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * <p>
 * 示例 3：
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 */
public class ReverseOnlyLetters {

    public static String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char leftChar = chars[left];
            char rightChar = chars[right];

            if (Character.isLetter(leftChar) && Character.isLetter(rightChar)) {
                chars[left] = rightChar;
                chars[right] = leftChar;
                left++;
                right--;
            }

            if (!Character.isLetter(leftChar)) {
                left++;
            }

            if (!Character.isLetter(rightChar)) {
                right--;
            }

        }

        return new String(chars);
    }

    public static void main(String[] args) {
//        String s = "ab-cd";
//        String s = "a-bC-dEf-ghIj";
        String s = "Test1ng-Leet=code-Q!";
        String result = reverseOnlyLetters(s);
        System.out.println(result);
    }
}
