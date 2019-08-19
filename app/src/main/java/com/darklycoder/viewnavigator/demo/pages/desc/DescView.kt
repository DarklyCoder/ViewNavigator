package com.darklycoder.viewnavigator.demo.pages.desc

import android.content.Context
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView

@Navigator(path = Paths.PATH_DESC)
class DescView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_desc, this, true)
    }
}