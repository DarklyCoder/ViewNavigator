package com.darklycoder.viewnavigator.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.ViewNavigator
import com.darklycoder.viewnavigator.ViewPaths
import com.darklycoder.viewnavigator.demo.pages.InitView
import com.darklycoder.viewnavigator.demo.pages.Paths

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initView = InitView(this)
        setContentView(initView)

        MultiViewNavigator.instance.add(ViewNavigator())

        MultiViewNavigator.instance.init(this, initView, ViewPaths.getPaths())
        MultiViewNavigator.instance.goto(Paths.PATH_INDEX)
    }

    override fun onBackPressed() {
        val result = MultiViewNavigator.instance.back()
        if (!result) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        MultiViewNavigator.instance.onShow()
        super.onResume()
    }

    override fun onPause() {
        MultiViewNavigator.instance.onHide()
        super.onPause()
    }

    override fun onDestroy() {
        MultiViewNavigator.instance.finish()
        super.onDestroy()
    }

}
