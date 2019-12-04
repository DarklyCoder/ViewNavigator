package com.darklycoder.viewnavigator.interfaces

import com.darklycoder.viewnavigator.info.ViewIntent

/**
 * 生命周期
 */
interface ILife {

    /**
     * 可见
     *
     * @param isInit 是否是第一次可见
     * @param intent 传递的参数
     */
    fun onShow(isInit: Boolean = false, intent: ViewIntent? = null)

    /**
     * 不可见
     */
    fun onHide()

    /**
     * 被移除
     */
    fun onRemove()
}