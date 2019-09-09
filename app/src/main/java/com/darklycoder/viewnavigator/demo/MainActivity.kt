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

        // 初始化路径
        MultiViewNavigator.initPaths(ViewPaths.getPaths())

        // 添加导航器
        MultiViewNavigator.add(ViewNavigator(this, initView))

        // 跳转界面
        MultiViewNavigator.goto(Paths.PATH_INDEX)
    }

    override fun onBackPressed() {
        val result = MultiViewNavigator.back(2)
        if (!result) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        MultiViewNavigator.onShow()
        super.onResume()
    }

    override fun onPause() {
        MultiViewNavigator.onHide()
        super.onPause()
    }

    override fun onDestroy() {
        MultiViewNavigator.finish()
        super.onDestroy()
    }

}
