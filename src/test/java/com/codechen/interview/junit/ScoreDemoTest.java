package com.codechen.interview.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author：Java陈序员
 * @date：2025-2-10 16:07
 * @description：
 */
public class ScoreDemoTest {

    @Test
    public void scoreLevel() {

        ScoreDemo scoreDemo = new ScoreDemo();

        assertThrows(IllegalArgumentException.class, () -> scoreDemo.scoreLevel(-1));
        assertEquals("弱", scoreDemo.scoreLevel(50));
        assertEquals("差", scoreDemo.scoreLevel(65));
        assertEquals("中", scoreDemo.scoreLevel(75));
        assertEquals("良", scoreDemo.scoreLevel(85));
//        assertEquals("优", scoreDemo.scoreLevel(95));
    }
}