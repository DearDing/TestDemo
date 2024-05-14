package com.ding.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ding.kotlin.util.ViewBindingUtil

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var mBind: VB? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initParams()
        mBind = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false)
        return mBind?.root
    }

    open fun initParams(){}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()
    }

    abstract fun initView()

    open fun initListener(){}
    open fun initData(){}

    override fun onDestroyView() {
        super.onDestroyView()
        mBind = null
    }
}