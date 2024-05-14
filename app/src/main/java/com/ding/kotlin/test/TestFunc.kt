package com.ding.kotlin.test

/**
 * 正常函数写法：求和
 */
fun sumNumbers(num1: Int, num2: Int): Int {
    val result = num1 + num2
    println("sumNumbers--求和：$num1 + $num2 = $result")
    return result
}

/**
 * 定义匿名函数
 */
val sum = { num1: Int, num2: Int ->
    val result = num1 + num2
    println("sum--匿名函数 ： $num1 + $num2 = $result")
    result
}

/**
 * 匿名函数作为参数
 */
fun printSum(initValue: Int, sumMethod: (Int, Int) -> Int) {
    val result = sumMethod(initValue, 20)
    println("printSum--匿名函数结果： $result")
}

/**
 * 返回一个匿名函数
 */
fun printSumResult(): (Int, Int) -> Int {
    return { num1: Int, num2: Int ->
        val result = num1 + num2
        println("printSumResult-- result:$result")
        result
    }
}

fun main() {
    //调用匿名函数
    sum(10, 12)
    printSum(11) { num1: Int, num2: Int ->
        num1 + num2
    }
    //使用了 sumNumbers 函数的引用
    printSum(19, ::sumNumbers)

    //numSumMethod 是一个匿名函数
    val numSumMethod = printSumResult()
    printSum(15,numSumMethod)

}