package cn.wh.study.chapter_02;

import cn.wh.study.chapter_01.Calculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Author 王恒
 * @ClassName cn.wh.study.chapter_02.ParameterizedTest
 * @Date 2024-09-26 01:14
 * @Description 参数化测试
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    private final double expected;
    private final double valueOne;
    private final double valueTwo;

    /**
     * Parameterized 要求提供有参构造器，方便每个用例调用此构造器构造测试用例。
     * @param expected 期望值
     * @param valueOne 第一个参数
     * @param valueTwo 第二个参数
     */
    public ParameterizedTest(double expected, double valueOne, double valueTwo) {
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    @Parameterized.Parameters
    public static Collection<Integer[]> getTestParameters() {
        return Arrays.asList(new Integer[][]{
                {3, 1, 2}, //每个Object[]代表一个测试用例,调用的是有参构造器。
                {5, 2, 3},
                {7, 3, 4}
        });
    }

    /**
     * 有多少个用例，就实际执行了多少次。
     */
    @Test
    public void testAdd() {
        Calculator cal = new Calculator();
        Assert.assertEquals(expected, cal.add(valueOne, valueTwo), 0);
    }
}
