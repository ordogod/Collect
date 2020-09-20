package com.ordolabs.collect.util

import android.view.View

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