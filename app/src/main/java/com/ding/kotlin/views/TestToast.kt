package com.ding.kotlin.views

import android.content.Context
import android.widget.Toast

open class TestToast {
    companion object {

        @JvmStatic
        fun showToast(context: Context) {
            val toast = Toast.makeText(context, "哈哈哈", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}