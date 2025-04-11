package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-11 11:26
 * @description：最小公共值 https://leetcode.cn/problems/minimum-common-value/description/
 * 给你两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数 。
 * 如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1 。
 * 如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3], nums2 = [2,4]
 * 输出：2
 * 解释：两个数组的最小公共元素是 2 ，所以我们返回 2 。
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2,3,6], nums2 = [2,3,4,5]
 * 输出：2
 * 解释：两个数组中的公共元素是 2 和 3 ，2 是较小值，所以返回 2 。
 */
public class GetCommon {

    public static int getCommon(int[] nums1, int[] nums2) {
        int slow = 0;
        int fast = 0;

        while (slow < nums1.length && fast < nums2.length) {
            if (nums1[slow] == nums2[fast]) {
                return nums1[slow];
            } else if (nums1[slow] < nums2[fast]) {
                slow++;
            } else {
                fast++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 2, 3};
//        int[] nums2 = new int[]{2, 4};

//        int[] nums1 = new int[]{1, 2, 3, 6};
//        int[] nums2 = new int[]{2, 3, 4, 5};

        int[] nums1 = new int[]{1, 2, 3, 6};
        int[] nums2 = new int[]{4, 5};

        int common = getCommon(nums1, nums2);
        System.out.println(common);
    }
}
