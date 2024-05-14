package com.ding.kotlin.test

/**
 * openrator  运算符重载 关键字
 */
class TestOperator(val num:Int) {

    fun plus1(x: Int): Int {
        return x + num
    }

    /**
     * 加法
     * 方法名只能是 plus
     */
    operator fun plus(x: Int): Int {
        return x + num
    }

    /**
     * 加法
     */
    operator fun plus(value: String): String {
        return "$value + $num"
    }

    /**
     * 减法
     */
    operator fun minus(value: Int): Int {
        return value - num
    }

    /**
     * 乘法
     */
    operator fun times(value:Int):Int{
        return value.times(num)
    }

    /**
     * 除法
     */
    operator fun div(value:Int):Int{
        return value.div(num)
    }

    /**
     * 比较
     */
    operator fun compareTo(value:Int):Int{
        val result = value.compareTo(num)
        println("compareTo = $result")
        return result
    }
}

fun main() {
    val clz = TestOperator(10)
    println("普通方法： ${clz.plus1(1)}")
    println("重载加法 result(Int) =  ${clz + 1}")
    println("重载加法 result(String) = ${clz + "1"}")
    println("重载减法 result(Int) =  ${clz - 1}")
    println("重载乘法 result(Int) =  ${clz * 2}")
    println("重载除法 result(Int) =  ${clz / 2}")
    println("重载比较 result(Int) =  ${clz > 2}")
}