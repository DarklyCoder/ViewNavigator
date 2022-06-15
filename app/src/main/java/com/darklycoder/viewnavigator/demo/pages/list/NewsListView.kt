package com.darklycoder.viewnavigator.demo.pages.list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.DataParam
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.info.ViewIntent
import kotlinx.android.synthetic.main.view_page_news_list.view.*

@Navigator(path = Paths.PATH_LIST)
class NewsListView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_news_list, this, true)
        setBackgroundColor(Color.WHITE)

        val list = generateList()
        list_view.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list)
        list_view.setOnItemClickListener { _, _, position, _ ->
            run {
                MultiViewNavigator.jump(ViewIntent.newIntent(Paths.PATH_DESC) {
                    params = DataParam(1, list[position])
                })
            }
        }
    }

    private fun generateList(): List<String> {
        val list = ArrayList<String>()
        for (i in 1..20) {
            list.add("$i 新闻标题新闻标题新闻标题新闻标题新闻标题")
        }

        return list
    }
}