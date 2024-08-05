package com.ding.kotlin.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class TestTouchView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    companion object{
        private const val TAG = "Touch - TestTouchView"
    }

    var name:String = ""
    var touchEnable: Boolean = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(TAG,"Touch--  $name  onTouchEvent  ${event?.action?.let { MotionEvent.actionToString(it) }}")
        if(touchEnable){
            return true
        }
        return super.onTouchEvent(event)
    }
}
