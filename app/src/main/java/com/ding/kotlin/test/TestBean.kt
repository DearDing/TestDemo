package com.ding.kotlin.test

data class TestBean(
    var number: Number = 0,
    var name: String = "haha",
    var sex: String = "男",
    var anmal: AnimalCat = AnimalCat()
)

data class AnimalCat(
    var annimal: String = "猫"
)

fun main() {
    var obj1 = TestBean()
    obj1.name = "小明"
    var obj2 = TestBean()
    println(obj1 == obj2)
}
