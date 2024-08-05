package com.ding.kotlin.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class TestTouchLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "Touch - TestTouchLayout"
    }

    var name: String = ""
    var touchEnable: Boolean = false
    var interceptTouchEnable: Boolean = false
    var dispatchTouchEnable: Boolean = true
    var interceptHalfEnable: Boolean = false

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        Log.i(
//            TAG,
//            "Touch--  $name  dispatchTouchEvent  ${ev?.action?.let { MotionEvent.actionToString(it) }}"
//        )
//        if (dispatchTouchEnable) {
//            return super.dispatchTouchEvent(ev)
//        }
//        return true
//    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(
            TAG,
            "Touch--  $name  onInterceptTouchEvent  ${
                ev?.action?.let {
                    MotionEvent.actionToString(it)
                }
            }"
        )
        if (interceptHalfEnable) {
            val touchY = ev?.y
            if (touchY != null && touchY > measuredHeight / 2) {
                return true
            }
        }
        if (interceptTouchEnable) {
            return true
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(
            TAG,
            "Touch--  $name  onTouchEvent  ${event?.action?.let { MotionEvent.actionToString(it) }}"
        )
        if (touchEnable) {
            return true
        }
        return super.onTouchEvent(event)
    }

}