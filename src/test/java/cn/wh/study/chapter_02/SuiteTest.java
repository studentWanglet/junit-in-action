package cn.wh.study.chapter_02;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Author 王恒
 * @ClassName cn.wh.study.chapter_02.SuiteTest
 * @Date 2024-09-26 01:34
 * @Description 单元测试中的测试集构建
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MyMathTest.class, ParameterizedTest.class})
public class SuiteTest {
}
