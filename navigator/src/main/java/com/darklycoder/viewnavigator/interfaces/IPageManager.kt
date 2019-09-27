package com.darklycoder.viewnavigator.interfaces

import com.darklycoder.viewnavigator.info.ViewIntent

/**
 * Page管理器接口，管理Page的子界面切换
 */
interface IPageManager {

    /**
     * 绑定视图
     */
    fun bindPage(pageView: IPageView)

    /**
     * 跳转到新界面
     */
    fun jump(intent: ViewIntent)

}