package com.darklycoder.viewnavigator.impl

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.interfaces.IPageManager
import com.darklycoder.viewnavigator.interfaces.IPageView
import com.darklycoder.viewnavigator.interfaces.IParams
import com.darklycoder.viewnavigator.utils.PagePathUtil
import com.darklycoder.viewnavigator.utils.VLog

/**
 * 页面组件
 */
open class PageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IPageView {

    init {
        isClickable = true
        isFocusable = true
        setBackgroundColor(Color.WHITE)
    }

    // 存放子界面
    private var mContainerView: ViewGroup? = null
    // 存放 mContainerView 里显示的界面
    private var mPages: LinkedHashMap<String, IPageView> = LinkedHashMap()
    // 管理子界面切换
    private var mPageManager: IPageManager? = null

    override fun bindContainerView(containerView: ViewGroup) {
        this.mContainerView = containerView
        bindPageManager(PageManager())
    }

    override fun getContainerView(): ViewGroup? {
        return mContainerView
    }

    override fun bindPageManager(pageManager: IPageManager) {
        this.mPageManager = pageManager
        this.mPageManager?.bindPage(this)
    }

    override fun getPageManager(): IPageManager? {
        return mPageManager
    }

    override fun onShow(isInit: Boolean, params: IParams?) {
        VLog.d("onShow: ${javaClass::class.java.simpleName}")

    }

    override fun onHide() {
        VLog.d("onHide: ${javaClass::class.java.simpleName}")
        // TODO 最上层同步隐藏
    }

    override fun onRemove() {
        // 清空子界面
        VLog.d("onRemove: ${javaClass.simpleName}")
        clear()
    }

    override fun getTopView(): Pair<String, IPageView?>? {
        if (mPages.isNullOrEmpty()) {
            return null
        }

        val key = mPages.keys.last()
        return Pair(key, mPages[key])
    }

    override fun contain(intent: ViewIntent): Boolean {
        return null != mPages[intent.path]
    }

    override fun isTop(intent: ViewIntent): Boolean {
        return contain(intent) && mPages.keys.last() == intent.path
    }

    override fun moveTop(intent: ViewIntent) {
        if (intent.flag == ViewIntent.Flags.FLAG_CLEAR) {
            // 清除当前操作界面的以外界面
            clearByPath(intent.path)
        }

        val topView = getTopView()
        if (topView?.first == intent.path) {
            // 当前界面已在栈顶
            topView.second?.onShow(false, intent.params)
            return
        }

        // 移到栈顶
        val pageView = mPages[intent.path] ?: return
        mPages.remove(intent.path)
        mPages[intent.path] = pageView

        if (pageView is PageView) {
            pageView.bringToFront()
        }

        topView?.second?.onHide()
        pageView.onShow(false, intent.params)
    }

    override fun addPage(intent: ViewIntent) {
        if (intent.flag == ViewIntent.Flags.FLAG_CLEAR) {
            clear()
        }

        val pageView = PagePathUtil.getPageView(context, intent.path) ?: return
        val topView = getTopView()
        mPages[intent.path] = pageView

        try {
            // TODO 动画处理
            topView?.second?.onHide()
            getContainerView()?.addView(pageView)
            pageView.onShow(true, intent.params)

            VLog.d("addPage: ${pageView::class.java.simpleName}")

        } catch (e: Exception) {
            VLog.e(e)
        }
    }

    private fun clear() {
        mPages.values.forEach { it.onRemove() }
        mPages.clear()
        getContainerView()?.removeAllViews()
    }

    private fun clearByPath(path: String) {
        var findIndex = -1
        val delList = ArrayList<String>()
        mPages.keys.reversed().forEachIndexed { index, key ->
            if (key == path) {
                findIndex = index
            }

            if (findIndex != -1 && findIndex != index) {
                delList.add(key)
            }
        }

        delList.forEach {
            val pageView = mPages[it]
            pageView?.onRemove()
            mPages.remove(it)
            if (pageView is PageView) {
                getContainerView()?.removeView(pageView)
            }
        }
    }

}