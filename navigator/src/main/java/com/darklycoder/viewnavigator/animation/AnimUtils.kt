package com.darklycoder.viewnavigator.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.animation.Animation
import com.darklycoder.viewnavigator.utils.VLog

/**
 * 动画工具类
 */
object AnimUtils {

    /**
     * 执行动画
     */
    fun runAnim(animationInfo: AnimationInfo?, view: View, endAction: () -> Unit) {
        if (null == animationInfo || !animationInfo.valid()) {
            VLog.d("动画信息为空，不执行动画！")
            endAction()
            return
        }

        animationInfo.animation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                endAction()

                animation?.setAnimationListener(null)
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        animationInfo.animator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                endAction()
                animationInfo.animator?.removeListener(this)
            }
        })

        if (null != animationInfo.animation) {
            view.startAnimation(animationInfo.animation)
        }

        if (null != animationInfo.animator) {
            animationInfo.animator?.setTarget(view)
            animationInfo.animator?.start()
        }
    }

}