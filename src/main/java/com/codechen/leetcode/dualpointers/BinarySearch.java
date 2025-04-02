package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-2 9:39
 * @description：二分查找 https://leetcode.cn/problems/binary-search/description/
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class BinarySearch {

    public static int forSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle = (left + right) / 2;
        while (left <= right) {
            if (nums[middle] == target) {
                // 数组中找到目标值，直接返回下标
                return middle;
            } else if (nums[middle] < target) {
                // 结果比目标，小了要变大，左针右移+
                left = middle + 1;
            } else {
                // 结果比目标，大了要变小，右针左移-
                right = middle - 1;
            }

            middle = (left + right) / 2;
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
//        int target = 9;
////        int i = forSearch(nums, target);
//        int i = binarySearch(nums, target);
//        System.out.println(i);
//
//        int[] nums2 = new int[]{-1, 0, 3, 5, 9, 12};
//        int target2 = 2;
//        int i2 = binarySearch(nums2, target2);
//        System.out.println(i2);

        int[] nums3 = new int[]{5};
        int target3 = 5;
        int i3 = binarySearch(nums3, target3);
        System.out.println(i3);
    }
}
