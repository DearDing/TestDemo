package com.ding.kotlin.test

class TestString {

}

fun main() {
    val name = "刘名"
//    val desc = "刘名11哈哈到时候发顺丰刘名22的合法刘名"
    val desc = "11哈哈到时候发顺丰22的合法"
    var descArr = desc.split(name)
    println(descArr)

    val size = descArr.size
    val descBuilder = StringBuilder()
    descArr.forEachIndexed { index, it ->
        descBuilder.append(it)
        if (index != (size - 1)) {
            descBuilder.append(name)
        }
    }
    println(descBuilder.toString())
}