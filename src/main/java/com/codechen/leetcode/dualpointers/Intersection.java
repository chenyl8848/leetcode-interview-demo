package com.codechen.leetcode.dualpointers;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-4-3 9:29
 * @description：两个数组的交集 https://leetcode.cn/problems/intersection-of-two-arrays/description/
 * 给定两个数组 nums1 和 nums2 ，返回 它们的 交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 * <p>
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class Intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        // 1、先排序数组
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 2、双指针
        int pointer1 = 0;
        int pointer2 = 0;

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;

        while (pointer1 < nums1.length && pointer2 < nums2.length) {
            if (nums1[pointer1] == nums2[pointer2]) {
                if (index > 0 && nums1[pointer1] == result[index - 1]) {
                    pointer1++;
                    pointer2++;
                    continue;
                }
                result[index] = nums1[pointer1];
                index++;
                pointer1++;
                pointer2++;
            } else if (nums1[pointer1] < nums2[pointer2]) {
                pointer1++;
            } else {
                pointer2++;
            }
        }

        // 返回新数组，不然有可能多值
//        return result;
        return Arrays.copyOf(result, index);
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 2, 2, 1};
//        int[] nums2 = new int[]{2, 2};

        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};

        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }
}
