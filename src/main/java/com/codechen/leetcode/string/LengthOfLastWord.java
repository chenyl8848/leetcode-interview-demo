package com.codechen.leetcode.string;

/**
 * @author：Java陈序员
 * @date：2025-5-7 10:16
 * @description：最后一个单词的长度 https://leetcode.cn/problems/length-of-last-word/description/
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为 5。
 * <p>
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为 4。
 * <p>
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为 6 的“joyboy”。
 */
public class LengthOfLastWord {

    public static int lengthOfLastWord1(String s) {
        s = s.trim();
        int count = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) != ' ') {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    public static int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }

        int count = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            count++;
            index--;
        }

        return count;
    }

    public static void main(String[] args) {
//        String s = "Hello World";
//        String s = "   fly me   to   the moon  ";
        String s = "luffy is still joyboy";
        int lengthOfLastWord = lengthOfLastWord(s);
        System.out.println(lengthOfLastWord);
    }
}
