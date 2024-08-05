package com.ding.kotlin.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.IPrintAidlInterface
import com.ding.kotlin.R

/**
 * 进程间通讯
 * aidl 用例
 */
class TestAidlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_aidl_layout)
        startService()
    }

    private fun startService(){
        val intent = Intent()
        intent.component = ComponentName("com.ding.kotlin","com.ding.kotlin.services.PrintAidlService")
        bindService(intent,TestAidlConnection(),Context.BIND_AUTO_CREATE)
    }

    inner class TestAidlConnection: ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val aidlInterface = IPrintAidlInterface.Stub.asInterface(service)
            aidlInterface.printText("打印姓名===========")
            aidlInterface.printName("张三")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            println("服务断开连接！！！！！")
        }
    }
}
