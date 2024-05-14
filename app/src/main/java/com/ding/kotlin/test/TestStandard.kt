package com.ding.kotlin.test

import java.util.*

/**
 * let \ apply \ also \ run \ takeIf \ takeUnless
 */
class TestStandard {

    private var strValue: String? = null

    /**
     * let 返回最后一行表达式结果
     */
    fun testLet() {
        strValue = "hEllo worlD"
        strValue = strValue?.let {
            it.lowercase()
                .replaceFirstChar { char ->
                    if (char.isLowerCase()) {
                        char.titlecase(Locale.getDefault())
                    } else {
                        char.toString()
                    }
                }
        }
        println("test let :$strValue")
    }

    /**
     * also 返回原对象
     */
    fun testAlso() {
        strValue = "hEllo worlD"
        strValue = strValue?.also {
            it.lowercase()
        }
        println("test also :$strValue")
    }

    /**
     * apply 返回原对象
     */
    fun testApply() {
        strValue = "hEllo worlD"
        strValue = strValue?.apply {
            lowercase()
        }
        println("test apply :$strValue")
    }

    /**
     * run 返回最后一行执行结果；可用来执行函数
     */
    fun testRun1() {
        strValue = "hEllo worlD"
        strValue = strValue?.run {
            lowercase()
        }
        println("test run :$strValue")
    }

    fun testRun2() {
        strValue = "hEllo worlD"
        strValue?.run(::toLowercase)
            ?.run(::toFirstChar)
            .run(::printValue)
    }

    /**
     * 转换为小写
     */
    fun toLowercase(value: String?): String? {
        return value?.lowercase() ?: ""
    }

    /**
     * 首字母大写
     */
    fun toFirstChar(value: String?): String {
        return value?.capitalize() ?: ""
    }

    fun printValue(text: String?) {
        println("打印: $text")
    }

    /**
     * takeIf 如果表达式为true,返回原对象；为false ,则返回null
     */
    fun testTakeIf() {
        strValue = "hEllo worlD"
        val isResult = strValue?.takeIf { it == "hello world" }
        println("test takeIf :$strValue  isResult = $isResult")
    }

    /**
     * takeIf 如果表达式为true,则返回null；为false ,返回原对象
     */
    fun testTakeUnless() {
        strValue = "hEllo worlD"
        val isResult = strValue?.takeUnless { it == "hello world" }
        println("test takeUnless :$strValue  isResult = $isResult")
    }
}

fun main() {
    val obj = TestStandard()
    obj.testLet()
    obj.testAlso()
    obj.testApply()
    obj.testRun1()
    obj.testRun2()
    obj.testTakeIf()
    obj.testTakeUnless()
}