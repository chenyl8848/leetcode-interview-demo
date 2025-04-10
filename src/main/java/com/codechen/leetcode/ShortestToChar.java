package com.codechen.leetcode;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-10 10:28
 * @description：字符的最短距离 https://leetcode.cn/problems/shortest-distance-to-a-character/description/
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 * <p>
 * 示例 1：
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 * <p>
 * 示例 2：
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 */
public class ShortestToChar {

    public static int[] shortestToChar(String s, char c) {
        int[] nums = new int[s.length()];
        Arrays.fill(nums, s.length() + 1);

        for (int i = 0, j = -1; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                j = i;
            }

            if (j != -1) {
                nums[i] = i - j;
            }
        }

        for (int i = s.length() - 1, j = -1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                j = i;
            }

            if (j != -1) {
                nums[i] = Math.min(nums[i], j - i);
            }
        }

        return nums;
    }

    public static void main(String[] args) {

//        String s = "aaab";
//        char c = 'b';

        String s = "loveleetcode";
        char c = 'e';
        int[] nums = shortestToChar(s, c);
        System.out.println(Arrays.toString(nums));
    }
}
