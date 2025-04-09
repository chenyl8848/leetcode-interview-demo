package com.codechen.leetcode;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-9 9:53
 * @description：轮转数组 https://leetcode.cn/problems/rotate-array/description/
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 */
public class RotateArray {

    public static void rotate1(int[] nums, int k) {
        int length = nums.length;
        int[] newNums = new int[length];

        for (int i = 0; i < length; i++) {
            newNums[(i + k) % length] = nums[i];
        }

//        System.arraycopy(newNums, 0, newNums, 0, length);
        for (int i = 0; i < newNums.length; i++) {
            nums[i] = newNums[i];
        }

    }

    public static void rotate(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k % nums.length - 1);
        reverse(nums, k % nums.length, nums.length - 1);
    }

    /**
     * 翻转数组
     *
     * @param nums
     * @param start
     * @param end
     */
    public static void reverse(int nums[], int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        int[] nums = new int[]{-1};
        int[] nums = new int[]{1, 2};
        rotate(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
