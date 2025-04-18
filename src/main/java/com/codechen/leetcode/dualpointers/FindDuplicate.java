package com.codechen.leetcode.dualpointers;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-18 9:06
 * @description：寻找重复数 https://leetcode.cn/problems/find-the-duplicate-number/description/
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * <p>
 * 示例 3 :
 * 输入：nums = [3,3,3,3,3]
 * 输出：3
 */
public class FindDuplicate {

    public static int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int pre1 = 0;
        int pre2 = slow;

        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }

        return pre1;
    }

    public static int findDuplicate2(int[] nums) {
        int n = nums.length;
        int left = 1;
        int right = n - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }

            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = mid;
            }
        }

        return result;
    }

    public static int findDuplicate1(int[] nums) {
        Arrays.sort(nums);

        int slow = 0;
        int fast = 1;

        while (slow < nums.length && fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                return nums[slow];
            } else {
                slow++;
                fast++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        int duplicate = findDuplicate(nums);
        System.out.println(duplicate);
    }
}
