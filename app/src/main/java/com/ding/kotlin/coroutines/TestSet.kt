package com.ding.kotlin.coroutines

/**
 * 类委托
 */
class TestSet<T>(private val innerSet: MutableSet<T> = HashSet<T>()) : MutableSet<T> by innerSet {

    var objectSize: Int = 0

    override fun add(element: T): Boolean {
        objectSize++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectSize += elements.size
        return innerSet.addAll(elements)
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append('[')
        innerSet.forEachIndexed { index, it ->
            builder.append(it)
            if (index < (objectSize - 1)) {
                builder.append(',')
            }
        }
        builder.append(']')
        return builder.toString()
    }
}

fun main() {
    val mySet = TestSet<Int>()
    mySet.add(29)
    mySet.add(30)
    mySet.add(32)
    mySet.add(340)
    mySet.add(4)
    mySet.addAll(listOf(1, 2, 3))
    println(mySet.toString())
}

