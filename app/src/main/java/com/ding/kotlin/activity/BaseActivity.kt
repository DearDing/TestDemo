package com.ding.kotlin.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.ding.kotlin.util.ViewBindingUtil

/**
 * viewBinding 封装
 */
abstract class BaseActivity<DB : ViewBinding> : AppCompatActivity() {

    val mDB: DB by lazy {
        ViewBindingUtil.inflateWithGeneric(this, layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mDB.root)
        initView()
        initListener()
        initData()
    }

    abstract fun initView()

    open fun initListener(){}

    open fun initData(){}

}