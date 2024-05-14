package com.ding.kotlin.test

/**
 * 1、内联类必须含有唯一的一个属性(只读的)在主构造函数中初始化。在运行时， 将使用这个唯一属性来表示内联类的实例。内联类被编译为其基础类型。
 * 2、类的数据被内联到该类使用的地方.
 * 3、内联类支持普通类中的一些功能。可以在内联类里声明属性、次构造函数、init
 * 4、内联类允许去继承接口。
 * 5、在生成的代码中，Kotlin 编译器为每个内联类保留一个包装器。内联类的实例可以在运行时表示为包装器或者基础类型。这就类似于 Int 可以表示为原生类型 int 或者包装器 Integer。
 * 为了生成性能最优的代码，Kotlin 编译更倾向于使用基础类型而不是包装器。 然而，有时候使用包装器是必要的。一般来说，只要将内联类用作另一种类型， 它们就会被装箱。
 * ⚠️注意：因为内联类既可以表示为基础类型有可以表示为包装器，引用相等 对于内联类而言毫无意义，因此这也是被禁止的。
 * 6、由于内联类被编译为其基础类型，因此可能会导致各种模糊的错误，例如意想不到的平台签名冲突：happySelf方法 。为了缓解这种问题，一般会通过在函数名后面拼接一些稳定的哈希码来修饰函数名。 因此，fun happySelf(my: TestInline) 将会被表示为 public final void happySelf-<hashcode>(TestInline my)，以此来解决冲突的问题。
 */
interface Family{
    val sister:String
    fun sayHelloToSister()
}

@JvmInline
value class TestInline(val name: String):Family {

    override val sister: String
        get() = "珍妮 赫本"

    override fun sayHelloToSister() {
        TODO("Not yet implemented")
    }

    //只读属性
    val sex: String
        get() = "性别属性"

    init {
        println("打印 sex =  $sex")
    }

    //不能有次构造函数方法体，会报错
    constructor(firstName: String, lastName: String) : this("$firstName $lastName")

    fun sayName() {
        println("我的名字叫：$name")
    }

}

fun happyFamily(family: Family){
    println("哈哈哈哈")
}

/**
 *
 */

// 在 JVM 平台上被表示为'public final void compute(String my)'
fun happySelf(my:TestInline){
    println("嘿嘿嘿")
}

// 同理，在 JVM 平台上也被表示为'public final void happySelf(String my)'！
fun happySelf(my:String) {
    println("嘿嘿嘿")
}

fun main() {
    // 不存在 'TestInline' 类的真实实例对象
    // 在运行时，'name' 仅仅包含 'String'
    val first = TestInline("内联类测试")
    val second = TestInline("罗杰夫", "赫本")
    first.sayName() //函数会作为一个静态方法被调用
    println(first.sex)//属性的 getter 会作为一个静态方法被调用
    second.sayName()
    println(second.name)

    happyFamily(first)//装箱操作: 用作类型 Family (用作另一种类型)
    happySelf(first)//拆箱操作: 用作 TestInline 本身


}
