package com.codechen.leetcode.dualpointers;

import java.math.BigDecimal;

/**
 * @author：Java陈序员
 * @date：2025-4-28 10:36
 * @description：字符串相加 https://leetcode.cn/problems/add-strings/solutions/357938/zi-fu-chuan-xiang-jia-by-leetcode-solution/
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * <p>
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * <p>
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 */
public class AddStrings {

    public static String addStrings1(String num1, String num2) {
        return new BigDecimal(num1).add(new BigDecimal(num2)).toString();
    }

    public static String addStrings(String num1, String num2) {
        // 使用竖式计算的方式，从最后一位开始相加
        int first = num1.length() - 1;
        int second = num2.length() - 1;

        // 用于统计是否相加超过 10
        int add = 0;

        // 用于计算结果
        StringBuilder result = new StringBuilder();

        while (first >= 0 || second >= 0 || add != 0) {
            int firstValue = 0;
            int secondValue = 0;

            if (first >= 0) {
                // 说明不是最高位，如是最高位，取 0
                firstValue = num1.charAt(first) - '0';
            }

            if (second >= 0) {
                secondValue = num2.charAt(second) - '0';
            }

            // 计算两位之和，并加上上一位的高位
            int sum = firstValue + secondValue + add;

            result.append(sum % 10);
            add = sum / 10;

            first--;
            second--;
        }

        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "11", num2 = "123";
//        String num1 = "456", num2 = "77";
//        String num1 = "0", num2 = "0";
        String result = addStrings(num1, num2);
        System.out.println(result);
    }
}
