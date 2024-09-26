package cn.wh.study.chapter_03;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_03.HamcrestTest
 * @date 2024-09-26 23:45
 * @description todo
 */
public class HamcrestTest {

    private List<String> values;

    @Before
    public void setUpList() {
        values = new ArrayList<>();
        values.add("x");
        values.add("y");
        values.add("z");
    }

    @Test
    public void testWithoutHamcrest() {
        Assert.assertTrue(
                values.contains("x")
                ||values.contains("y")
                ||values.contains("z")
        );
    }

    @Test
    public void testWithHamcrest() {
        Assert.assertThat(values,
                JUnitMatchers.hasItem(
                        CoreMatchers.anyOf(
                                CoreMatchers.equalTo("x"),
                                CoreMatchers.equalTo("y"),
                                CoreMatchers.equalTo("z")
                        )
                )
        );
    }
}
