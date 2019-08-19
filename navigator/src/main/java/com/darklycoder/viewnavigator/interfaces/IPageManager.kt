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
    fun goto(intent: ViewIntent)

    /**
     * 回退
     */
    fun onBack(): Boolean

    /**
     * 显示
     */
    fun onShow()

    /**
     * 隐藏
     */
    fun onHide()

    /**
     * 根据key关闭指定界面
     */
    fun finishByKey(key: String)

    /**
     * 关闭
     */
    fun finish()
}