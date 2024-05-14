package com.ding.kotlin.test

/**
 * 扩展函数
 */
fun String.replaceNull(): String {
    return this.replace("null", "")
}

/**
 * 可空扩展函数
 */
fun String?.replaceUnknow(): String {
    return this?.replace("unknow", "") ?: "当前字符串为空！"
}

open class TestExtend {

    companion object{
        fun printlnCompanion(){
            println("我是伴生对象里的方法")
        }
    }

    var name: String = "我是TestExtend类"
        set(value) {
            field = "我是处理后的结果:$value"
        }
}

/**
 * val TestExtend.result = "1" // 错误：扩展属性不能有初始化器,只能通过显式的 getter 和 settter 定义
 * set方法里不能直接调用field
 */
val TestExtend.result:String
    get() = "我是扩展属性"

/**
 * 伴生对象扩展函数
 */
fun TestExtend.Companion.printlnOther(){
    println("我是伴生对象扩展函数")
}

fun <T> T.toSayHi(){
    println("$this say hello world")
}

fun main() {
    //扩展函数
    val str1 = "hello world null"
    val result1 = str1.replaceNull()
    println(result1)

    //可空扩展函数
    val str2: String? = null
    println(str2.replaceUnknow())

    //扩展属性
    val obj = TestExtend()
    println(obj.result)

    //伴生对象扩展函数
    TestExtend.printlnOther()

    //泛型扩展函数
    obj.toSayHi()
}