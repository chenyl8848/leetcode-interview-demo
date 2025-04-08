package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-8 9:37
 * @description：平方数之和 https://leetcode.cn/problems/sum-of-square-numbers/description/
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a*a + b*b = c
 * <p>
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * <p>
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 */

public class JudgeSquareSum {

    public static boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);

        while (left <= right) {
            long result = left * left + right * right;

            if (result == c) {
                return true;
            } else if (result < c) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int n = 1000;
//        int n = 8;
//        int n = 6;
//        int n = 5;
//        int n = 3;
        boolean b = judgeSquareSum(n);
        System.out.println(b);

    }
}
