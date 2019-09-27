package com.darklycoder.viewnavigator.info

import com.darklycoder.viewnavigator.enums.Flags
import com.darklycoder.viewnavigator.interfaces.IParams

/**
 * 界面跳转意图
 */
class ViewIntent @JvmOverloads constructor(
    val path: String, // 路径，与所需渲染的组件一一对应
    var params: IParams? = null, // 携带的参数
    val flag: Flags = Flags.FLAG_NEW_TASK
)