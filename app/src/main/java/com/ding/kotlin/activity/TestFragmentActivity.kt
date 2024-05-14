package com.ding.kotlin.activity

import androidx.fragment.app.Fragment
import com.ding.kotlin.databinding.ActivityViewPagerTestBinding

class TestFragmentActivity : BaseActivity<ActivityViewPagerTestBinding>() {

    private val mFragmentList = arrayListOf<Fragment>()

    override fun initView() {
        mFragmentList.add(RedFragment.getInstance())
        mFragmentList.add(RedFragment.getInstance())
        mFragmentList.add(RedFragment.getInstance())
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    private fun createPageAdapter(){

    }

}