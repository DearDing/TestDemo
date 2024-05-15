package com.ding.kotlin.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ding.kotlin.R
import com.ding.kotlin.databinding.ActivityViewPagerTestBinding
import com.ding.kotlin.fragment.ColorFragment

class TestFragActivity : BaseActivity<ActivityViewPagerTestBinding>() {

    private val mFragmentList = arrayListOf<Fragment>()
    private val mFragmentList2 = arrayListOf<Fragment>()

    private var mPosition = -1

    override fun initView() {
        mFragmentList.add(ColorFragment.getInstance(0, R.color.blue))
        mFragmentList.add(ColorFragment.getInstance(1, R.color.red))
        mFragmentList.add(ColorFragment.getInstance(2, R.color.purple))
        mFragmentList.add(ColorFragment.getInstance(3, R.color.green))

        mFragmentList2.add(ColorFragment.getInstance(0, R.color.blue))
        mFragmentList2.add(ColorFragment.getInstance(1, R.color.red))
        mFragmentList2.add(ColorFragment.getInstance(2, R.color.purple))
        mFragmentList2.add(ColorFragment.getInstance(3, R.color.green))

        //ViewPager 的 offscreenPageLimit 默认为 1,开启预加载
//        mDB.viewPager.offscreenPageLimit = 2 // 缓存 2n+1 个,最大为 getCount()
//        mDB.viewPager.adapter = createFragmentPageAdapter()

//        mDB.viewPager.offscreenPageLimit = 2 // 缓存 2n+1 个,最大为 getCount()
//        mDB.viewPager.adapter = createFragmentStatePagerAdapter()

        //ViewPager2 的 offscreenPageLimit 默认为 -1，不开启预加载
        mDB.viewPager2.offscreenPageLimit = 1
        mDB.viewPager2.adapter = createFragmentStateAdapter()
    }

    override fun initListener() {
        mDB.btChange.setOnClickListener {
            mPosition++
            if (mPosition == mFragmentList.size) {
                mPosition = 0
            }
            mDB.viewPager.currentItem = mPosition
        }
    }

    /**
     * 受 offscreenPageLimit 的值影响；
     * 不会销毁Fragment, Fragment会调用 onDestroyView() 方法
     */
    private fun createFragmentPageAdapter(): FragmentPagerAdapter {
        return object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mFragmentList.size
            }

            override fun getItem(position: Int): Fragment {
                return mFragmentList[position]
            }
        }
    }

    /**
     * 受 offscreenPageLimit 的值影响；
     * 会销毁Fragment, Fragment会调用 onDestroy() 方法
     */
    private fun createFragmentStatePagerAdapter(): FragmentStatePagerAdapter {
        return object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return mFragmentList.size
            }

            override fun getItem(position: Int): Fragment {
                return mFragmentList[position]
            }
        }
    }

//    private fun createTopAdapter(): PagerAdapter {
//        return object : PagerAdapter() {
//
//            override fun isViewFromObject(view: View, `object`: Any): Boolean {
//                return view === `object`
//            }
//
//            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//                container.removeView(`object` as View)
//            }
//
//            override fun getCount(): Int {
//                return mListTop.size
//            }
//
//            override fun instantiateItem(container: ViewGroup, position: Int): Any {
//                val view = mListTop[position]
//                container.addView(view)
//                return view
//            }
//        }
//    }

    private fun createFragmentStateAdapter():FragmentStateAdapter{
        return object: FragmentStateAdapter(supportFragmentManager,lifecycle) {

            override fun getItemCount(): Int {
                return mFragmentList2.size
            }

            override fun createFragment(position: Int): Fragment {
                return mFragmentList2[position]
            }

        }
    }

}