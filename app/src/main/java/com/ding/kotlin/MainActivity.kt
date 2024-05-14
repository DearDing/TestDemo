package com.ding.kotlin

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.ding.kotlin.activity.MyCardActivity
import com.ding.kotlin.activity.TestActivity
import com.ding.kotlin.views.MarqueeTextView
import com.ding.kotlin.views.MyTextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "TestCoroutine"
    private val mainScope: CoroutineScope = MainScope()

    private lateinit var mTv: MyTextView
    private lateinit var mMarqueeTv: MarqueeTextView
    private lateinit var mTest:Button
    private var mClickCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTv = findViewById(R.id.tv_text)
        mMarqueeTv = findViewById(R.id.tv_marquee)
        mTest = findViewById(R.id.bt_test)
        initListener()
//        testGlobalScope1()
//        testGlobalScope2()
//        testMethod()
//        doSomething()
//        case1()
//        case2()
//        caseJob()
//        caseHandler()
        mMarqueeTv.startScroll()
    }

    override fun onRestart() {
        super.onRestart()
        println("MainActivity - onRestart")
    }

    override fun onStart() {
        super.onStart()
        println("MainActivity - onStart")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        println("MainActivity - onPostCreate")
    }

    override fun onPostResume() {
        super.onPostResume()
        println("MainActivity - onPostResume  ${mTv.width}")
    }

    override fun onResume() {
        super.onResume()
        println("MainActivity - onResume ${mTv.width}")
    }

    override fun onPause() {
        super.onPause()
        println("MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        println("MainActivity - onStop")
    }

    private fun initListener() {
        mTv.setOnClickListener {
//            if (mClickCount % 2 == 0) {
//                caseInvalidate()
//            } else {
//                caseRequestLayout()
//            }
            caseObjectAnimation()
        }
        mTest.setOnClickListener {
//            startActivity(Intent(this,TestActivity::class.java))
            startActivity(Intent(this, MyCardActivity::class.java))
        }
    }

    private fun caseObjectAnimation() {
//        Thread({
//            Looper.prepare()
            val objectAnim = ObjectAnimator.ofFloat(mTv, "translationX", 0f,10f,20f,30f,40f,50f,60f,60f,70f,80f)
            objectAnim.duration = 1000
            objectAnim.start()
//        }, "AAA").start()
    }

    private fun caseInvalidate() {
        mTv.setInvalidateCall()
    }

    private fun caseRequestLayout() {
        mTv.setRequestLayout()
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