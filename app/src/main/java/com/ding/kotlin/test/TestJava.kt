@file:JvmName("TestPrinter")

package com.ding.kotlin.test

import com.ding.kotlin.lock.TestKotlin
import java.io.IOException

class TestJava {

    companion object {

        @JvmField
        var secondFood: String = "午餐"

        var dinnerFood: String = "晚餐"

        fun eatLunch() {
            println("吃午餐")
        }

        @JvmStatic
        fun eatDinner() {
            println("吃晚餐")
        }
    }

    val food: String = "米饭"

    @JvmField
    val fruit: String = "苹果"

    @JvmOverloads
    fun eatFood(first: String = "面条", second: String = "鸡肉") {
        println("先吃 $first ,然后在吃 $second")
    }

    fun drinkWarter(first: String = "牛奶", second: String = "热水") {
        println("先喝 $first ,然后在喝 $second")
    }

    /**
     * 可以捕获java方法抛出的异常
     */
    fun catchJavaException(){
        val obj = TestKotlin()
        try {
            obj.testThrowException()
        }catch (e:IOException){
            println("捕获到了java方法抛出的IOException")
        }
    }

    /**
     * 添加 @Throws 注解，java 调用该方法才能正确捕获IOException
     */
    @Throws(IOException::class)
    fun testThrowException(){
        throw IOException()
    }

    /**
     * 匿名函数对象
     */
    val translater = { text:String->
        text.toLowerCase().capitalize()
    }
}

fun printStr(str: String) {
    println(str)
}

fun main(){
    val obj = TestJava()
    obj.catchJavaException()
}