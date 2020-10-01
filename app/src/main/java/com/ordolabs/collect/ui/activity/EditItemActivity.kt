package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ordolabs.collect.R
import com.ordolabs.collect.model.item.ItemParams
import com.ordolabs.collect.ui.activity.base.BaseActivity

class EditItemActivity : BaseActivity(R.layout.activity_edit_item) {

    private lateinit var itemParams: ItemParams

    override fun setUp() {
        parseIntent()
    }

    override fun setViews() {
        val itemTypeName = getString(itemParams.type.labelId)
        Toast.makeText(this, itemTypeName, Toast.LENGTH_SHORT).show()
    }

    private fun parseIntent() {
        val extras = intent.extras ?: return
        itemParams = extras.getParcelable(EXTRA_ITEM_PARAMS) ?: return
    }

    companion object {

        fun getStartIntent(caller: Context, params: ItemParams): Intent {
            return Intent(caller, EditItemActivity::class.java).apply {
                putExtra(EXTRA_ITEM_PARAMS, params)
            }
        }
    }
}