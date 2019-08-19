package com.darklycoder.viewnavigator

import android.content.Context
import com.darklycoder.viewnavigator.info.NavigatorInfo
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.interfaces.IPageView
import com.darklycoder.viewnavigator.utils.PagePathUtil
import java.lang.ref.WeakReference

/**
 * 视图导航器
 */
class ViewNavigator private constructor() {

    // 入口界面
    private var mPageView: IPageView? = null
    private var mContext: WeakReference<Context>? = null

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ViewNavigator()
        }
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param pageView 入口界面
     * @param paths 路径映射
     */
    fun init(context: Context, pageView: IPageView, paths: ArrayList<NavigatorInfo>) {
        this.mContext = WeakReference(context)
        this.mPageView = pageView

        PagePathUtil.init(paths)
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
        mPageView?.getPageManager()?.goto(intent)
    }

    /**
     * 返回
     */
    fun back(): Boolean {
        return mPageView?.getPageManager()?.onBack() ?: false
    }

    /**
     * 根据key关闭指定界面
     */
    fun finishByKey(key: String) {
        mPageView?.getPageManager()?.finishByKey(key)
    }

    /**
     * 关闭所有界面
     */
    fun finish() {
        mPageView?.getPageManager()?.finish()
    }

    /**
     * 显示
     */
    fun onShow() {
        mPageView?.getPageManager()?.onShow()
    }

    /**
     * 隐藏
     */
    fun onHide() {
        mPageView?.getPageManager()?.onHide()
    }

}