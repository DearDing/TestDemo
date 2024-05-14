package com.ding.kotlin.lock;

import java.util.HashMap;

/**
 * volatile
 * 1、保证多个线程可见性（每次都重新读取）
 * 2、防止指令重排序（虚拟机级别的优化，会对指令重排序，但不影响最后结果，比如 x=1 ,b=2, c=3,三个赋值操作可能并不是按顺序执行，有可能c=3先执行）
 * 注意：并不能保证线程安全；在多线程里，有可能其他线程先赋值，但是当前线程还是旧值，最后通过旧值计算，同步错误的结果到主内存里。
 */
public class TestVolatile {

    private volatile static TestVolatile instance1;

    public static TestVolatile getInstance1(){
        if(null == instance1){
            synchronized (TestVolatile.class){
                if(null == instance1){
                    instance1 = new TestVolatile();
                }
            }
        }
        return instance1;
    }


    public static TestVolatile getInstance2(){
        return CreatorSelfClass.instance2;
    }

    private static class CreatorSelfClass{
        private static final TestVolatile instance2 = new TestVolatile();
    }
}

