package com.ding.kotlin.juc

//import kotlinx.coroutines.sync.Semaphore
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore

open class TestSemaphore {

    fun runService() {
        val semaphore = Semaphore(3, true)
        val pool = Executors.newFixedThreadPool(5)
        (1..20).forEach {
            pool.submit {
                semaphore.acquire(2)
                println("${Thread.currentThread().name}  成功获取许可证")
                Thread.sleep(1000)
                println("${Thread.currentThread().name}  释放许可证")
                semaphore.release(2)
            }
        }
        pool.shutdown()
        val tryAcquire = semaphore.tryAcquire()
        println("${Thread.currentThread().name} 尝试获取锁 ${if(tryAcquire) "成功" else "失败"}")
    }
}

fun main(){
    val test = TestSemaphore()
    test.runService()
}