package com.darklycoder.viewnavigator.interfaces

import android.view.ViewGroup
import com.darklycoder.viewnavigator.info.ViewIntent

/**
 * 界面组件
 */
interface IPageView : ILife {

    /**
     * 绑定子容器视图
     */
    fun bindContainerView(containerView: ViewGroup)

    /**
     * 获取绑定的子容器视图
     */
    fun getContainerView(): ViewGroup?

    /**
     * 绑定界面管理器
     */
    fun bindPageManager(pageManager: IPageManager)

    /**
     * 获取界面管理器
     */
    fun getPageManager(): IPageManager?

    /**
     * 获取当前最上面的视图
     */
    fun getTopView(): Pair<String, IPageView?>?

    /**
     * 是否含有此次界面
     */
    fun contain(intent: ViewIntent): Boolean

    /**
     * 是否是栈顶
     */
    fun isTop(intent: ViewIntent): Boolean

    /**
     * 移动至栈顶
     */
    fun moveTop(intent: ViewIntent)

    /**
     * 添加界面
     *
     * @param path 路径
     */
    fun addPage(intent: ViewIntent)

}