package com.ding.kotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ding.kotlin.databinding.FragmentRedLayoutBinding

class ColorFragment: BaseFragment<FragmentRedLayoutBinding>() {

    companion object {

        @JvmStatic
        fun getInstance(position:Int):Fragment{
            return ColorFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}