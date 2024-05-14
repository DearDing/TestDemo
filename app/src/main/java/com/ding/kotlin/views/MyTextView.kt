package com.ding.kotlin.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class MyTextView : AppCompatTextView {

    constructor(context: Context):super(context)

    constructor(context: Context, attrs: AttributeSet?):super(context,attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setInvalidateCall() {
        invalidate()
    }

    fun setRequestLayout() {
        requestLayout()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        val isVisible = (visibility == View.VISIBLE)
        Log.e("MyTextView","onWindowVisibilityChanged  isVisible=$isVisible")
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        val isVisible = (visibility == View.VISIBLE)
        Log.e("MyTextView","onVisibilityChanged  isVisible=$isVisible")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.e("MyTextView","onAttachedToWindow")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.e("MyTextView","onDetachedFromWindow")
    }
}