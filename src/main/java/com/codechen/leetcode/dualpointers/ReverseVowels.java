package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-25 9:41
 * @description：反转字符串中的元音字母 https://leetcode.cn/problems/reverse-vowels-of-a-string/description/
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
 * <p>
 * 示例 1：
 * 输入：s = "IceCreAm"
 * 输出："AceCreIm"
 * 解释：
 * s 中的元音是 ['I', 'e', 'e', 'A']。反转这些元音，s 变为 "AceCreIm".
 * <p>
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："leotcede"
 */
public class ReverseVowels {

    public static String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;

        StringBuilder result = new StringBuilder(s);

        while (left < right) {
            char leftChar = result.charAt(left);
            char rightChar = result.charAt(right);

            if (isVowel(leftChar) && isVowel(rightChar)) {
                result.setCharAt(left, rightChar);
                result.setCharAt(right, leftChar);
                left++;
                right--;
            } else if (isVowel(leftChar) && !isVowel(rightChar)) {
                right--;
            } else if (isVowel(rightChar) && !isVowel(leftChar)) {
                left++;
            } else {
                left++;
                right--;
            }
        }

        return result.toString();
    }

    public static Boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);

        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {

        String s = "IceCreAm";
//        String s = "leetcode";
        String reverseVowels = reverseVowels(s);
        System.out.println(reverseVowels);
    }
}
