package com.ding.kotlin.juc

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

/**
 * 生成一个计算任务，计算 1+2+3.........+1000,每 100 个数切分一个 子任务
 */
class TestForkJoin(
    private var start: Int,
    private var end: Int
) : RecursiveTask<Long>() {

    private var sum: Long = 0

    override fun compute(): Long {
        println("任务 $start  ====  $end  累加开始")
        //大于100 个数相加切分，小于直接加
        if (end - start < 100) {
            (start..end).forEach {
                sum += it
            }
        } else {
            val middle = start + 100
            //切分成两个小任务，递归调用（内部会再次切分）
            val task1 = TestForkJoin(start, middle)
            val task2 = TestForkJoin(middle, end)
            task1.fork()
            task2.fork()
            sum = task1.join() + task2.join()
        }
        return sum
    }
}

fun main() {
    val task = TestForkJoin(0, 1000)
    val forkJoinPool = ForkJoinPool()
    val result = forkJoinPool.submit(task)
    try {
        println(result.get())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        forkJoinPool.shutdown()
    }
}