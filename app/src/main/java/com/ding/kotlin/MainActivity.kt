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
import com.ding.kotlin.activity.MyCardActivity
import com.ding.kotlin.activity.TestActActivity
import com.ding.kotlin.activity.TestCoroutineActivity
import com.ding.kotlin.activity.TestFragActivity
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var mRvView: RecyclerView
    private val textArr = arrayListOf("仿探探卡片", "搞懂Activity", "搞懂Fragment", "搞懂CoroutineScope")

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