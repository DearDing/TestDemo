package com.ding.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main() {
    println("start--")

//    case1()
//    case2()
    try {
        case3()
    }catch (e:Exception){
        println(e.toString())
    }

    println("end--")
}

suspend fun case1(){
    suspendCoroutine<Unit> {
        it.resume(Unit)
    }
}

suspend fun case2(){
    val result = suspendCoroutine<Int> {
        it.resume(12)
    }
    println("返回值 $result")
}

suspend fun case3(){
    val result  = suspendCancellableCoroutine<String> {
//        it.cancel(java.lang.NullPointerException("取消，并抛出了空指针异常"))
        it.cancel()
        it.resume("我是返回值")
    }
    println(result)
}