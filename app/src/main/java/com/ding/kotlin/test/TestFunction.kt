package com.ding.kotlin.test

/**
 * 函数式编程（针对集合数据类型设计）
 * 1、transform 转换: (1) map  (2) flatMap
 * 2、filter 过滤: (1) filter
 * 3、combine 合并: (1) zip  (2) fold
 */
class TestFunction {

    var listMap = listOf("cat", "dog", "鱼", "虾")
    var nameList = listOf("Jack", "Tom", "Martin", "Jcc", "HaHa")

    /**
     * 转换：map函数
     * 会对集合的每个元素遍历
     * 原集合没有被修改
     * @return 返回一个新的集合，并且元素个数和输入的必须相同；但是，返回的新集合里的元素可以是不同类型的。
     */
    fun testMap1(): List<String> {
        return listMap.map { animal -> "一只 $animal" }
            .map { text -> "吃 $text" }
    }

    /**
     * 转换：map函数，输入的集合元素转换成其他类型
     */
    fun testMap2(): List<Int> {
        return listMap.map { it.length }
    }

    /**
     * 转换：flatMap 函数
     * 操作一个集合的集合，将其中多个集合中的元素合并后返回一个包含所有元素的单一集合
     */
    fun testFlatMap(): List<Int> {
        return listOf(
            listOf(1, 2, 3, 4),
            listOf(5, 6, 7, 8)
        ).flatMap { list -> list.map { it + 1 } }
    }

    /**
     * 过滤：filter 函数
     * filter里的表达式返回true,就将该元素加入到新的集合里，返回false,就过滤掉
     */
    fun testFilter(): List<String> {
        return nameList.filter { it.contains("J") }
    }

    /**
     * 过滤：filter 函数（找出集合中的素数）
     * none函数 :如果没有元素与给定的元素匹配，则返回true
     * 素数：除了1和它本身，不能被任何数整除的数；
     * 取模等于0，说明能够整除，如果没有一个是等于0的，说明是素数
     */
    fun testFilter2() {
        val numbers: List<Int> = listOf(2, 3, 5, 7, 11, 33, 18, 12)
        val primes: List<Int> = numbers.filter { number ->
            (2 until number).map { number % it }.none { it == 0 }
        }
        println(primes)
    }

    /**
     * 合并：zip 函数
     * @return 返回一个包含键值对的新集合
     */
    fun testZip(): Map<String, String> {
        val list1 = listOf("Jack", "Mac", "Haha", "Ben") // key
        val list2 = listOf("large", "small", "little") // value
        return list1.zip(list2).toMap() //返回三个元素：{Jack=large, Mac=small, Haha=little}
    }

    /**
     * 合并：fold 函数
     * 用来合并值
     * @return
     */
    fun testFold(): Int {
        val list = listOf(1, 2, 3)
        val initNum = 2
        // initNum + 1*2 + 2*2 + 3*2
        return list.fold(initNum) { result, number ->
            println("result=$result  number=$number")
            result + number * 2
        }
    }

    /**
     * 序列 Sequence
     * 取1000个素数
     * generateSequence 序列构造函数，接收一个初始值，作为序列的起步值；会调用指定的迭代器函数获取到下一个值。
     */
    fun testSequence() {
        //序列构造函数，初始值为2
        val list2 = generateSequence(2) { value ->
            //迭代器函数，获取下一个值
            value + 1
        }.filter { num ->
            (2 until num)
                .map { num % it } //num对x（2<=x<num）求余，返回余数集合。
                .none { it == 0 } //集合中有0，说明有可以整除的数，不为素数，返回false。
        }.take(1000) //take：从第一个值开始，取前1000个值
        println(list2.toList().size)
    }
}

fun main() {
    val obj = TestFunction()
    println(obj.listMap)
    println(obj.testMap1())
    println(obj.listMap)
    println(obj.testMap2())
    println(obj.testFlatMap())
    println(obj.testFilter())
    println(obj.testZip())
    println(obj.testFold())
    obj.testFilter2()
    obj.testSequence()


}