package com.ding.kotlin.coroutines

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            println("当前线程：${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default  当前线程：${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined  当前线程：${Thread.currentThread().name}")
            delay(1000)
            println("Unconfined  当前线程：${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO  当前线程：${Thread.currentThread().name}")
        }
    }
    runBlocking {
        coroutineScope {
            launch {

            }
        }
    }
}