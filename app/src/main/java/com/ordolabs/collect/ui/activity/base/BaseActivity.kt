package com.ordolabs.collect.ui.activity.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.ordolabs.collect.ui.navigation.Navigator
import org.koin.android.ext.android.inject

abstract class BaseActivity(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    protected val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        setUp()
        setViews()
    }

    /**
     * Configures non-view components.
     */
    @CallSuper
    protected open fun setUp() {
        parseStartIntent()
    }

    /**
     * Parses `Intent`, that started this `Activity`.
     */
    protected open fun parseStartIntent() {
        // override me
    }

    /**
     * Sets activity's views and configures them.
     */
    protected open fun setViews() {
        // override me
    }

    companion object {

        const val EXTRA_ITEM_PARAMS = "com.ordolabs.collect.EXTRA_ITEM_PARAMS"
    }
}