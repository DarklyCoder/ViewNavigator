package com.darklycoder.viewnavigator.demo.pages.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.DataParam
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.utils.VLog
import kotlinx.android.synthetic.main.view_page_main.view.*

@Navigator(path = Paths.PATH_MAIN)
class MainView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_main, this, true)
        bindContainerView(fl_container)
        setBackgroundColor(Color.WHITE)

        btn_main1.setOnClickListener { MultiViewNavigator.jump(Paths.PATH_MAIN01) }

        btn_main2.setOnClickListener { MultiViewNavigator.jump(Paths.PATH_MAIN02) }
    }

    override fun onOriginShow(isInit: Boolean, params: Any?) {
        super.onOriginShow(isInit, params)

        VLog.d("isInit: $isInit")

        if (params is DataParam) {
            VLog.d("isInit: $isInit params:${params.type}")
        }
    }

}