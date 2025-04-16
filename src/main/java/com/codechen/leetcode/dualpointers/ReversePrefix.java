package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-16 9:45
 * @description：反转单词前缀 https://leetcode.cn/problems/reverse-prefix-of-word/description/
 * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
 * 返回 结果字符串 。
 * <p>
 * 示例 1：
 * 输入：word = "abcdefd", ch = "d"
 * 输出："dcbaefd"
 * 解释："d" 第一次出现在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "dcbaefd" 。
 * <p>
 * 示例 2：
 * 输入：word = "xyxzxe", ch = "z"
 * 输出："zxyxxe"
 * 解释："z" 第一次也是唯一一次出现是在下标 3 。
 * 反转从下标 0 到下标 3（含下标 3）的这段字符，结果字符串是 "zxyxxe" 。
 * <p>
 * 示例 3：
 * 输入：word = "abcd", ch = "z"
 * 输出："abcd"
 * 解释："z" 不存在于 word 中。
 * 无需执行反转操作，结果字符串是 "abcd" 。
 */
public class ReversePrefix {

    public static String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index < 0) {
            return word;
        }

        int left = 0;
        int right = index;

        StringBuilder stringBuilder = new StringBuilder(word);
        while (left < right) {
            char leftChar = stringBuilder.charAt(left);
            stringBuilder.setCharAt(left, stringBuilder.charAt(right));
            stringBuilder.setCharAt(right, leftChar);
            left++;
            right--;
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        String word = "abcdefd";
//        char ch = 'd';

//        String word = "xyxzxe";
//        char ch = 'z';

        String word = "abcd";
        char ch = 'z';

        String reversePrefix = reversePrefix(word, ch);
        System.out.println(reversePrefix);
    }
}
