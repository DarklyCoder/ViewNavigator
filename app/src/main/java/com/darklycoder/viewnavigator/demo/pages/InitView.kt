package com.darklycoder.viewnavigator.demo.pages

import android.content.Context
import android.graphics.Color
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.impl.PageView

/**
 * 初始界面
 */
@Navigator(path = Paths.PATH_INIT)
class InitView(context: Context) : PageView(context) {

    init {
        bindContainerView(this)
        setBackgroundColor(Color.WHITE)
    }

}