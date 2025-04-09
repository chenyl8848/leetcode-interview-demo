package com.codechen.leetcode.dualpointers;

/**
 * @author：Java陈序员
 * @date：2025-4-9 11:36
 * @description：长按键入 https://leetcode.cn/problems/long-pressed-name/description/
 * <p>
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * <p>
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 */
public class IsLongPressedName {

    public static boolean isLongPressedName(String name, String typed) {
        int pointName = 0;
        int pointTyped = 0;

        while (pointTyped < typed.length()) {
            if (pointName < name.length() && name.charAt(pointName) == typed.charAt(pointTyped)) {
                pointName++;
                pointTyped++;
            } else if (pointTyped > 0 && typed.charAt(pointTyped) == typed.charAt(pointTyped - 1)) {
                pointTyped++;
            } else {
                return false;
            }
        }

        return pointName == name.length();
    }

    public static void main(String[] args) {
        String name = "alex";
        String typed = "aaleex";

//        String name = "saeed";
//        String typed = "ssaaedd";

        boolean longPressedName = isLongPressedName(name, typed);
        System.out.println(longPressedName);
    }
}
