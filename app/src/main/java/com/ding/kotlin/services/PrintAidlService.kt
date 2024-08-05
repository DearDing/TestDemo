package com.ding.kotlin.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ding.kotlin.IPrintAidlInterface

/**
 * aidl 打印日志服务
 */
class PrintAidlService : Service() {

    override fun onBind(intent: Intent?): IBinder? {

        return object:IPrintAidlInterface.Stub(){
            override fun printText(text: String?): String {
                Log.i("tag aidl","aidl service : $text")
                return "打印的文本：$text"
            }

            override fun printName(name: String?): String {
                Log.i("tag aidl","aidl service : $name")
                return "你好，我的名字是 $name"
            }
        }
    }
}