package com.codechen.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author：Java陈序员
 * @date：2025-4-29 10:37
 * @description：查找共用字符 https://leetcode.cn/problems/find-common-characters/description/
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 * <p>
 * 示例 2：
 * 输入：words = ["cool","lock","cook"]
 * 输出：["c","o"]
 */
public class CommonChars {

    public static List<String> commonChars(String[] words) {

        List<String> result = new ArrayList<>();
        int[] chars = new int[26];
        Arrays.fill(chars, Integer.MAX_VALUE);

        for (String word : words) {
            int[] temp = new int[26];
            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 'a']++;
            }

            for (int i = 0; i < temp.length; i++) {
                chars[i] = Math.min(chars[i], temp[i]);
            }
        }

        System.out.println(Arrays.toString(chars));

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i]; j++) {
                result.add(String.valueOf((char)(i + 'a')));
            }
        }

        return result;
    }

    public static void main(String[] args) {
       String[] words = {"bella","label","roller"};
        List<String> list = commonChars(words);
        System.out.println(list);
    }
}
