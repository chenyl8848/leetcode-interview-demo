package com.codechen.leetcode;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-30 9:58
 * @description：“气球” 的最大数量 https://leetcode.cn/problems/maximum-number-of-balloons/description/
 * 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 * <p>
 * 示例 1：
 * 输入：text = "nlaebolko"
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：text = "loonbalxballpoon"
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：text = "leetcode"
 * 输出：0
 */
public class MaxNumberOfBalloons {

    public static int maxNumberOfBalloons(String text) {
        // balloon
        // 字母 - 坐标 - 所需个数
        // a - 0 - 1
        // b - 1 - 1
        // l - 2 - 2
        // n - 3 - 1
        // o - 4 - 2
        int[] nums = new int[5];

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == 'a') {
                nums[0]++;
            } else if (ch == 'b') {
                nums[1]++;
            } else if (ch == 'l') {
                nums[2]++;
            } else if (ch == 'n') {
                nums[3]++;
            } else if (ch == 'o') {
                nums[4]++;
            }
        }

        // 因为 l、o 两个字母需要出现两次，所以需要 / 2
        nums[2] = nums[2] / 2;
        nums[4] = nums[4] / 2;

        // 最后计算数组中的最小值就是能组建的单词个数
        return Arrays.stream(nums).min().getAsInt();
    }

    public static void main(String[] args) {
//        String text = "nlaebolko";
//        String text = "loonbalxballpoon";
        String text = "leetcode";
        int number = maxNumberOfBalloons(text);
        System.out.println(number);
    }
}
