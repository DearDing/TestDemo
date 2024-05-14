package com.ding.kotlin.lock;

/**
 * final 修饰的类不可被继承
 */
public final class TestFinal {

    /**
     * 1、final 修饰的变量，只能被赋值一次；满足多线程可见性，线程安全。
     * 2、final 对象的引用初始化后就不能指向其他引用。但是该对象的值是可变的。
     * 3、static作用于成员变量用来表示只保存一份副本，而final的作用是用来保证变量不可变
     */
    private final String name = "哈哈";

}


