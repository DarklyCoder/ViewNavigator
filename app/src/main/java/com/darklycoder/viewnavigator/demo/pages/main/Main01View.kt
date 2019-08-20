package com.darklycoder.viewnavigator.demo.pages.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView

@Navigator(path = Paths.PATH_MAIN01)
class Main01View(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_main01, this, true)
        setBackgroundColor(Color.WHITE)
    }
}