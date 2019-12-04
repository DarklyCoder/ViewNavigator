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
     * 获取视图层级
     */
    fun getDeep(): Int

    fun getIntent(): ViewIntent?

    /**
     * 请求数据回调
     */
    fun onResult(requestCode: Int, resultCode: Int, intent: ViewIntent? = null)

    /**
     * 请求权限回调
     */
    fun onPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

    /**
     * 返回
     */
    fun back(): Boolean

    /**
     * 关闭指定界面
     */
    fun finishByKey(key: String): ViewIntent?

    /**
     * 跳转到新界面
     */
    fun jump(intent: ViewIntent)

}