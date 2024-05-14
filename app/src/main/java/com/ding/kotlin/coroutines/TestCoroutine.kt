package com.ding.kotlin.coroutines

import kotlinx.coroutines.*

//fun main(){
//    GlobalScope.launch {
//        delay(1000)
//        println("world!")
//    }
//    println("hello ")
////    Thread.sleep(2000)
//
//    //阻塞
//    runBlocking {
//        //挂起函数
//        delay(2000)
//    }
//}

//fun main() = runBlocking {
//    GlobalScope.launch {
//        delay(1000)
//        println("world")
//    }
//    println("hello ")
//    delay(2000)
//}

//fun main() = runBlocking {
//    val job = GlobalScope.launch {
//        delay(1000)
//        println("world")
//    }
//    println("hello ")
//    job.join() //等待协程结束
//    println("结束---")
//}

fun main() = runBlocking<Unit> {
    this.launch {
        delay(1000)
        println("2 world")
    }
    //创建一个协程作用域,需要等待子协程结束，才往下执行
    coroutineScope {
        launch {
            delay(200)
            println("3 作用域 结束。。。")
        }
        delay(500)
        println("1 结束。。。")
    }
    println("4 hello ")
}

