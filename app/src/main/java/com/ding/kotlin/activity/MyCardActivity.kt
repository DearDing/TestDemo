package com.ding.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ding.kotlin.MainActivity
import com.ding.kotlin.R
import com.ding.kotlin.adapter.CardAdapter
import com.ding.kotlin.adapter.CardVPAdapter
import com.ding.kotlin.bean.CardBean
import com.ding.kotlin.transformer.MyCardTransformer
import com.ding.kotlin.transformer.PageTransformerConfig
import com.ding.kotlin.views.MyTextView
import com.lin.cardlib.CardLayoutManager
import com.lin.cardlib.CardSetting
import com.lin.cardlib.CardTouchHelperCallback
import com.lin.cardlib.utils.ReItemTouchHelper


class MyCardActivity : AppCompatActivity() {

    private lateinit var mVpView: ViewPager2
    private var mAdapter: CardVPAdapter? = null

    private lateinit var mRvView: RecyclerView
    private var mRvAdapter: CardAdapter? = null
    private var mHelperCallback: CardTouchHelperCallback<CardBean>? = null

    private lateinit var mBtn: Button
    private lateinit var mBtnVisible: Button
    private lateinit var mText:MyTextView
    private var isVisible:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_card)
        mVpView = findViewById(R.id.vp_view)
        mRvView = findViewById(R.id.rv_view)
        mBtn = findViewById(R.id.bt_update)
        mText = findViewById(R.id.tv_text)
        mBtnVisible = findViewById(R.id.bt_visible)
        initViewPager()
        initRecyclerView()
        initListener()
    }

    private fun initListener() {
        mBtn.setOnClickListener {
            if(isVisible){
                mText.visibility = View.GONE
            }else{
                mText.visibility = View.VISIBLE
            }
            isVisible = !isVisible
            updateList()
        }
        mText.setOnClickListener {
            startActivity(Intent(this@MyCardActivity,MainActivity::class.java))
        }
        mBtnVisible.setOnClickListener {
            val visibility = mText.visibility
            if(visibility == View.VISIBLE){
                mText.visibility = View.GONE
            }else{
                mText.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        val list: ArrayList<CardBean> = ArrayList()
        list.add(
            CardBean(
                "https://copyright.bdstatic.com/vcg/creative/cc9c744cf9f7c864889c563cbdeddce6.jpg@h_1280",
                "1 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://pic.rmb.bdstatic.com/bjh/914b8c0f9814b14c5fedeec7ec6615df5813.jpeg",
                "2 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://pic.rmb.bdstatic.com/bjh/914b8c0f9814b14c5fedeec7ec6615df5813.jpeg",
                "3 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://i0.hdslb.com/bfs/archive/609b9b5611900fad4b1f505687b7fe6b8a3442ad.jpg",
                "4 card",
                ""
            )
        )
        val setting = CardSetting()
        mHelperCallback = CardTouchHelperCallback(mRvView, list, setting)
        val mReItemTouchHelper = ReItemTouchHelper(mHelperCallback)
        val layoutManager = CardLayoutManager(mReItemTouchHelper, setting)
        mRvView.layoutManager = layoutManager
        mRvAdapter = CardAdapter(list)
        mRvView.adapter = mRvAdapter
    }

    private fun updateList() {
        val list: ArrayList<CardBean> = ArrayList()
        list.add(
            CardBean(
                "https://copyright.bdstatic.com/vcg/creative/cc9c744cf9f7c864889c563cbdeddce6.jpg@h_1280",
                "4 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://pic.rmb.bdstatic.com/bjh/914b8c0f9814b14c5fedeec7ec6615df5813.jpeg",
                "5 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://pic.rmb.bdstatic.com/bjh/914b8c0f9814b14c5fedeec7ec6615df5813.jpeg",
                "6 card",
                ""
            )
        )
        list.add(
            CardBean(
                "https://i0.hdslb.com/bfs/archive/609b9b5611900fad4b1f505687b7fe6b8a3442ad.jpg",
                "7 card",
                ""
            )
        )
        mHelperCallback?.updateDataList(list)
        mRvAdapter?.updateList(list)
    }

    private fun initViewPager() {
        mAdapter = CardVPAdapter(this)
        mVpView.offscreenPageLimit = 2
//        mVpView.setPageTransformer(MarginPageTransformer(50))
        mVpView.setPageTransformer(getTransformer2())
        mVpView.adapter = mAdapter
    }

    private fun getTransformer1(): ViewPager2.PageTransformer {
        return MyCardTransformer.getBuild() //建造者模式
            // .addAnimationType(PageTransformerConfig.ROTATION)//默认动画  旋转  当然 也可以一次性添加两个  后续会增加更多动画
            .setRotation(-45f) //旋转角度
            // .addAnimationType(PageTransformerConfig.ALPHA)//默认动画 透明度 暂时还有问题
            .setViewType(PageTransformerConfig.TOP)
            .setOnPageTransformerListener { page, position ->
                //你也可以在这里对 page 实行自定义动画
            }
            .setTranslationOffset(40)
            .setScaleOffset(80)
            .create()
    }

    val mScaleOffset = 200f
    val mTranslationOffset = 100f

    private fun getTransformer2(): ViewPager2.PageTransformer {
        return ViewPager2.PageTransformer { page, position ->
            if (position <= 0F) {
                page.translationX = 0F
            } else {
                val pageWidth: Int = page.width
                val transX = -pageWidth * position + mTranslationOffset * position
                page.translationX = transX
                // 缩放比例
                val scale: Float = (pageWidth - mScaleOffset * position) / pageWidth.toFloat()
                page.scaleX = scale
                page.scaleY = scale
                // 控制展示层级
                page.translationZ = -position
            }
        }
    }

    private fun getTransformer3(): ViewPager2.PageTransformer {
        return ViewPager2.PageTransformer { page, position ->
//            if (position <= 0.0f) {
//                page.setAlpha(1.0f)
//                //出现卡片抽动效果的关键代码
//                page.setTranslationY(0f)
//            } else {
//                float scale = (float) (page.getWidth() - ScreenUtils.dp2px(context, 20 * position)) / (float) (page.getWidth())
//                page.setAlpha(1.0f)
//                page.setPivotX(page.getWidth() / 2f)
//                page.setPivotY(page.getHeight() / 2f)
//                page.setScaleX(scale)
//                page.setScaleY(scale)
//                //修改过的代码
//                page.setTranslationY(-page.getHeight() * position + (page.getHeight() * 0.5f) * (1 - scale) + ScreenUtils.dp2px(context, 10) * position)
//            }
        }
    }
}