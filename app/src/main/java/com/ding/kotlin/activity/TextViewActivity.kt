package com.ding.kotlin.activity

import android.graphics.Color
import com.ding.kotlin.R
import com.ding.kotlin.databinding.ActivityTextViewLayoutBinding

class TextViewActivity : BaseActivity<ActivityTextViewLayoutBinding>() {

    companion object{
        private const val MAX_STR = "start--我是多行文字1我是多行文字2我是多行文字3我是多行文字4我是多行文字5我是多行文字6我是多行文字7我是多行文字8我是多行文字9我是多行文字10我是多行文字11我是多行文字12我是多行文字13我是多行文字14我是多行文字15我是多行文字16我是多行文字17我是多行文字18我是多行文字19我是多行文字20我是多行文字21我是多行文字22我是多行文字23我是多行文字--end"
    }

    override fun initView() {
        mDB.exTvText.post {
            mDB.exTvText.text = "我是多行文字我是多行文字我是多行文字我是多行文字我是多行文字我是多行文字我是多行文字我是多行文字"
        }
        mDB.expandedText.initWidth(500)
        mDB.expandedText.maxLines = 2
        mDB.expandedText.setHasAnimation(true)
        mDB.expandedText.setCloseInNewLine(true)
        mDB.expandedText.setOpenSuffixColor(Color.BLUE)
        mDB.expandedText.setCloseSuffixColor(Color.RED)
        mDB.expandedText.setOriginalText(
            """
                在全球，随着Flutter被越来越多的知名公司应用在自己的商业APP中，Flutter这门新技术也逐渐进入了移动开发者的视野，尤其是当Google在2018年IO大会上发布了第一个Preview版本后，国内刮起来一股学习Flutter的热潮。
                
                为了更好的方便帮助中国开发者了解这门新技术，我们，Flutter中文网，前后发起了Flutter翻译计划、Flutter开源计划，前者主要的任务是翻译Flutter官方文档，后者则主要是开发一些常用的包来丰富Flutter生态，帮助开发者提高开发效率。而时至今日，这两件事取得的效果还都不错！
                """.trimIndent()
        )

        mDB.expandedTextSelf.maxLines = 2
        mDB.expandedTextSelf.setSuffixText("收起","展开")
            .setSuffixTextColor(Color.BLACK)
//            .setSuffixImageResource(R.drawable.icon_arrow_up,R.drawable.icon_arrow_down)
            .setSuffixImageResource(R.drawable.icon_arrow_up,0)
            .setOriginText(MAX_STR)
    }

}