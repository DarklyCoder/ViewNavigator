package com.darklycoder.viewnavigator.demo.pages.desc

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import kotlinx.android.synthetic.main.view_page_desc.view.*

@Navigator(path = Paths.PATH_DESC)
class DescView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_desc, this, true)
        setBackgroundColor(Color.WHITE)

        mBtnTest.setOnClickListener {
            // 模拟回调数据
            val bundle = Bundle()
            bundle.putInt("data", 2)
            setOnResult(0, bundle)

            MultiViewNavigator.finishByKey(Paths.PATH_DESC)
        }
    }
}