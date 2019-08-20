package com.darklycoder.viewnavigator.demo.pages.index

import android.content.Context
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import kotlinx.android.synthetic.main.view_page_index.view.*

@Navigator(path = Paths.PATH_INDEX)
class IndexView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_index, this, true)
        bindContainerView(fl_container)

        btn_main.setOnClickListener { MultiViewNavigator.instance.goto(Paths.PATH_MAIN) }
        btn_about.setOnClickListener { MultiViewNavigator.instance.goto(Paths.PATH_ABOUT) }
        btn_close.setOnClickListener { MultiViewNavigator.instance.finish() }
        btn_close_about.setOnClickListener { MultiViewNavigator.instance.finishByKey(Paths.PATH_ABOUT) }
    }

}