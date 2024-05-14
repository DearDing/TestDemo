package com.ding.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun testFlow() {
    val testFlow = flowOf(1, 2, 3)
    GlobalScope.launch {
        testFlow.collect { value ->
            println(value)
        }
    }
}

/**
 * StateFlow
 * 有状态的Flow ，可以有多个观察者，热流
 * 构造时需要传入初始值 : initialState
 * 常用作与UI相关的数据观察，类比LiveData
 *
 * 打印结果：哈哈哈
 */
fun testStateFlow() {
    val testFlow = MutableStateFlow("hello world!")
    GlobalScope.launch {
        testFlow.collect {
            println(it)
        }
    }
    testFlow.value = "哈哈哈"
}


fun main() {
    runBlocking {
//        testFlow()
//        testStateFlow()

        var flowObj = (1..10).asFlow()
        flowObj.collect {value->
            println(value)
        }
        flowObj = (10..15).asFlow()
        flowObj.collect {
            println(it)
        }
//        flow {
//            for (i in 1..10){
//                emit(i)
//            }
//        }.collect {
//            println(it)
//        }
    }
}
