package com.ding.test1

/**
 * 密封类 sealed 修饰
 * 1、密封类可以让我们在编译时就确定所有可能的子类类型，从而提高代码的安全性和可读性
 * 2、密封类和接口的直接子类必须在同一个包中声明。它们可以是顶级的，也可以嵌套在任意数量的其他命名类、命名接口或命名对象中。子类可以具有任何可见性，只要它们与Kotlin中的正常继承规则兼容即可。
 * 3、抽象类本质 : 密封类本质是抽象类 , 其类型不能被实例化 , 只能实例化其子类
 * 4、私有构造函数 : 密封类的构造函数默认是 private 私有的 , 其构造函数必须是私有的 , 不允许非私有构造函数存在 ;
 * 5、enum类不能扩展密封类（以及任何其他类），但它们可以实现密封接口；
 * 6、密封类与枚举类 :
 * --① 相同点 ( 类型限制 ) : 从类型种类角度对比 , 类与枚举类类似 , 值的集合是受限制的 , 不能随意扩展 ;
 * --② 不同点 ( 对象个数限制 ) : 从每个类型对象个数对比 , 枚举类的每个类型只能存在一个实例 , 而密封类的每个类型可以创建无数个实例 ;
 */
sealed class State {
    abstract fun switch()
}

//abstract class Child:Student()

abstract class Bad : State()

/**
 * 状态设计模式(行为类设计模式)
 */
class On : State() {
    override fun switch() {
        Light.state = Off()
    }

    override fun equals(other: Any?): Boolean {
        if (other is State) {
            return other == Off()
        }
        return false
    }
}

class Off : State() {
    override fun switch() {
        Light.state = On()
    }

    override fun equals(other: Any?): Boolean {
        if (other is State) {
            return other == Off()
        }
        return false
    }
}

object Light {
    var state: State = On()

    fun switch() {
        state.switch()
    }
}

fun main() {
    //单例对象
    val light = Light
    //切换状态
    light.switch()
}

