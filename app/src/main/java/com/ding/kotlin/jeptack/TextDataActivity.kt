package com.ding.kotlin.jeptack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.databinding.ActivityTextDataLayoutBinding

class TextDataActivity : AppCompatActivity() {

    private lateinit var layoutBinding: ActivityTextDataLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = ActivityTextDataLayoutBinding.inflate(layoutInflater)
        setContentView(layoutBinding.root)
    }
}