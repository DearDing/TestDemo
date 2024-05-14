package com.ding.kotlin.lock

import java.util.concurrent.locks.ReentrantLock
import kotlin.random.Random

fun main() {
    val printer = Printer()
    val threadArr: Array<Thread?> = arrayOfNulls(10)
    (0..4).forEach {
        threadArr[it] = Thread(PrintJob(printer))
    }
    for (thread in threadArr) {
        thread?.start()
        try {
            Thread.sleep(100)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

class PrintJob(private val printer: Printer) : Runnable {

    override fun run() {
        println("${Thread.currentThread().name} 开始打印")
        printer.runPrint()
        println("${Thread.currentThread().name} 结束打印")
    }
}

class Printer {

    //非公平锁
//    private val lock = ReentrantLock(false)
    //公平锁
    private val lock = ReentrantLock(true)

    fun runPrint() {
        //步骤1
        lock.lock()
        try {
            val duration = realPrint()
            Thread.sleep(duration * 1000L)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }

        //非公平锁会插队，当一个线程执行完步骤1后，会立即尝试获取锁（步骤2），因此该线程会抢先队列中的线程，从而获取到锁；
        //步骤2：再次尝试获取锁
        lock.lock()
        try {
            val duration = realPrint()
            Thread.sleep(duration * 1000L)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

    private fun realPrint(): Int {
        val duration = Random.nextInt(5) + 1
        println("${Thread.currentThread().name}  正在打印，需要 $duration 秒")
        return duration
    }
}