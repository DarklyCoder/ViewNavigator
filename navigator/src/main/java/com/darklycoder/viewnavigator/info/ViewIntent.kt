package com.darklycoder.viewnavigator.info

import android.support.annotation.IntDef
import com.darklycoder.viewnavigator.interfaces.IParams

/**
 * 界面跳转意图
 */
class ViewIntent(
    val path: String, // 路径，与所需渲染的组件一一对应
    var params: IParams? = null, // 携带的参数
    var launchMode: Int = StartMode.Standard,
    val flag: Int = Flags.FLAG_NEW_TASK

) {

    @IntDef(Flags.FLAG_NEW_TASK, Flags.FLAG_CLEAR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Flags {
        companion object {
            private const val FLAG_INDEX = 0x1
            const val FLAG_NEW_TASK = FLAG_INDEX shl 1
            const val FLAG_CLEAR = FLAG_INDEX shl 2
        }
    }

    @IntDef(StartMode.Standard, StartMode.SingleTask, StartMode.SingleTop)
    @Retention(AnnotationRetention.SOURCE)
    annotation class StartMode {
        companion object {
            private const val LAUNCH_MODE = 0x1
            const val Standard = LAUNCH_MODE shl 1
            const val SingleTask = LAUNCH_MODE shl 2
            const val SingleTop = LAUNCH_MODE shl 3
        }
    }

}