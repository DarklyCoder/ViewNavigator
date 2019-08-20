package com.darklycoder.viewnavigator.impl

import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.interfaces.IPageManager
import com.darklycoder.viewnavigator.interfaces.IPageView
import com.darklycoder.viewnavigator.utils.PagePathUtil

/**
 * 视图管理器
 */
class PageManager : IPageManager {

    private var mPageView: IPageView? = null

    override fun bindPage(pageView: IPageView) {
        this.mPageView = pageView
    }

    override fun goto(intent: ViewIntent) {
        val gotoGroup = PagePathUtil.getGroup(intent.path)
        val curGroup = mPageView?.getGroup()
        // 是同一组的
        if (gotoGroup == curGroup) {
            handleIntent(intent)
            return
        }

        // 非同一组
        mPageView?.goto(gotoGroup, intent)
    }

    private fun handleIntent(intent: ViewIntent) {
        // 判断如何跳转
        when {
            intent.launchMode == ViewIntent.StartMode.Standard -> {
                // 每次都跳转到新界面
                mPageView?.addPage(intent)
            }

            intent.launchMode == ViewIntent.StartMode.SingleTask -> {
                // 如果栈中有此界面，则移到栈顶，否则，新建界面
                if (mPageView?.contain(intent) == true) {
                    mPageView?.moveTop(intent)
                    return
                }

                mPageView?.addPage(intent)
            }

            intent.launchMode == ViewIntent.StartMode.SingleTop -> {
                // 如果有栈中有此界面，且在栈顶，不再显示，否则，新建界面
                if (mPageView?.isTop(intent) == true) {
                    mPageView?.moveTop(intent)
                    return
                }

                mPageView?.addPage(intent)
            }
        }
    }

}