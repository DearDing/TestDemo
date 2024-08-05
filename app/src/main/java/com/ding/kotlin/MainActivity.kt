package com.ding.kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ding.kotlin.activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRvView: RecyclerView
    private val textArr = arrayListOf("仿探探卡片", "搞懂Activity", "搞懂Fragment", "搞懂CoroutineScope", "aidl使用","事件分发","折叠文本","折叠文本1","FlexboxLayout")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRvView = findViewById(R.id.rv_view)
        mRvView.layoutManager = GridLayoutManager(this, 1)
        mRvView.adapter = createAdapter()
    }

    private fun createAdapter(): RecyclerView.Adapter<ItemHolder> {
        return object : RecyclerView.Adapter<ItemHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
                return ItemHolder(
                    LayoutInflater.from(this@MainActivity)
                        .inflate(R.layout.item_main_layout, parent, false)
                )
            }

            override fun getItemCount(): Int {
                return textArr.size
            }

            override fun onBindViewHolder(holder: ItemHolder, position: Int) {
                holder.itemView.tag = position
                holder.mText.text = textArr[position]
            }
        }
    }

    fun onItemClickEvent(position: Int) {
        when (position) {
            0 -> {
                startActivity(Intent(this, MyCardActivity::class.java))
            }
            1 -> {
                startActivity(Intent(this, TestActActivity::class.java))
            }
            2 -> {
                startActivity(Intent(this, TestFragActivity::class.java))
            }
            3 -> {
                startActivity(Intent(this, TestCoroutineActivity::class.java))
            }
            4 -> {
                startActivity(Intent(this, TestAidlActivity::class.java))
            }
            5 -> {
                startActivity(Intent(this, TestTouchActivity::class.java))
            }
            6->{
                startActivity(Intent(this, TextViewActivity::class.java))
            }
            7->{
                startActivity(Intent(this, TextView1Activity::class.java))
            }
            8->{
                startActivity(Intent(this, TestFlexBoxLayoutActivity::class.java))
            }
        }
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mText: TextView = view.findViewById(R.id.tv_text)

        init {
            itemView.setOnClickListener {
                var position = itemView.tag
                if (position is Int) {
                    onItemClickEvent(position)
                }
            }
        }
    }

}