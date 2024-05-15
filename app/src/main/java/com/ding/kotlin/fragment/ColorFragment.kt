package com.ding.kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.ding.kotlin.R
import com.ding.kotlin.databinding.FragmentColorLayoutBinding

class ColorFragment : BaseFragment<FragmentColorLayoutBinding>() {

    companion object {

        private const val TAG = "ColorFragment"

        @JvmStatic
        fun getInstance(position: Int, @ColorRes color: Int): Fragment {
            val fragment = ColorFragment()
            fragment.arguments = bundleOf(Pair("position", position), Pair("color", color))
            return fragment
        }
    }

    private var mPosition = 0
    private var mAddr:Int = -1

    override fun initParams() {
        mPosition= arguments?.getInt("position", 0) ?: 0
        super.initParams()
    }

    override fun initView() {
        val color = arguments?.getInt("color") ?: R.color.white
        mBind?.viewBg?.setBackgroundResource(color)
        mBind?.tvText?.text = "当前位置：$mPosition"
    }

    private fun getMemoryAddr():Int{
        if(mAddr == -1) {
            mAddr = System.identityHashCode(this)
        }
        return mAddr
    }

    override fun onAttach(context: Context) {
        Log.i(TAG,"${getMemoryAddr()} onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"${getMemoryAddr()} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG,"${getMemoryAddr()} onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG,"${getMemoryAddr()} onViewCreated $mPosition")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.i(TAG,"${getMemoryAddr()} onStart $mPosition")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG,"${getMemoryAddr()} onResume $mPosition")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG,"${getMemoryAddr()} onPause $mPosition")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG,"${getMemoryAddr()} onStop $mPosition")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.i(TAG,"${getMemoryAddr()} onDestroyView $mPosition")
        super.onDestroyView()
    }

    override fun onDetach() {
        Log.i(TAG,"${getMemoryAddr()} onDetach $mPosition")
        super.onDetach()
    }

    override fun onDestroy() {
        Log.i(TAG,"${getMemoryAddr()} onDestroy $mPosition")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i(TAG,"${getMemoryAddr()} onSaveInstanceState $mPosition")
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.i(TAG,"${getMemoryAddr()} onViewStateRestored $mPosition")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Log.i(TAG,"${getMemoryAddr()} onHiddenChanged $mPosition hidden:$hidden")
        super.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Log.i(TAG,"${getMemoryAddr()} setUserVisibleHint isVisibleToUser:$isVisibleToUser")
        super.setUserVisibleHint(isVisibleToUser)
    }


}