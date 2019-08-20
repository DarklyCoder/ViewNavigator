package com.darklycoder.viewnavigator.demo.pages.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.ViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import kotlinx.android.synthetic.main.view_page_main.view.*

@Navigator(path = Paths.PATH_MAIN)
class MainView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_main, this, true)
        bindContainerView(fl_container)
        setBackgroundColor(Color.WHITE)

        btn_main1.setOnClickListener { ViewNavigator.instance.goto(Paths.PATH_MAIN01) }

        btn_main2.setOnClickListener { ViewNavigator.instance.goto(Paths.PATH_MAIN02) }
    }

}