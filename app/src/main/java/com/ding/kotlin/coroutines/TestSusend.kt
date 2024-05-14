package com.ding.kotlin.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        val isSuccess = copyFileTo()
        println("---copy reusult $isSuccess")
    }
}

private suspend fun copyFileTo(): Boolean {
    val isSuccess = withContext(Dispatchers.IO) {
        try {
            delay(3000)
            println("假装复制成功")
            true
        } catch (e: Exception) {
            println(e.toString())
            false
        } finally {
            println("关闭并清除资源")
        }
    }
    return isSuccess
}

