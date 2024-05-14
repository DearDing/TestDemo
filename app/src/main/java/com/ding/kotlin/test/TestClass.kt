package com.ding.kotlin.test

import com.ding.test1.State
import kotlin.properties.Delegates

/**
 * (1)在实例初始化期间，初始化块按照它们出现在类体中的顺序执行，与属性初始化器交织在一起;
 * (2)初始化块中的代码实际上会成为主构造函数的一部分。对主构造函数的委托发生在访问次构造函数的第一条语句时，因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行。
 * 即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块
 */
class TestClass {

    private var name: String = "小明"

    init {
        //在lastName初始化之前执行
        println("执行init()   name=$name")
    }

    /**
     * 次构造函数
     */
    constructor(value: Int) {
        println("执行构造函数  value=$value")
    }

    private var lastName: String = "哈哈哈"

    init {
        println("执行init() name=$name  lastName=$lastName")
    }
}

/**
 * 如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。构造函数的可见性是 public。
 * 如果你不希望你的类有一个公有构造函数，那么声明一个带有非默认可见性的空的主构造函数。
 */
class TestClassSelf private constructor() {

    companion object {

        private lateinit var instance: TestClassSelf

        @JvmStatic
        fun getInstance(): TestClassSelf {
            instance = TestClassSelf()
            return instance
        }
    }
}

/**
 * 在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成一个额外的无参构造函数，它将使用默认值。
 */
class TestClassParams(val params: String = "哈哈哈")

interface AnimalInfo {
    var lastName: String
    var firstName: String
}

/**
 * 可以在接口中定义属性。
 * 在接口中声明的属性要么是抽象的，要么提供访问器的实现。
 * 在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们。
 */
interface Dog : AnimalInfo {

    var name: String // 抽象的
    val master: String // 抽象的

    val sayHi: String //提供访问器实现
        get() = "say hi"

    fun eat() {
        println("dog eat()")
    }

    fun water()
}

/**
 * 函数式接口（或 单一抽象方法 SAM）
 * 只有一个抽象方法的接口称为函数式接口或 单一抽象方法（SAM）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
 * 作用：可以使用lambda简化编程（SAM 转换）
 */
fun interface Cat {
    fun wan()
}

/**
 * 类型别名 typealias
 * '类型别名' 跟 '函数式接口' 区别
 * （1）函数式接口和类型别名用途并不相同。 类型别名只是现有类型的名称——它们不会创建新的类型，而函数式接口却会创建新类型。
 * （2）类型别名只能有一个成员，而函数式接口可以有多个非抽象成员以及一个抽象成员。 函数式接口还可以实现以及继承其他接口。
 */
typealias SumType = (num1: Int, num2: Int) -> Int //两个数求和，返回 Int类型数据

private var totalAge: SumType = { num1, num2 ->
    println("两个数求和 $num1 + $num2")
    num1 + num2
}

open class Animal {

    open fun eat() {
        println("anmial eat()")
    }

    open fun water() {
        println("animal water()")
    }

    open fun sayName() {}
}

class ZooLife : Animal(), Dog {

    override var lastName: String = "aaa"

    override var firstName: String
        get() = "bbb"
        set(value) {}

    override var name: String = "haha"
    override val master: String
        get() = "ccc"

    //传统实现
    private var mCat1: Cat = object : Cat {
        override fun wan() {
            println("cat happy")
        }
    }

    //SAM转换 后，更简洁
    private var mCat2: Cat = Cat { println("cat more happy") }

//    private lateinit var name:String

    /**
     * 同时父类跟接口的 eat() 方法
     */
    override fun eat() {
        super<Animal>.eat()
        super<Dog>.eat()
        println("ZooLife eat()")
    }

    override fun water() {
        super.water()
        println("ZooLife water()")
    }

}

interface Base {
    val message: String
    fun printline(msg: String)
}

class BaseImpl : Base {

    override val message: String = "aaaaaa"

    override fun printline(msg: String) {
        println("BaseImpl 打印：$msg")
    }
}

/**
 * 委托
 */
class Printer(impl: BaseImpl) : Base by impl {
    override val message: String = "bbbbbb"
}

/**
 * 属性委托
 */
class User {
    /**
     * 可观察属性，观察属性值变化
     */
    var name: String by Delegates.observable("小明") { property, oldValue, newValue ->
        run {
            //处理属性变化监听
            println("property:$property  oldValue:$oldValue   newValue:$newValue")
        }
    }

    /**
     * 拦截属性赋值
     */
    var lastName: String by Delegates.vetoable("姓名") { property, oldValue, newValue ->
        run {
            println("property:$property  oldValue:$oldValue   newValue:$newValue")
        }
        false
    }
}


fun main() {
    val clzObj = TestClass(1000)
    val animalObj = ZooLife()
    animalObj.eat()
    animalObj.water()

    //使用类型别名
    totalAge(10, 20)

    //委托
    val impl: BaseImpl = BaseImpl()
    val printer: Printer = Printer(impl)
    printer.printline(printer.message) //BaseImpl 打印：bbbbbb
    printer.printline(impl.message) //BaseImpl 打印：aaaaaa

    val user: User = User()
    user.name = "大王" //property:property name (Kotlin reflection is not available)  oldValue:小明   newValue:大王
    user.lastName = "哈哈" //property:property lastName (Kotlin reflection is not available)  oldValue:姓名   newValue:哈哈
}