package com.darklycoder.viewnavigator.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darklycoder.viewnavigator.ViewNavigator
import com.darklycoder.viewnavigator.ViewPaths
import com.darklycoder.viewnavigator.demo.pages.InitView
import com.darklycoder.viewnavigator.demo.pages.Paths

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initView = InitView(this)
        setContentView(initView)

        ViewNavigator.instance.init(this, initView, ViewPaths.getPaths())

        ViewNavigator.instance.goto(Paths.PATH_INDEX)
    }

    override fun onBackPressed() {
        val result = ViewNavigator.instance.back()
        if (!result) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        ViewNavigator.instance.onShow()
        super.onResume()
    }

    override fun onPause() {
        ViewNavigator.instance.onHide()
        super.onPause()
    }

    override fun onDestroy() {
        ViewNavigator.instance.finish()
        super.onDestroy()
    }

}
