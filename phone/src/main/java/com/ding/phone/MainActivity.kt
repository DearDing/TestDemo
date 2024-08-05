package com.ding.phone

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity

class MainActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService()
    }

    private fun startService(){
        val intent = Intent()
        intent.component = ComponentName("com.ding.kotlin","com.ding.kotlin.services.PrintAidlService")
        bindService(intent,TestAidlConnection(), Context.BIND_AUTO_CREATE)
    }

    inner class TestAidlConnection: ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val aidlInterface = IPrintAidlInterface.Stub.asInterface(service)
            aidlInterface.printText("phone打印姓名===========")
            aidlInterface.printName("我是phone ")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            println("服务断开连接！！！！！")
        }
    }

}