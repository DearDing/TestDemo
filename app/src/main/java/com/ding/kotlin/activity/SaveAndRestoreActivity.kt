package com.ding.kotlin.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ContextMenu
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity 的恢复
 */
class SaveAndRestoreActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun getLastNonConfigurationInstance(): Any? {
        return super.getLastNonConfigurationInstance()
    }
}