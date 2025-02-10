package com.codechen.interview.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author：Java陈序员
 * @date：2025-2-10 16:04
 * @description： 从 Spring Boot 在 2.x 版本中已经使用了 Junit5 来作为单元测试的支持
 * <p>
 * 在 Junit5 中
 * @Before 和 @After 注解被 @BeforeEach和 @AfterEach 所替代
 * @BeforeClass 和 @AfterClass 注解被 @BeforeAll 和 @AfterAll 所替代
 */
public class JunitDemoTest {

    JunitDemo junitDemo = null;

    static StringBuffer stringBuffer = null;

    @BeforeAll
    static void m1() {
        stringBuffer = new StringBuffer("abc");
        System.out.println("===============: " + stringBuffer.length());
    }

    @AfterAll
    static void m2() {
        System.out.println("===============: " + stringBuffer.append(",end").toString());
    }

    // Junit4 的注解
    // @Before
    // Junit5 的注解
    @BeforeEach
    public void setUp() throws Exception {
        junitDemo = new JunitDemo();
    }

    // Junit4 的注解
    // @After
    // Junit5 的注解
    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("test finish.........");
    }

    @Test
    public void add() {
        int add = junitDemo.add(2, 2);
        // 不能使用人肉验证
        // System.out.println(add);

        // 绿色代表验证通过
        assertEquals(4, add);
        // 需要覆盖多种场景
        assertEquals(5, junitDemo.add(2, 3));
    }

    @Test
    public void subtract() {
    }
}