package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordolabs.collect.R
import com.ordolabs.collect.ui.activity.base.BaseActivity
import com.ordolabs.collect.ui.activity.base.StartableActivity
import kotlinx.android.synthetic.main.activity_create_item.*

class CreateItemActivity : BaseActivity(R.layout.activity_create_item) {

    override fun setViews() {
        setNoteTypesRecycler()
    }

    private fun setNoteTypesRecycler() {
        builder_types_list.apply {
            layoutManager = LinearLayoutManager(context)

        }
    }

    companion object : StartableActivity {
        override fun getStartIntent(caller: Context): Intent {
            return Intent(caller, CreateItemActivity::class.java)
        }
    }
}