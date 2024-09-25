package cn.wh.study.chapter_02;

import org.junit.Assert;
import org.junit.Test;

public class MyMathTest {

    @Test
    public void sin() {
        Assert.assertEquals(0.5, MyMath.sin(Math.PI / 6), 0.00001);
    }
}