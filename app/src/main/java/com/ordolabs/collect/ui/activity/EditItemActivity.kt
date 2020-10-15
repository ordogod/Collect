package com.ordolabs.collect.ui.activity

import android.widget.Toast
import com.ordolabs.collect.R
import com.ordolabs.collect.model.item.ItemParams
import com.ordolabs.collect.ui.activity.base.BaseActivity

class EditItemActivity : BaseActivity(R.layout.activity_edit_item) {

    private lateinit var itemParams: ItemParams

    override fun setUp() {
        super.setUp()
    }

    override fun parseStartIntent() {
        val extras = intent.extras ?: return
        itemParams = extras.getParcelable(EXTRA_ITEM_PARAMS) ?: return
    }

    override fun setViews() {
        val itemTypeName = getString(itemParams.type.labelId)
        Toast.makeText(this, itemTypeName, Toast.LENGTH_SHORT).show()
    }
}