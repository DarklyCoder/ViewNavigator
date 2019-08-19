package com.darklycoder.viewnavigator.utils

import android.util.Log

class VLog private constructor() {

    companion object {
        private const val TAG = "ViewNavigator-log"

        fun d(s: String) {
            Log.d(TAG, s)
        }

        fun e(s: String) {
            Log.e(TAG, s)
        }

        fun e(e: Throwable) {
            Log.e(TAG, "", e)
        }
    }
}