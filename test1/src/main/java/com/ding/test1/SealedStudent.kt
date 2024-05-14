package com.ding.test1

/**
 * 密封类
 */
sealed class Student {

    /**
     * 注意 : 只有被 open 修饰的函数才能被 override 重写
     */
    open fun study(){
        println("学习")
    }

    /**
     * 子类 1
     */
    class GoodStudent : Student(){
        override fun study() {
            println("学习很好")
        }

        fun read(){
            println("读书")
        }
    }

    /**
     * 子类 2
     */
    class NormalStudent : Student(){
        override fun study() {
            println("学习一般")
        }

        fun seat(){
            println("静坐")
        }
    }

    /**
     * 子类 3
     */
    class BadStudent : Student(){
        override fun study() {
            println("学的很渣")
        }

        fun play(){
            println("打游戏")
        }
    }
}

/**
 * 根据不同的类型执行不同的方法
 */
fun studentAction(student : Student) = when(student){
    //如果已经覆盖了 3 个子类 , 即所有的情况 , 此时可以不需要定义 else 语句
    is Student.GoodStudent -> student.read()
    is Student.BadStudent -> student.play()
    is Student.NormalStudent -> student.seat()
    //else -> println("其它情况")
}

fun main(){
    // 1 . 测试密封类子类 1
    val goodStudent : Student.GoodStudent = Student.GoodStudent()
    //学习很好
    goodStudent.study()
    //读书
    studentAction(goodStudent)
    //2 . 测试密封类子类 2
    val normalStudent : Student.NormalStudent = Student.NormalStudent()
    //学习一般
    normalStudent.study()
    //静坐
    studentAction(normalStudent)
    //3 . 测试密封类子类 3
    val badStudent : Student.BadStudent = Student.BadStudent()
    //学的很渣
    badStudent.study()
    //打游戏
    studentAction(badStudent)


}