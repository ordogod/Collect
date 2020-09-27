package com.ordolabs.collect.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isInvisible
import com.ordolabs.collect.R
import com.ordolabs.collect.ui.adapter.base.BaseAdapter
import com.ordolabs.collect.ui.adapter.base.BaseViewHolder
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.collect.util.viewId
import com.ordolabs.collect.viewmodel.CreateItemViewModel.ItemType

class ItemTypesAdapter(
    clicksListener: OnRecyclerItemClicksListener
) : BaseAdapter<ItemType, ItemTypesAdapter.TypeViewHolder>(clicksListener) {

    private val types: List<ItemType> = ItemType.expandedList
    private val typesExpanded: List<Boolean> = MutableList(types.size) { false }

    override fun setItems(items: List<ItemType>) {
        // items are defined already
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = types[position]
        holder.onBind(item)
    }

    override fun getItemViewLayout(viewType: Int): Int {
        return R.layout.item_create_type
    }

    override fun createViewHolder(itemView: View): TypeViewHolder {
        return TypeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ItemType.collapsedCount
    }

    class TypeViewHolder(root: View) : BaseViewHolder<ItemType>(root) {

        private val name by viewId<AppCompatTextView>(R.id.item_create_type_name)
        private val dropdown by viewId<ImageView>(R.id.item_create_type_dropdown)

        override fun setViewsOnBind(item: ItemType) {
            setTypeName(item.label)
            setDropdownVisibility(item.children.isNotEmpty())
        }

        private fun setTypeName(@StringRes itemLabel: Int) {
            val typeName = itemView.context.getString(itemLabel)
            name.text = typeName
        }

        private fun setDropdownVisibility(hasChildren: Boolean) {
            dropdown.isInvisible = !hasChildren
        }

        override fun onClick(v: View?) {
            if (boundItem.children.isEmpty()) return

            Toast.makeText(v?.context, "open", Toast.LENGTH_SHORT).show()
        }
    }
}