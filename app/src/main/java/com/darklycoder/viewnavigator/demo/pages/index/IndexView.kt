package com.darklycoder.viewnavigator.demo.pages.index

import android.content.Context
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.DataParam
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.info.ViewIntent
import kotlinx.android.synthetic.main.view_page_index.view.*

@Navigator(path = Paths.PATH_INDEX)
class IndexView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_index, this, true)
        bindContainerView(fl_container)

        btn_main.setOnClickListener {
            MultiViewNavigator.jump(ViewIntent.newIntent(Paths.PATH_MAIN) {
                params = DataParam(1)
            })
        }
        btn_about.setOnClickListener {
            MultiViewNavigator.jump(Paths.PATH_ABOUT)
            MultiViewNavigator.jump(Paths.PATH_DIALOG)
        }
        btn_close.setOnClickListener { MultiViewNavigator.finish() }
        btn_close_about.setOnClickListener { MultiViewNavigator.finishByKey(Paths.PATH_ABOUT) }
    }

}