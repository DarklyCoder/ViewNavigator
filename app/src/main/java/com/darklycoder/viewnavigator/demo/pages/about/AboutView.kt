package com.darklycoder.viewnavigator.demo.pages.about

import android.Manifest
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import com.darklycoder.viewnavigator.MultiViewNavigator
import com.darklycoder.viewnavigator.annotation.Navigator
import com.darklycoder.viewnavigator.demo.R
import com.darklycoder.viewnavigator.demo.pages.Paths
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.info.ViewIntent
import com.darklycoder.viewnavigator.utils.VLog
import kotlinx.android.synthetic.main.view_page_about.view.*

@Navigator(path = Paths.PATH_ABOUT)
class AboutView(context: Context) : PageView(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_page_about, this, true)
        setBackgroundColor(Color.WHITE)

        btn_desc.setOnClickListener {
            MultiViewNavigator.jump(ViewIntent.newIntent(Paths.PATH_DESC) {
                requestCode = 666
                animationInfo = loadAnimation(context, R.anim.slide_in_from_right)
//                animationInfo = loadAnimator(context, R.animator.change_slide)
            })
        }
    }

    override fun onResult(requestCode: Int, resultCode: Int, intent: ViewIntent?) {
        super.onResult(requestCode, resultCode, intent)

        VLog.d(
            "requestCode:$requestCode, resultCode:$resultCode, result:${intent?.result?.getInt(
                "data",
                -1
            )}"
        )

        requestPermission()
    }

    override fun onPermissionResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onPermissionResult(requestCode, permissions, grantResults)

        VLog.d("requestCode:$requestCode, permissions:${permissions[0]}, grantResults:${grantResults[0]}")
    }

    /**
     * 申请权限
     */
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 100
        )
    }

}