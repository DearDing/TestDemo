package com.ding.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.R
import com.ding.kotlin.views.MyTextView

class TestActActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "TestActActivity"
    }

    private lateinit var mTv: MyTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_test)
    }

    override fun onRestart() {
        super.onRestart()
        println("$TAG - onRestart")
    }

    override fun onStart() {
        super.onStart()
        println("$TAG - onStart")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        println("$TAG - onPostCreate")
    }

    override fun onPostResume() {
        super.onPostResume()
        println("$TAG - onPostResume  ${mTv.width}")
    }

    override fun onResume() {
        super.onResume()
        println("$TAG - onResume ${mTv.width}")
    }

    override fun onPause() {
        super.onPause()
        println("$TAG - onPause")
    }

    override fun onStop() {
        super.onStop()
        println("$TAG - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("$TAG - onDestroy")
    }


}