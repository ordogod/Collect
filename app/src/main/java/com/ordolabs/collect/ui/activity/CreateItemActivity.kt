package com.ordolabs.collect.ui.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ordolabs.collect.R
import com.ordolabs.collect.model.item.ItemParams
import com.ordolabs.collect.ui.activity.base.BaseActivity
import com.ordolabs.collect.ui.adapter.ItemTypesAdapter
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.collect.util.lazyUnsafe
import com.ordolabs.collect.viewmodel.CreateItemViewModel
import kotlinx.android.synthetic.main.activity_create_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateItemActivity : BaseActivity(R.layout.activity_create_item),
    OnRecyclerItemClicksListener {

    private val createVM by viewModel<CreateItemViewModel>()

    private val typesAdapter: ItemTypesAdapter by lazyUnsafe {
        ItemTypesAdapter(this)
    }

    override fun onDestroy() {
        create_types_recycler.adapter = null
        super.onDestroy()
    }

    override fun setViews() {
        setItemTypesRecycler()
        setDoneFAB()
    }

    private fun setItemTypesRecycler() {
        create_types_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = typesAdapter
        }
    }

    private fun setDoneFAB() {
        create_type_done_fab.apply {
            setOnClickListener { onDoneFabClick() }
        }
    }

    private fun onDoneFabClick() {
        val params = makeItemParams()
        val intent = EditItemActivity.getStartIntent(this, params)
        startActivity(intent)
    }

    override fun onRecyclerItemClick(position: Int) {
        typesAdapter.getSelectedType() ?: return
        enableDoneFAB()
    }

    private fun enableDoneFAB() {
        create_type_done_fab.apply {
            isEnabled = true
            alpha = 1f
        }
    }

    private fun makeItemParams(): ItemParams {
        val type = typesAdapter.getSelectedType()!!
        return ItemParams(type)
    }

    companion object {

        fun getStartIntent(caller: Context): Intent {
            return Intent(caller, CreateItemActivity::class.java)
        }
    }
}