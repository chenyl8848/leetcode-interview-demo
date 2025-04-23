package com.codechen.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：Java陈序员
 * @date：2025-4-23 9:52
 * @description：只出现一次的数字 https://leetcode.cn/problems/single-number/description/
 * <p>
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * <p>
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 * <p>
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 * <p>
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 */
public class SingleNumber {

    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
            System.out.println(ans);
        }
        return ans;
    }

    public static int singleNumber2(int[] nums) {
        Arrays.sort(nums);

        int slow = 0;
        int fast = 1;

        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                return nums[slow];
            } else {
                slow += 2;
                fast += 2;
            }
        }

        return nums[nums.length - 1];
    }

    public static int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        for (Integer key : map.keySet()) {
            Integer count = map.get(key);
            if (count == 1) {
                return key;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 2, 1};
        int[] nums = {4, 1, 2, 1, 2};
//        int[] nums = {1};

        int singleNumber = singleNumber(nums);
        System.out.println(singleNumber);
    }
}
