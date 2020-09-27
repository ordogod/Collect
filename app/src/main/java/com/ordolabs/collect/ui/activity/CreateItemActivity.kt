package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordolabs.collect.R
import com.ordolabs.collect.ui.activity.base.BaseActivity
import com.ordolabs.collect.ui.activity.base.StartableActivity
import com.ordolabs.collect.ui.adapter.ItemTypesAdapter
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import kotlinx.android.synthetic.main.activity_create_item.*

class CreateItemActivity : BaseActivity(R.layout.activity_create_item),
    OnRecyclerItemClicksListener {

    override fun setViews() {
        setItemTypesRecycler()
    }

    private fun setItemTypesRecycler() {
        builder_types_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemTypesAdapter(this@CreateItemActivity)
        }
    }

    override fun onRecyclerItemClick(position: Int) {
        // nothing's here for a while
    }

    companion object : StartableActivity {
        override fun getStartIntent(caller: Context): Intent {
            return Intent(caller, CreateItemActivity::class.java)
        }
    }
}