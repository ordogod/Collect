package com.ordolabs.collect.ui.adapter

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import com.ordolabs.collect.R
import com.ordolabs.collect.ui.adapter.base.BaseAdapter
import com.ordolabs.collect.ui.adapter.base.BaseViewHolder
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.collect.util.viewId
import com.ordolabs.collect.viewmodel.CreateItemViewModel.ItemType

class ItemTypesAdapter(
    clicksListener: OnRecyclerItemClicksListener
) : BaseAdapter<ItemType, ItemTypesAdapter.TypeViewHolder>(clicksListener) {

    val types: List<ItemType> = ItemType.expandedList

    override fun setItems(items: List<ItemType>) {
        // items are defined already
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = types[position]
        holder.onBind(item)
    }

    override fun getItemViewLayout(viewType: Int): Int {
        return R.layout.item_create_item_type_single
    }

    override fun createViewHolder(itemView: View): TypeViewHolder {
        return TypeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ItemType.collapsedCount
    }

    class TypeViewHolder(root: View) : BaseViewHolder<ItemType>(root) {

        private val name by itemView.viewId<AppCompatTextView>(R.id.item_type_single_name)

        override fun setViewsOnBind(item: ItemType) {
            setTypeName(item.label)
        }

        private fun setTypeName(@StringRes itemLabel: Int) {
            val typeName = itemView.context.getString(itemLabel)
            name.text = typeName
        }

    }
}