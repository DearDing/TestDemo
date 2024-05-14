package com.ding.kotlin.coroutines

import kotlinx.coroutines.*

//fun main() = runBlocking<Unit> {
//    val job = launch {
//        delay(20000)
//        println("超时：20秒")
//    }
//    println("执行代码。。。")
//    delay(1000)
//    job.cancel()
//    println("取消超时协程")
//    job.join()
//    println("执行结束")
//}

//fun main() = runBlocking {
//    val job = launch {
//        //被取消时抛出 CancellationException
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } catch (e: Exception) {
//            //kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@61e717c2
//            println(e.toString())
//        } finally {
//            println("job: I'm running finally")
//        }
//    }
//    delay(1300L) // 延迟一段时间
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // 取消该作业并且等待它结束
//    println("main: Now I can quit.")
//}

/**
 * 运行不能取消的代码块
 */
//fun main() = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("job: I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                println("job: I'm running finally")
//                delay(1000L)
//                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
//            }
//        }
//    }
//    delay(1300L) // 延迟一段时间
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin() // 取消该作业并等待它结束
//    println("main: Now I can quit.")
//}

/**
 * withTimeout 抛出了 TimeoutCancellationException，它是 CancellationException 的子类。
 * Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 1300 ms
 */
//fun main() = runBlocking {
//    withTimeout(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//}

//fun main() = runBlocking {
//    val result = withTimeoutOrNull(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//        "Done" // 在它运行得到结果之前取消它
//    }
//    println("Result is $result")
//}

var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource

    fun close() {
        acquired--
    } // Release the resource
}

fun main() {
    runBlocking {
        repeat(10_000) { // Launch 10K coroutines
            launch {
                var resource: Resource? = null
                try {
                    withTimeout(60) { // Timeout of 60 ms
                        delay(59) // Delay for 50 ms
                        resource =
                            Resource() // Acquire a resource and return it from withTimeout block
                    }
                } finally {
                    resource?.close() // Release the resource
                }
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}
