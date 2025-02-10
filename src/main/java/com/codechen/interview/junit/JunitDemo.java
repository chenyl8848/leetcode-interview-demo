package com.codechen.interview.junit;

/**
 * @author：Java陈序员
 * @date：2025-2-10 15:53
 * @description：单元测试
 */
public class JunitDemo {

    @CustomTest
    public int add(int x, int y) {
        return x + y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    @CustomTest
    public int divide(int x, int y) {
        return x / y;
    }
}
