package com.codechen.leetcode.dualpointers;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-2 10:39
 * @description：移动零 https://leetcode.cn/problems/move-zeroes/description/
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * 输出: [0]
 * <p>
 * 提示:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            // 快慢相等值不变，慢针不动快针走
            // 快慢不等值改变，快针赋值给慢针，慢针向前一步走，快针向前一步走
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }

            fast++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
