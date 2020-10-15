package com.ordolabs.collect.ui.navigation

import android.app.Activity
import android.content.Intent
import com.ordolabs.collect.model.item.ItemParams
import com.ordolabs.collect.ui.activity.CreateItemActivity
import com.ordolabs.collect.ui.activity.EditItemActivity
import com.ordolabs.collect.ui.activity.HomeActivity
import com.ordolabs.collect.ui.activity.base.BaseActivity

/**
 * Knows how to create [Intent]-s for each application's [Activity]
 * and start them.
 */
class Navigator {

    fun startHomeActivity(caller: Activity, closeSelf: Boolean = false) {
        val intent = Intent(caller, HomeActivity::class.java)
        startActivity(caller, intent, closeSelf)
    }

    fun startCreateItemActivity(caller: Activity, closeSelf: Boolean = false) {
        val intent = Intent(caller, CreateItemActivity::class.java)
        startActivity(caller, intent, closeSelf)
    }

    fun startEditItemActivity(caller: Activity, params: ItemParams, closeSelf: Boolean = false) {
        val intent = Intent(caller, EditItemActivity::class.java).apply {
            putExtra(BaseActivity.EXTRA_ITEM_PARAMS, params)
        }
        startActivity(caller, intent, closeSelf)
    }

    private fun startActivity(caller: Activity, intent: Intent, closeSelf: Boolean) {
        caller.startActivity(intent)
        if (closeSelf) caller.finish()
    }
}