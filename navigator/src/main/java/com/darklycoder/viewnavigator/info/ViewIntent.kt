package com.darklycoder.viewnavigator.info

import android.animation.AnimatorInflater
import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.darklycoder.viewnavigator.animation.AnimationInfo
import com.darklycoder.viewnavigator.enums.Flags

/**
 * 界面跳转意图
 */
class ViewIntent(
    var path: String // 路径，与所需渲染的组件一一对应
) {

    var params: Any? = null // 携带的参数
    var flag: Flags = Flags.FLAG_NEW_TASK
    var requestCode: Int = -1 // 请求码
    var resultCode: Int = -1 // 回应码
    var result: Bundle? = null // 回应的结果
    var animationInfo: AnimationInfo? = null // 动画

    companion object {

        fun newIntent(path: String, init: (ViewIntent.() -> Unit)? = null): ViewIntent {
            val intent = ViewIntent(path)

            if (null != init) {
                intent.apply(init)
            }

            return intent
        }
    }

    fun loadAnimation(context: Context, resId: Int): AnimationInfo {
        val info = AnimationInfo()
        info.animation = AnimationUtils.loadAnimation(context, resId)
        return info
    }

    fun loadAnimator(context: Context, resId: Int): AnimationInfo {
        val info = AnimationInfo()
        info.animator = AnimatorInflater.loadAnimator(context, resId)
        return info
    }

}