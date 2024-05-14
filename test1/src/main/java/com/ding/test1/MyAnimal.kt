package com.ding.test1

/**
 * sealed 是抽象的,无法直接实例化
 * java字节码：public abstract class MyAnimal {}
 */
sealed class MyAnimal {
    abstract fun sayName()
}

sealed interface Dog {
    fun catHi()
}

sealed class MyDog : MyAnimal(), Dog {

    private val dogName = "牧羊犬"

    override fun catHi() {
        println("$dogName 打招呼")
    }

    override fun sayName() {
        println(dogName)
    }
}