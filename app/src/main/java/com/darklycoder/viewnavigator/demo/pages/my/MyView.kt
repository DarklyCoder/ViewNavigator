package com.darklycoder.viewnavigator.demo.pages.my

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import kotlinx.android.synthetic.main.view_page_about.view.*

@Navigator(path = Paths.PATH_MY)
class MyView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_my, this, true)
        setBackgroundColor(Color.WHITE)

    }
}