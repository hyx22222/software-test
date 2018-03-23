package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 咔咔吃萝卜 on 2018/3/22.
 */
public class Test2Test {

    private Test2 test2;
    @Before
    public void setUp() throws Exception {
        test2 = new Test2();
    }

    @Test
    public void sayHello() throws Exception {
        assertEquals("no",test2.sayHello(2,3,5));
        assertEquals("no",test2.sayHello(-2,3,5));
        assertEquals("等腰",test2.sayHello(3,3,5));
        assertEquals("等边",test2.sayHello(3,3,3));
        assertEquals("普通",test2.sayHello(6,3,5));
    }

}