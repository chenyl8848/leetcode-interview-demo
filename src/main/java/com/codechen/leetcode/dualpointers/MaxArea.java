package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-7 16:00
 * @description：盛最多水的容器 https://leetcode.cn/problems/container-with-most-water/description/
 * <p>
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 */
public class MaxArea {

    public static int maxArea1(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int volume = 0;
        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;
            volume = Math.max(volume, area);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }

        }

        return volume;
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int area = 0;
        while (left < right) {
            int temp = 0;
            if (height[left] < height[right]) {
                temp = height[left] * (right - left);
                left++;
            } else {
                temp = height[right] * (right - left);
                right--;
            }

            if (area < temp) {
                area = temp;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] height = new int[]{1, 1};
        int area = maxArea(height);
        System.out.println(area);
    }
    }
