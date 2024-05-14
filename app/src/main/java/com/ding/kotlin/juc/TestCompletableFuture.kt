package com.ding.kotlin.juc

import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

class TestCompletableFuture {

    /**
     * 场景一：主动完成任务
     */
    fun test1() {
        val future = CompletableFuture<String>()
        Thread({
            try {
                println("线程-${Thread.currentThread().name} 开始干活了")
                Thread.sleep(500)
                future.complete("success!!")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, "A").start()
        println("线程-${Thread.currentThread().name} 获取结果：${future.get()}")
        println("线程-${Thread.currentThread().name} test1() 执行结束！！")
    }

    /**
     * 场景二：没有返回值的异步任务
     */
    fun test2() {
        println("线程-${Thread.currentThread().name} test2() 开始执行了")
        val future = CompletableFuture.runAsync {
            try {
                println("线程-${Thread.currentThread().name} 开始干活了")
                Thread.sleep(500)
                println("线程-${Thread.currentThread().name} 干完活了")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        //主线程阻塞
        future.get()
        println("线程-${Thread.currentThread().name} test2() 结束了")
    }

    /**
     * 场景三：有返回值的异步任务
     */
    fun test3() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        val future: CompletableFuture<String> = CompletableFuture.supplyAsync {
            try {
                println("线程-${findCurrentThreadName()} 开始执行")
                Thread.sleep(1000)
                println("线程-${findCurrentThreadName()} 执行结束了,返回结果")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            "hello world"
        }
        println("线程-${findCurrentThreadName()} 打印结果：${future.get()}")
    }

    /**
     * 场景四：线程串行化
     */
    fun test4() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        var action: String = ""
        val future = CompletableFuture.supplyAsync {
            action = "和朋友去烧烤！！！/n"
            println("线程-${findCurrentThreadName()} $action")
            action
        }.thenApply {
            action = "然后去按摩店！！！/n"
            println("线程-${findCurrentThreadName()} $action")
            action
        }.thenApply {
            action = "天黑了回家！！！/n"
            println("线程-${findCurrentThreadName()} $action")
            "执行结束！"
        }
        println("线程-${findCurrentThreadName()} ${future.get()}")
    }

    /**
     * 场景五：thenAccept 消费处理结果
     */
    fun test5() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        var action: String = ""
        val future = CompletableFuture.supplyAsync {
            action = "和朋友去烧烤！！！/n"
            println("线程-${findCurrentThreadName()} $action")
            action
        }.thenApply {
            action = "然后去按摩店！！！/n"
            println("线程-${findCurrentThreadName()} $action")
            action
        }.thenAccept {
            //没有返回值
            action = "天黑了回家！！！/n"
            println("线程-${findCurrentThreadName()} $action")
        }
    }

    /**
     * 场景六：异常处理
     * （1）exceptionally 异常处理,出现异常时触发，可以回调给你一个从原始Future中生成的错误恢复的机会。你可以在这里记录这个异常并返回一个默认值。
     */
    fun test6_1() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        val future = CompletableFuture.supplyAsync {
            println("线程-${findCurrentThreadName()}  正在干活。。。")
            val result = 1 / 0
            println("-----------前方有异常")
            result
        }.exceptionally {
            println("线程-${findCurrentThreadName()}  捕获到异常：${it}")
            -1
        }
        println("线程-${findCurrentThreadName()} ${future.get()}")
    }

    /**
     * 场景六：异常处理
     * （2）使用 handle() 方法处理异常 API提供了一个更通用的方法 - handle()从异常恢复，无论一个异常是否发生它都会被调用
     */
    fun test6_2() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        val future = CompletableFuture.supplyAsync {
            println("线程-${findCurrentThreadName()}  正在干活。。。")
            val result = 1 / 0
            println("-----------前方有异常")
            result
        }.handle { t, u ->
            println("线程-${findCurrentThreadName()} $u")
            -1
        }
        println("线程-${findCurrentThreadName()} ${future.get()}")
    }

    /**
     * 场景七： 结果合并
     * (1)thenCompose 合并两个有依赖关系的 CompletableFutures 的执行结果
     */
    fun test7_1() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        var number = 0
        val job1 = CompletableFuture.supplyAsync {
            println("线程-${Thread.currentThread().name}  干活...$number")
            number += 10
            number
        }
        val job2 = CompletableFuture.supplyAsync {
            println("线程-${Thread.currentThread().name}  干活...$number")
            number += 10
            number
        }
        val future = job1.thenCompose { job2 }
        println("线程-${findCurrentThreadName()} 结果：${future.get()}")
    }

    /**
     * 场景七： 结果合并
     * (2)thenCombine 合并两个没有依赖关系的 CompletableFutures 任务
     */
    fun test7_2() {
        val job1 = CompletableFuture.supplyAsync {
            var result = ""
            (1..5).forEach {
                result += "$it + "
            }
            result
        }
        val job2 = CompletableFuture.supplyAsync {
            var result = 0
            (1..5).forEach {
                result += it
            }
            result
        }
        //合并结果
        val future = job1.thenCombine(job2) { result1, result2 ->
            val list = arrayListOf<Any>()
            list.add(result1)
            list.add(result2)
            list
        }
        println("线程-${findCurrentThreadName()} 结果：${future.get()}")
    }

    /**
     * 场景八：合并多个任务的结果
     * (1) allOf: 一系列独立的 future 任务，等其所有的任务执行完后做一些事情
     */
    fun test8_1() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        var number = 0
        val list = arrayListOf<CompletableFuture<Int>>()
        val job1 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number + 10")
            number += 10
            number
        }
        list.add(job1)
        val job2 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number + 5")
            number += 5
            number
        }
        list.add(job2)
        val job3 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number - 1")
            number -= 1
            number
        }
        list.add(job3)
        val job4 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number * 10")
            number *= 10
            number
        }
        list.add(job4)
//        val future = CompletableFuture.allOf(job1,job2,job3,job4)
        val result: List<Int> =
            list.stream().map(CompletableFuture<Int>::join).collect(Collectors.toList())
        println("线程-${findCurrentThreadName()} 结果：${result}")

    }

    /**
     * 场景八：合并多个任务的结果
     * (2) anyOf: 只要在多个 future 里面有一个返回，整个任务就可以结束，而不需要等到每一个 future 结束
     */
    fun test8_2() {
        println("线程-${Thread.currentThread().name}  开始干活了")
        var number = 0
        val list = arrayListOf<CompletableFuture<Int>>()
        val job1 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number + 10")
            number += 10
            number
        }
        list.add(job1)
        val job2 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number + 5")
            number += 5
            number
        }
        list.add(job2)
        val job3 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number - 1")
            number -= 1
            number
        }
        list.add(job3)
        val job4 = CompletableFuture.supplyAsync {
            println("${findCurrentThreadName()} $number * 10")
            number *= 10
            number
        }
        list.add(job4)
        val future = CompletableFuture.anyOf(job1,job2,job3,job4)
        println("线程-${findCurrentThreadName()} 结果：${future.get()}")

    }

    private fun findCurrentThreadName(): String {
        return Thread.currentThread().name
    }
}

fun main() {
    val future = TestCompletableFuture()
//    future.test1()
//    future.test2()
//    future.test3()
//    future.test4()
//    future.test5()
//    future.test6_1()
//    future.test6_2()
//    future.test7_1()
//    future.test7_2()
//    future.test8_1()
    future.test8_2()
}