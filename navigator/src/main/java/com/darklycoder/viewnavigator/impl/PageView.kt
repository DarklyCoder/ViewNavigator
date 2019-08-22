package com.darklycoder.viewnavigator.impl

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import com.darklycoder.viewnavigator.enums.Flags
import com.darklycoder.viewnavigator.enums.PageStatus
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

    private var status = PageStatus.UNKNOWN

    init {
        isClickable = true
        isFocusable = true
    }

    // 存放子界面
    private var mContainerView: ViewGroup? = null
    // 存放 mContainerView 里显示的界面
    private var mPages: ArrayList<Pair<String, IPageView>> = ArrayList()
    // 管理子界面切换
    private var mPageManager: IPageManager? = null

    override fun bindContainerView(containerView: ViewGroup) {
        this.mContainerView = containerView
        bindPageManager(PageManager())
    }

    override fun getGroup(): String? {
        return PagePathUtil.findGroup(this::class.java.name, null != mContainerView)
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
        if (status != PageStatus.SHOW) {
            onOriginShow(isInit, params)
        }
        status = PageStatus.SHOW

        if (!isInit) {
            getTopView()?.second?.onShow(false)
        }
    }

    open fun onOriginShow(isInit: Boolean, params: IParams?) {
        VLog.d("onShow: ${javaClass.simpleName}")
    }

    override fun onHide() {
        status = PageStatus.HIDE
        VLog.d("onHide: ${javaClass.simpleName}")
        getTopView()?.second?.onHide()
    }

    override fun onRemove() {
        status = PageStatus.REMOVE
        // 清空子界面
        VLog.d("onRemove: ${javaClass.simpleName}")
        clear()
    }

    /**
     * 处理返回事件，返回 true 表示事件已消费
     */
    override fun back(): Boolean {
        if (mPages.isNullOrEmpty()) {
            return false
        }

        val last = mPages.last()
        if (last.second.back()) {
            return true
        }

        closeItem(last)

        return true
    }

    /**
     * 关闭指定界面
     */
    override fun finishByKey(key: String): Boolean {
        if (mPages.isNullOrEmpty()) {
            return false
        }

        mPages.reversed().forEach {
            if (key == it.first) {
                closeItem(it)
                return true

            } else {
                if (it.second.finishByKey(key)) {
                    return true
                }
            }
        }

        return false
    }

    private fun closeItem(item: Pair<String, IPageView>) {
        try {
            val pageView = item.second

            if (pageView is PageView) {
                VLog.d("closeItem: ${javaClass.simpleName} -> ${pageView::class.java.simpleName}")

                pageView.onRemove()
                mPages.remove(item)
                getContainerView()?.removeView(pageView)
            }

        } catch (e: Exception) {
            VLog.e(e)
        }

    }

    override fun getTopView(): Pair<String, IPageView?>? {
        if (mPages.isNullOrEmpty()) {
            return null
        }

        return mPages.last()
    }

    override fun contain(intent: ViewIntent): Boolean {
        return null != findPageByPath(intent.path)
    }

    override fun isTop(intent: ViewIntent): Boolean {
        return contain(intent) && mPages.last().first == intent.path
    }

    override fun moveTop(intent: ViewIntent) {
        if (intent.flag == Flags.FLAG_CLEAR) {
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
        val item = findPageByPath(intent.path) ?: return
        mPages.remove(item)
        mPages.add(item)
        val pageView = item.second

        if (pageView is PageView) {
            pageView.bringToFront()
        }

        topView?.second?.onHide()
        pageView.onShow(false, intent.params)
    }

    override fun addPage(intent: ViewIntent) {
        if (intent.flag == Flags.FLAG_CLEAR) {
            clear()
        }

        val pageView = PagePathUtil.getPageView(context, intent.path) ?: return
        val topView = getTopView()
        mPages.add(Pair(intent.path, pageView))

        try {
            // TODO 动画处理
            topView?.second?.onHide()
            getContainerView()?.addView(pageView)
            VLog.d("addPage: ${javaClass.simpleName} -> ${pageView::class.java.simpleName}")

            pageView.onShow(true, intent.params)

        } catch (e: Exception) {
            VLog.e(e)
        }
    }

    /**
     * 跳转子界面
     */
    override fun goto(gotoGroup: String, intent: ViewIntent) {
        mPages.forEach {
            if (gotoGroup.contains(it.first)) {
                it.second.getPageManager()?.goto(intent)
            }
        }
    }

    /**
     * 清除所有子界面
     */
    private fun clear() {
        try {
            mPages.forEach { it.second.onRemove() }
            mPages.clear()
            getContainerView()?.removeAllViews()

        } catch (e: Exception) {
            VLog.e(e)
        }
    }

    private fun clearByPath(path: String) {
        var findIndex = -1
        val delList = ArrayList<Pair<String, IPageView>>()
        mPages.reversed().forEachIndexed { index, item ->
            if (findIndex == -1 && item.first == path) {
                findIndex = index
            }

            if (findIndex != -1 && findIndex != index) {
                delList.add(item)
            }
        }

        delList.forEach {
            val pageView = it.second
            if (pageView is PageView) {
                pageView.onRemove()
                mPages.remove(it)
                getContainerView()?.removeView(pageView)
            }
        }
    }

    private fun findPageByPath(path: String): Pair<String, IPageView>? {
        mPages.reversed().forEach {
            if (it.first == path) {
                return it
            }
        }

        return null
    }

}