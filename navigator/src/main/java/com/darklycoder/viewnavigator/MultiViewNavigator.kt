package com.darklycoder.viewnavigator

import com.darklycoder.viewnavigator.info.NavigatorInfo
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.utils.PagePathUtil
import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.collections.set

/**
 * 多 ViewNavigator 管理，一个项目中可以有多个 ViewNavigator
 */
class MultiViewNavigator {

    private val mViewNavigator: LinkedHashMap<String, ViewNavigator> = LinkedHashMap()

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MultiViewNavigator()
        }

        const val DEFAULT_TAG = "tag"
    }

    fun initPaths(paths: ArrayList<NavigatorInfo>) {
        PagePathUtil.init(paths)
    }

    fun add(navigator: ViewNavigator, tag: String = DEFAULT_TAG) {
        mViewNavigator[tag] = navigator
    }

    fun goto(pathIndex: String, tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.goto(pathIndex)
    }

    fun goto(intent: ViewIntent, tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.goto(intent)
    }

    fun back(tag: String = DEFAULT_TAG): Boolean {
        return mViewNavigator[tag]?.back() ?: false
    }

    fun onShow(tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.onShow()
    }

    fun onHide(tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.onHide()
    }

    fun finishByKey(key: String, tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.finishByKey(key)
    }

    fun finish(tag: String = DEFAULT_TAG) {
        mViewNavigator[tag]?.finish()
    }

    fun clear() {
        mViewNavigator.entries.forEach { it.value.finish() }
        mViewNavigator.clear()
    }

}