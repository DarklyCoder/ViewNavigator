package com.darklycoder.viewnavigator

import android.content.Context
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.interfaces.IPageView
import java.lang.ref.WeakReference

/**
 * 视图导航器
 *
 * @param context Context
 * @param pageView 入口界面
 */
class ViewNavigator(context: Context, private var pageView: IPageView?) {

    private var mContext: WeakReference<Context>? = null

    init {
        mContext = WeakReference(context)
    }

    /**
     * 跳转界面
     */
    fun goto(path: String) {
        this.goto(ViewIntent(path))
    }

    /**
     * 跳转界面
     */
    fun goto(intent: ViewIntent) {
        pageView?.getPageManager()?.goto(intent)
    }

    /**
     * 返回
     */
    fun back(): Boolean {
        val ret = pageView?.back() ?: false
        if (ret) {
            pageView?.onShow()
        }

        return ret
    }

    /**
     * 根据key关闭指定界面
     */
    fun finishByKey(key: String) {
        val ret = pageView?.finishByKey(key) ?: false
        if (ret) {
            pageView?.onShow()
        }
    }

    /**
     * 关闭所有界面
     */
    fun finish() {
        pageView?.onRemove()
        mContext?.clear()
        pageView = null
        mContext = null
    }

    /**
     * 显示
     */
    fun onShow() {
        pageView?.onShow(false)
    }

    /**
     * 隐藏
     */
    fun onHide() {
        pageView?.onHide()
    }

}