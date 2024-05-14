package com.ding.kotlin.test

/**
 * 单例
 * 对象声明的初始化过程是线程安全的并且在首次访问时进行。
 */
object TestObject {
    private var name: String = "还没有名字！"

    fun setName(txt: String): TestObject {
        name = txt
        return this
    }

    fun sayName() {
        println("我的名字叫$name")
    }
}

fun main() {
    TestObject.setName("小明").sayName()
}