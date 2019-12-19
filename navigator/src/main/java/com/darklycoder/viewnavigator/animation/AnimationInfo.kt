package com.darklycoder.viewnavigator.animation

import android.animation.Animator
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils

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

    fun valid(): Boolean {
        return null != animation || null != animator
    }
}