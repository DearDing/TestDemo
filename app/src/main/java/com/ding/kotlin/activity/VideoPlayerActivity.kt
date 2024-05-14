package com.ding.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.R

/**
 * 视频播放
 */
class VideoPlayerActivity : AppCompatActivity() {

    private val mPlayUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
    }


}