package com.darklycoder.viewnavigator.utils

import android.content.Context
import com.darklycoder.viewnavigator.impl.PageView
import com.darklycoder.viewnavigator.info.NavigatorInfo

/**
 * 保存view与path的映射关系
 */
class PagePathUtil private constructor() {

    companion object {

        private val mPagePaths: HashMap<String, ArrayList<NavigatorInfo>> = HashMap()

        fun init(paths: ArrayList<NavigatorInfo>?) {
            if (paths.isNullOrEmpty()) {
                return
            }

            mPagePaths.clear()

            // 分组
            paths.forEach {
                val group = getGroup(it.path)

                if (null == mPagePaths[group]) {
                    mPagePaths[group] = ArrayList()
                }

                mPagePaths[group]?.add(it)
            }
        }

        fun getGroup(path: String): String {
            val index = path.lastIndexOf("/")
            if (index == -1) {
                return path
            }

            return path.substring(0, index)
        }

        private fun getClsName(path: String): String? {
            val groupName = getGroup(path)
            val list = mPagePaths[groupName]
            if (list.isNullOrEmpty()) {
                return null
            }

            val ll = list.filter { it.path == path }
            if (ll.isNullOrEmpty()) {
                return null
            }

            return ll.first().clsName
        }

        fun getPageView(context: Context, path: String): PageView? {
            try {
                val clsName = getClsName(path) ?: return null

                val cls = Class.forName(clsName)
                val constructor = cls.getConstructor(Context::class.java)

                return constructor.newInstance(context) as PageView

            } catch (e: Exception) {
                VLog.e(e)
            }

            return null
        }

        fun findGroup(clsName: String, isGroup: Boolean): String? {
            var group: String? = null

            mPagePaths.entries.forEach {
                it.value.forEach { item ->
                    if (item.clsName == clsName) {
                        group = if (isGroup) item.path else it.key
                        return group
                    }
                }
            }

            return group

        }
    }

}