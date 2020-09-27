package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent

/**
 * Must be inherited by `Activity`'s `companion object`.
 */
interface StartableActivity {

    /**
     * Creates new [Intent] and configures it (sets extras etc.).
     */
    fun getStartIntent(caller: Context): Intent
}