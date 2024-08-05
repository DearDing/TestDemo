package com.ding.test1;

/**
 * 接口编译后也会生成 .class 文件；
 * 使用 Class 类创建对象的时候会检查是否是接口、数组、抽象类，如果是，会报异常
 */
public interface ITest {
    void jumpTest(String url);
    void jumpSelfPage();
    void create(Class<Integer> clz);
}
