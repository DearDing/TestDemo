package com.ding.kotlin.coroutines

private fun simple():Sequence<Int> = sequence{
    for (i in 1..3){
        Thread.sleep(1000)
        yield(i)
    }
}

fun main(){
    simple().forEach {
        println(it)
    }
}