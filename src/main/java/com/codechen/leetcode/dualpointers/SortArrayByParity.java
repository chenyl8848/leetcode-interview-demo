package com.codechen.leetcode.dualpointers;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-10 11:21
 * @description：按奇偶排序数组 https://leetcode.cn/problems/sort-array-by-parity/description/
 * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,2,4]
 * 输出：[2,4,3,1]
 * 解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
 * <p>
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[0]
 */
public class SortArrayByParity {

    public static int[] sortArrayByParity(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
            } else if (nums[right] % 2 == 1) {
                right--;
            } else {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 1, 2, 4};
        int[] nums = new int[]{4, 1, 3, 2, 4};
//        int[] nums = new int[]{0};
        int[] ints = sortArrayByParity(nums);
        System.out.println(Arrays.toString(ints));
    }
}
