package com.ding.kotlin.juc

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * 使用 ReentrantReadWriteLock 对一个 hashmap 进行读和写操作
 */
class TestReadWriteLock {

    @Volatile
    private var mMap = hashMapOf<String, Any>()

    private var mRwLock = ReentrantReadWriteLock()

    fun put(key: String, value: Any) {
        mRwLock.writeLock().lock()
        try {
            println("${Thread.currentThread().name} 正在写数据... key=$key value=$value")
            sleep(200)
//            TimeUnit.MICROSECONDS.sleep(200)

            mMap[key] = value
            println("${Thread.currentThread().name} 写完了...  key=$key value=$value")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mRwLock.writeLock().unlock()
        }
    }

    private fun sleep(time: Long) {
        val curTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - curTime > time) {
            return
        }
    }

    fun get(key: String): Any? {
        mRwLock.readLock().lock()
        var result: Any? = null
        try {
            println("${Thread.currentThread().name} 正在读数据... key=$key")
            sleep(200)
//            TimeUnit.MICROSECONDS.sleep(200)

            result = mMap[key]
            println("${Thread.currentThread().name} 读完了... key=$key result=$result")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mRwLock.readLock().unlock()
        }
        return result
    }

    fun hasQueuedThreads(): Boolean {
        return mRwLock.hasQueuedThreads()
    }

    fun queueLength(): Int {
        return mRwLock.queueLength
    }

    fun printlnMap() {
        println(mMap)
    }
}

fun main() {
    val mObj = TestReadWriteLock()
    (1..5).forEach {
        Thread({
            mObj.put(it.toString(), it)
        }, it.toString()+"nn").start()
    }
    (1..5).forEach {
        Thread({
            mObj.get(it.toString())
        }, it.toString()).start()
    }
}