package com.ordolabs.collect.ui.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        setUp()
        setViews()
    }

    /**
     * Configures non-view components.
     */
    protected open fun setUp() {
        // override me
    }

    /**
     * Sets activity's views and configures them.
     */
    protected open fun setViews() {
        // override me
    }
}