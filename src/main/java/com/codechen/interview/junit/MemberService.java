package com.codechen.interview.junit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author：Java陈序员
 * @date：2025-2-10 17:02
 * @description：
 */
@Service
@Slf4j
public class MemberService {

    public String add(Integer id) {
        log.info("---come in addUser");

        if (id == -1) throw new IllegalArgumentException("parameter is negative。。。。");

        return "ok";
    }

    public int del(Integer uid) {
        log.info("---come in del");

        return uid;
    }
}
