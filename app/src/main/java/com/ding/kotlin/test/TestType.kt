package com.ding.kotlin.test

/**
 * kotlin 数据类型
 */
class TestType {

    val numberValue = 100_100_100

    fun isEqual(number1: Int, number2: Int): Boolean {
        return number1 == number2
    }

    fun testNumber0() {
        val isEqual = isEqual(100_00, 10000)
        println(isEqual)
        println("----- testNumber0 --------- end")
    }

    /**
     * 由于 JVM 对 -128 到 127 的整数（Integer）应用了内存优化，因此，a 的所有可空引用实际上都是同一对象。
     * 但是没有对 b 应用内存优化，所以它们是不同对象。
     */
    fun testNumber1() {
        val a: Int = 127
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        val c: Int = -128
        val boxedC: Int? = c
        val anotherBoxedC: Int? = c

        val b: Int = 128
        val boxedB: Int? = b
        val anotherBoxedB: Int? = b

        val d: Int = -129
        val boxedD: Int? = d
        val anotherBoxedD: Int? = d

        println(boxedA === anotherBoxedA) // true
        println(boxedC === anotherBoxedC) // true
        println(boxedB === anotherBoxedB) // false
        println(boxedD === anotherBoxedD) // false

        println("----- testNumber1 --------- end")
    }

    fun testNumber2() {
        val b: Int = 10000
        //println(b == b) // 输出“true”
        val boxedB: Int? = b
        val anotherBoxedB: Int? = b
        println(boxedB == anotherBoxedB) // 输出“true”
        println("----- testNumber2 --------- end")
    }

    /**
     * 假想的代码，实际上并不能编译
     */
    fun testNumber3() {
//        val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
//        val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
//        print(b == a) // 惊！这将输出“false”鉴于 Long 的 equals() 会检测另一个是否也为 Long
    }

    fun testNumber4() {
        //val l = 1L + 3 // Long + Int => Long
        val l = 3 + 1L // Long + Int => Long
        println(l.javaClass) //long
        //println(l::class.java.name) //long

        //val a = 1f + 3 // float + Int => float
        val a = 3 + 1f // float + Int => float
        println(a.javaClass) //float

        //val b = 0.6 + 1
        val b = 1 + 0.6 // double + Int => double
        println(b.javaClass) //double

        //val c = 0.6 + 1
        val c = 1f + 0.6 // double + float => double
        println(c.javaClass) //double

        //val d = 0.6 + 1
        val d = 1L + 0.6 // double + long => double
        println(d.javaClass) //double

        println("----- testNumber4 --------- end")
    }

    fun testNumber5() {
        // 静态类型作为浮点数的操作数
        println(Double.NaN == Double.NaN)                 // false
        // 静态类型并非作为浮点数的操作数
        // 所以 NaN 等于它本身
        println(listOf(Double.NaN) == listOf(Double.NaN)) // true

        // 静态类型作为浮点数的操作数
        println(0.0 == -0.0)                              // true
        // 静态类型并非作为浮点数的操作数
        // 所以 -0.0 小于 0.0
        println(listOf(0.0) == listOf(-0.0))              // false

        println(listOf(Double.NaN, Double.POSITIVE_INFINITY, 0.0, -0.0).sorted())
        // [-0.0, 0.0, Infinity, NaN]
    }

    fun foo1() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // 非局部直接返回到 foo() 的调用者
            println("foo1:$it")
        }
        println("foo1: this point is unreachable")
    }

    fun foo2() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者——forEach 循环
            println("foo2:$it")
        }
        println("foo2: done with explicit label")
    }

    fun foo3() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者——forEach 循环
            println("foo3:$it")
        }
        println("foo3: done with implicit label")
    }

    fun foo4() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // 局部返回到匿名函数的调用者——forEach 循环
            println("foo4:$value")
        })
        println("foo4: done with anonymous function")
    }

    fun foo5() {
        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
                println("foo5:$it")
            }
        }
        print("foo5： done with nested loop")
    }
}



fun main() {
    val obj = TestType()
    obj.testNumber0()
    obj.testNumber1()
    obj.testNumber2()
    obj.testNumber4()

    obj.foo1()
    obj.foo2()
    obj.foo3()
    obj.foo4()
    obj.foo5()

}