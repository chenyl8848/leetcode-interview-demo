package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-14 14:19
 * @description：交替合并字符串 https://leetcode.cn/problems/merge-strings-alternately/description/
 * <p>
 * 给你两个字符串 word1 和 word2 。请你从 word1 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。
 * 返回 合并后的字符串 。
 * <p>
 * 示例 1：
 * 输入：word1 = "abc", word2 = "pqr"
 * 输出："apbqcr"
 * 解释：字符串合并情况如下所示：
 * word1：  a   b   c
 * word2：    p   q   r
 * 合并后：  a p b q c r
 * <p>
 * 示例 2：
 * 输入：word1 = "ab", word2 = "pqrs"
 * 输出："apbqrs"
 * 解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
 * word1：  a   b
 * word2：    p   q   r   s
 * 合并后：  a p b q   r   s
 * <p>
 * 示例 3：
 * 输入：word1 = "abcd", word2 = "pq"
 * 输出："apbqcd"
 * 解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
 * word1：  a   b   c   d
 * word2：    p   q
 * 合并后：  a p b q c   d
 */
public class MergeAlternately {

    public static String mergeAlternately1(String word1, String word2) {
        int fast = 0;
        int slow = 0;
        StringBuilder result = new StringBuilder();

        if (word1.length() < word2.length()) {
            while (fast < word1.length()) {
                result.append(word1.charAt(fast));
                result.append(word2.charAt(slow));
                fast++;
                slow++;
            }

            result.append(word2.substring(word1.length()));
        } else {
            while (slow < word2.length()) {
                result.append(word1.charAt(fast));
                result.append(word2.charAt(slow));
                fast++;
                slow++;
            }

            result.append(word1.substring(word2.length()));
        }

       return result.toString();
    }

    public static String mergeAlternately(String word1, String word2) {
        int fast = 0;
        int slow = 0;

        StringBuilder result = new StringBuilder();

        while (fast < word1.length() || slow < word2.length()) {
            if (fast < word1.length()) {
                result.append(word1.charAt(fast));
                fast++;
            }

            if (slow < word2.length()) {
                result.append(word2.charAt(slow));
                slow++;
            }
        }

       return result.toString();
    }

    public static void main(String[] args) {
//        String word1 = "abc", word2 = "pqr";
//        String word1 = "ab", word2 = "pqrs";
        String word1 = "abcd", word2 = "pq";

        String mergeAlternately = mergeAlternately1(word1, word2);
        System.out.println(mergeAlternately);
    }
}
