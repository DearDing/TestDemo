package com.ding.kotlin.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private suspend fun doSomeThingOne(): Int {
    delay(1000)
    println("do something one")
    return 100
}

private suspend fun doSomeThingTwo(): Int {
    delay(1000)
    println("do something two")
    return 200
}

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomeThingOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomeThingTwo() }
            two.start()
            one.start()
            println("The answer is ${one.await() + two.await()}")
        }
        println("执行时间$time")
    }
}