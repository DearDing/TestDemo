package com.ding.kotlin.test

class TestByLazy {

    private val printer by lazy {
        PrintTool()
    }

    fun printText(){
        printer.print()
    }
}

class PrintTool{
    fun print(){
        println("hahahha")
    }
}

fun main(){
    val test = TestByLazy()
    test.printText()
}