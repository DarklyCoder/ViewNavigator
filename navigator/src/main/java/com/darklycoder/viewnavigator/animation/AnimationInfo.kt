package com.darklycoder.viewnavigator.animation

import android.animation.Animator
import android.view.animation.Animation

/**
 * 动画信息
 */
class AnimationInfo {

    var animation: Animation? = null // 帧动画
    var animator: Animator? = null // 属性动画

    companion object {

        fun newAnimation(init: AnimationInfo.() -> Unit): AnimationInfo {
            val animation = AnimationInfo()
            animation.apply(init)
            return animation
        }
    }
}