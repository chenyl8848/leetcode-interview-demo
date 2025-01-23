package com.codechen.interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author：Java陈序员
 * @date：2025-1-17 9:47
 * @description：遍历集合时 remove 或 add 操作注意事项
 */
public class IteratorRemoveBugDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
//
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Integer value = iterator.next();
//            if (value == 5) {
//                list.remove(value);
//            }
//        }

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value == 5) {
                iterator.remove();
            }
        }

//        for (Integer value : list) {
//            if (value == 5) {
//                list.remove(value);
//            }
//        }

//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i) == 5) {
//                list.remove(i);
//            }
//        }

        list.forEach(System.out::println);
    }
}
