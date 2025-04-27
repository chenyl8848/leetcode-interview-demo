package com.codechen.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：Java陈序员
 * @date：2025-4-27 14:56
 * @description：丢失的数字 https://leetcode.cn/problems/missing-number/description/
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * 示例 1：
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * 示例 3：
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 */
public class MissingNumber {

    public static int missingNumber1(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == nums[j]) {
                    break;
                }

                if (j == nums.length - 1 && i != nums[j]) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int missingNumber2(int[] nums) {
        // [9,6,4,2,3,5,7,0,1]
        Arrays.sort(nums);
        // [0,1,2,3,4,5,6,7,9]

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    public static int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return -1;

    }

    public static int missingNumber4(int[] nums) {

        int n = nums.length;
        // 使用高斯公式计算 0 1 2 3 4 ... 总和
        int total = n * (n + 1) / 2;
        int result = 0;

        // 计算数组总和
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }

        // 两个数相减就是缺少的值
        return total - result;

    }

    public static int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }

    public static void main(String[] args) {

//        int[] nums = {3, 0, 1};
        int[] nums = {0, 1};
//        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int missingNumber = missingNumber(nums);
        System.out.println(missingNumber);

    }
}
