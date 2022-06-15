package com.darklycoder.viewnavigator.demo.pages.index

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.DataParam
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.interfaces.IPageChange
import kotlinx.android.synthetic.main.view_page_index.view.*

@Navigator(path = Paths.PATH_INDEX)
class IndexView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_index, this, true)
        bindContainerView(fl_container)

        // 主页
        btn_main.setOnClickListener {
            selectTab(0)
            MultiViewNavigator.jump(ViewIntent.newIntent(Paths.PATH_MAIN) {
                params = DataParam(1)
            })
        }
        // 列表
        btn_list.setOnClickListener {
            selectTab(1)
            MultiViewNavigator.jump(Paths.PATH_LIST)
//            MultiViewNavigator.jump(Paths.PATH_DIALOG)
        }
        // 我的
        btn_myself.setOnClickListener {
            selectTab(2)
            MultiViewNavigator.jump(Paths.PATH_MY)
        }
        btn_close.setOnClickListener { MultiViewNavigator.finish() }
        btn_close_about.setOnClickListener { MultiViewNavigator.finishByKey(Paths.PATH_ABOUT) }
    }

    override fun onOriginShow(isInit: Boolean, params: Any?) {
        super.onOriginShow(isInit, params)
        if (isInit) {
            // 默认显示主页
            btn_main.performClick()
        }
    }

    private fun selectTab(index: Int) {
        btn_main.setTextColor(if (0 == index) Color.RED else Color.BLACK)
        btn_list.setTextColor(if (1 == index) Color.RED else Color.BLACK)
        btn_myself.setTextColor(if (2 == index) Color.RED else Color.BLACK)
    }

}