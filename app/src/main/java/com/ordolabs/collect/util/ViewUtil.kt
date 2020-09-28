package com.ordolabs.collect.util

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.view.updatePadding

/**
 * Sets [View.VISIBLE] visibility state to `this` View.
 */
internal fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Sets [View.INVISIBLE] visibility state to `this` View.
 */
internal fun View.hide() {
    this.visibility = View.INVISIBLE
}

/**
 * Sets [View.GONE] visibility state to `this` View.
 */
internal fun View.remove() {
    this.visibility = View.INVISIBLE
}

internal fun View.setBackgroundResourceSavingPaddings(@DrawableRes resid: Int) {
    val paddings = listOf(paddingLeft, paddingTop, paddingRight, paddingBottom)
    this.setBackgroundResource(resid)
    this.updatePadding(paddings[0], paddings[1], paddings[2], paddings[3])
}