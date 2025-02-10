package com.codechen.interview.junit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author：Java陈序员
 * @date：2025-2-10 17:03
 * @description：
 */
@SpringBootTest
public class MemberServiceTest {

    //==========第一组==========
    @Resource
    private MemberService memberService1;

    @Test
    void m1() {
        String result = memberService1.add(2);
        assertEquals("ok", result);

        System.out.println("----m1 over");
    }

    //==========第二组==========
    // 如果没有指定规则的话，统统返回默认值，对象为 null, 数字为零；制定了 mock 规则就按照规则走
    // Mock 注入实现原理：使用打桩(Stub)技术动态替换原来的程序
    @MockBean
    private MemberService memberService2;

    @Test
    void m2NotMockRule() {
        String result = memberService2.add(2);
        assertEquals("ok", result);

        System.out.println("----m2NotMockRule over");
    }

    @Test
    void m2WithMockRule() {
        // 不真的进入数据库/MQ，不落盘，改变return
        when(memberService2.add(3)).thenReturn("ok");

        String result = memberService2.add(3);
        assertEquals("ok", result);

        System.out.println("----m2WithMockRule over");
    }

    //==========第三组==========
    // @Resource 真实调用
    // @MockBean 如果没有指定规则的话，统统返回默认值，对象为 null, 数字为零；制定了 mock 规则就按照规则走
    @SpyBean //有规则按照规则走，没有规则走真实
    private MemberService memberService3;

    @Test
    void m3() {
        when(memberService3.add(2)).thenReturn("ok");
        String result = memberService3.add(2);
        System.out.println("----add result:  " + result);
        assertEquals("ok", result);

        int result2 = memberService3.del(3);
        System.out.println("----del result2:  " + result2);
        assertEquals(3, result2);

        System.out.println("----over");
    }
}