package com.ding.kotlin.juc

import java.util.concurrent.locks.ReentrantLock

class TestLock {

    private val lock = ReentrantLock()
    private val mCondition = lock.newCondition()
    private var number = 1

    /**
     * +1
     */
    fun incNum() {
        try {
            lock.lock()
            while (number != 0) {
                mCondition.await()
            }
            number++
            println("${Thread.currentThread().name}  incNum()被调用，number = $number")
            mCondition.signal()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

    /**
     * -1
     */
    fun decNum() {
        try {
            lock.lock()
            while (number != 1) {
                mCondition.await()
            }
            number--
            println("${Thread.currentThread().name}  decNum()被调用，number = $number  ")
            mCondition.signal()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }

}

fun main() {
    val lockObj = TestLock()
    Thread({
        (1..10).forEach { _ ->
            lockObj.incNum()
        }
    }, "AA-Thread").start()
    Thread({
        (1..10).forEach { _ ->
            lockObj.decNum()
        }
    }, "BB-Thread").start()
}

