package com.ding.kotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ding.kotlin.views.TestTouchLayout
import com.ding.kotlin.views.TestTouchView

class TestTouchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view1 = TestTouchView(this)
        view1.apply { touchEnable = true }
        val view2 = TestTouchView(this)
        val view3 = TestTouchView(this)
        val view4 = TestTouchView(this)
        val view5 = TestTouchView(this)
        val view6 = TestTouchView(this)
        val view7 = TestTouchView(this)
        val view8 = TestTouchView(this)
        val view9 = TestTouchView(this)

        val vp1 = TestTouchLayout(this)
        vp1.addView(view1)
        vp1.addView(view2)
        vp1.addView(view3)

        val vp2 = TestTouchLayout(this)
        vp2.addView(view4)
        vp2.addView(view5)
        vp2.addView(view6)

        val vp3 = TestTouchLayout(this)
        vp3.addView(view7)
        vp3.addView(view8)
        vp3.addView(view9)

        val vpRoot = TestTouchLayout(this)
        //vpRoot.apply { interceptHalfEnable = true }
        vpRoot.addView(vp1)
        vpRoot.addView(vp2)
        vpRoot.addView(vp3)

        setContentView(vpRoot)

        view1.name = "view1"
        view2.name = "view2"
        view3.name = "view3"
        view4.name = "view4"
        view5.name = "view5"
        view6.name = "view6"
        view7.name = "view7"
        view8.name = "view8"
        view9.name = "view9"
        vpRoot.name ="vp_root"
        vp1.name ="vp1"
        vp2.name ="vp2"
        vp3.name ="vp3"

    }

}