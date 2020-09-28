package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordolabs.collect.R
import com.ordolabs.collect.ui.activity.base.BaseActivity
import com.ordolabs.collect.ui.activity.base.StartableActivity
import com.ordolabs.collect.ui.adapter.ItemTypesAdapter
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.collect.viewmodel.CreateItemViewModel
import kotlinx.android.synthetic.main.activity_create_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateItemActivity : BaseActivity(R.layout.activity_create_item),
    OnRecyclerItemClicksListener {

    private val createVM by viewModel<CreateItemViewModel>()

    override fun setViews() {
        setItemTypesRecycler()
    }

    private fun setItemTypesRecycler() {
        create_types_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemTypesAdapter(this@CreateItemActivity)
        }
    }

    override fun onRecyclerItemClick(position: Int) {
        // nothing's here for a while
    }

    override fun onStop() {
        create_types_recycler.adapter = null
        super.onStop()
    }

    companion object : StartableActivity {
        override fun getStartIntent(caller: Context): Intent {
            return Intent(caller, CreateItemActivity::class.java)
        }
    }
}