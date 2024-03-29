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

        pageView?.onShow(true)
    }

    /**
     * 跳转界面
     */
    fun jump(path: String) {
        this.jump(ViewIntent(path))
    }

    /**
     * 跳转界面
     */
    fun jump(intent: ViewIntent) {
        pageView?.jump(intent)
    }

    /**
     * 返回
     */
    fun back(minDeep: Int = 2): Boolean {
        val deep = (pageView?.getDeep() ?: 0) + 1
        if (deep <= minDeep) {
            return false
        }

        val ret = pageView?.back() ?: false
        if (ret) {
            pageView?.onShow()
        }

        return ret
    }

    /**
     * 根据key关闭指定界面
     */
    fun finishByKey(vararg keys: String) {
        var viewIntent: ViewIntent? = null
        keys.forEach {
            viewIntent = pageView?.finishByKey(it)
        }

        pageView?.onShow()

        val intent = viewIntent

        if (null != intent && -1 != intent.requestCode) {
            pageView?.onResult(intent.requestCode, intent.resultCode, intent)
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
        pageView?.onShow()
    }

    /**
     * 隐藏
     */
    fun onHide() {
        pageView?.onHide()
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        pageView?.onPermissionResult(requestCode, permissions, grantResults)
    }

}