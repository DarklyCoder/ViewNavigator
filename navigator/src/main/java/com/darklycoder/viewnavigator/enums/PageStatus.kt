package com.darklycoder.viewnavigator.enums

enum class PageStatus(val type: Int, val desc: String) {

    UNKNOWN(0, "nothing or onDestroy"),
    SHOW(1, "on show"),
    HIDE(2, "on hide")

}