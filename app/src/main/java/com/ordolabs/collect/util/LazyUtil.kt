package com.ordolabs.collect.util

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// do not remove explicit type argument — causes build crash

fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun <T : View> View.viewId(@IdRes id: Int): Lazy<T> =
    lazyUnsafe { this.findViewById<T>(id) }

fun <T : View> Fragment.viewId(@IdRes id: Int): Lazy<T> =
    lazyUnsafe { requireView().findViewById<T>(id) }

fun <T : View> AppCompatActivity.viewId(@IdRes id: Int): Lazy<T> =
    lazyUnsafe { findViewById<T>(id) }
