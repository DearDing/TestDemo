package com.ding.kotlin.lock;

import com.ding.kotlin.test.TestJava;
import com.ding.kotlin.test.TestPrinter;

import java.io.IOException;

import kotlin.jvm.functions.Function1;

public class TestKotlin {

    public String name = "张三";
    public String sex = "男";

    private TestJava foodClass = new TestJava();

    public void sayHi() {
        TestPrinter.printStr(name + "say hi");
    }

    public void test() {
        String food = foodClass.getFood();
        String fruit = foodClass.fruit;
        foodClass.eatFood(food);
        foodClass.drinkWarter(fruit, "果汁");
        TestPrinter.printStr(TestJava.secondFood);
        TestPrinter.printStr(TestJava.Companion.getDinnerFood());
        TestJava.eatDinner();
        TestJava.Companion.eatLunch();
    }

    /**
     * 捕获kotlin抛出的异常
     */
    public void catchKotlinIOException() {
        try {
            foodClass.testThrowException();
        } catch (IOException e) {
            TestPrinter.printStr("捕获到了kotlin方法抛出的异常");
        }
    }

    public void testThrowException() throws IOException {
        throw new IOException("io异常");
    }

    /**
     * kotlin函数使用 FunctionN 这样的名字来表示；N表示参数数量，每一个FunctionN 都包含一个invoke函数，专门用于调用函数类型函数。
     */
    public void testFunction(){
        Function1<String, String> translater = foodClass.getTranslater();
        String result = translater.invoke("TRUE");
        TestPrinter.printStr(result);
    }

}
