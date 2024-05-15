package com.ding.kotlin.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.R
import kotlinx.coroutines.*

class TestCoroutineActivity: AppCompatActivity() {

    private val TAG: String = "TestCoroutine"
    private val mainScope: CoroutineScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        testGlobalScope1()
//        testGlobalScope2()
//        testMethod()
//        doSomething()
//        case1()
//        case2()
//        caseJob()
//        caseHandler()
    }

    private fun caseHandler() {
        val handler = Handler(Looper.getMainLooper()) {
            println("${it.obj}")
            true
        }
        for (index in 0..5) {
            val msg = Message.obtain()
            msg.obj = "$index  hahahah"
            handler.sendMessage(msg)
        }
    }

    fun caseJob() {
        val parentHandler = CoroutineExceptionHandler { _, throwable ->
            println("caseJob：parent 捕获了异常 ${throwable.message}")
        }
        val childHandler = CoroutineExceptionHandler { _, throwable ->
            println("caseJob：child 捕获了异常 ${throwable.message}")
        }
        val scope = CoroutineScope(Dispatchers.Main + Job())
        scope.launch(parentHandler) {
            this.launch(childHandler + SupervisorJob()) {
                println("caseJob：执行了 job1 -- start")
                delay(1000)
                throw java.lang.NullPointerException("空指针异常了")
            }
        }
        scope.launch(Job()) {
            println("caseJob：执行了job2 -- start")
            delay(2000)
            println("caseJob：执行了 job2 -- end")
        }
    }

    fun case1() {
        val scope = MainScope()
        scope.launch {
            launch {
                delay(2000)
                println("执行了job1，接下来取消协程")
                scope.cancel()
            }
            launch {
                delay(3000)
                println("执行了job2")
            }
        }
    }

    fun case2() {
        val scope = MainScope()
        scope.launch(Job()) {
            launch {
                delay(2000)
                println("执行了job1，接下来取消协程")
                scope.cancel()
            }
            launch {
                delay(3000)
                println("执行了job2")
            }
        }
    }

    private fun doSomething() {
        repeat(1000) {
            mainScope.async {
                delay(500)
                Log.e(TAG, "$it 当前线程: ${Thread.currentThread().name}")
//                println("$it 当前线程: ${Thread.currentThread().name}")
            }
//            mainScope.launch {
//                Log.e(TAG, "$it 当前线程: ${Thread.currentThread().name}")
////                println("$it 当前线程: ${Thread.currentThread().name}")
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private var mMethodCount = 0

    /**
     * 递归测试（会报错，可以统计出方法执行次数）
     */
    private fun testMethod() {
        println("当前执行的方法次数:  mMethodCount = $mMethodCount")
        mMethodCount++
        testMethod()
        mMethodCount--
    }

    private fun testGlobalScope2() {
        val job = Job()
        val job1 = GlobalScope.launch(Dispatchers.Default + job) {
            println("job1 - start ")
            //将当前协程挂起指定时间，但不会阻塞线程，必须在协程的作用域或者其他挂起函数中执行
            delay(1000)
            println("job1 - end ")
        }
        Thread.sleep(200)
        job.cancel()
    }

    private fun testGlobalScope1() {
        GlobalScope.launch(Dispatchers.Main) {
            runUI1()
            runIO1()
            runUI2()
            runIO2()
            runUI3()
            runIO3()
        }
        GlobalScope.launch {
            // 当前线程 ： Thread[DefaultDispatcher-worker-1,5,main]
            println("当前线程 ： ${Thread.currentThread()}")
        }
    }

    private fun runUI1() {
        //执行run Ui 1    Thread[main,5,main]
        println("执行run Ui 1    ${Thread.currentThread()}")
    }

    private suspend fun runIO1() {
        withContext(Dispatchers.IO) {
            //执行run IO 1    Thread[DefaultDispatcher-worker-2,5,main]
            println("执行run IO 1    ${Thread.currentThread()}")
        }
    }

    private fun runUI2() {
        //执行run Ui 2    Thread[main,5,main]
        println("执行run Ui 2    ${Thread.currentThread()}")
    }

    private suspend fun runIO2() {
        withContext(Dispatchers.IO) {
            //执行run IO 2    Thread[DefaultDispatcher-worker-2,5,main]
            println("执行run IO 2    ${Thread.currentThread()}")
        }
    }

    private fun runUI3() {
        println("执行run Ui 3    ${Thread.currentThread()}")
    }

    private suspend fun runIO3() {
        withContext(Dispatchers.IO) {
            println("执行run IO 3    ${Thread.currentThread()}")
        }
    }
}