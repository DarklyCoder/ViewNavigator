package com.darklycoder.viewnavigator.demo.pages.dialog

import android.content.Context
import android.view.LayoutInflater
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import kotlinx.android.synthetic.main.view_page_dialog.view.*

@Navigator(path = Paths.PATH_DIALOG)
class DialogView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_dialog, this, true)

        btn_close.setOnClickListener { MultiViewNavigator.finishByKey(Paths.PATH_DIALOG) }
    }

}