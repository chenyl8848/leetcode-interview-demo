package com.codechen.leetcode.simple;

import java.util.Arrays;

/**
 * @author：Java陈序员
 * @date：2025-1-16 15:21
 * @description：合并两个有序数组
 */
public class MergeSortArray {

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     * 示例 2：
     * <p>
     * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
     * 输出：[1]
     * 解释：需要合并 [1] 和 [] 。
     * 合并结果是 [1] 。
     * 示例 3：
     * <p>
     * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
     * 输出：[1]
     * 解释：需要合并的数组是 [] 和 [1] 。
     * 合并结果是 [1] 。
     * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        while (m > 0 && n > 0) {
            if (nums1[m - 1] < nums2[n - 1]) {
                nums1[m + n - 1] = nums2[n - 1];
                --n;
            } else {
                nums1[m + n - 1] = nums1[m - 1];
                --m;
            }
        }

        if (m == 0 && n > 0) {
            for (int i = 0; i < n; ++i) {
                nums1[i] = nums2[i];
            }
        }

    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                nums1[m + i] = nums2[i];
            }
        }

        Arrays.sort(nums1);
    }

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;

        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }

            sorted[p1 + p2 - 1] = cur;

        }

        for (int i = 0; i < sorted.length; i++) {
            nums1[i] = sorted[i];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{3, 5, 6};

//        merge(nums1, 3, nums2, 3);
//        merge2(nums1, 3, nums2, 3);
        merge3(nums1, 3, nums2, 3);

//        int[] nums1 = new int[]{0};
//        int[] nums2 = new int[]{1};
//
//        merge(nums1, 0, nums2, 1);

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i]);
            System.out.print(",");
        }
    }
}
