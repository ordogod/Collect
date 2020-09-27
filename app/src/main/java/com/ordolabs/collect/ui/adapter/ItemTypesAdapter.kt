package com.ordolabs.collect.ui.adapter

import android.view.View
import android.widget.ImageView
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

    private val types: MutableList<ItemType> = ItemType.getCollapsedList()
    private val typesCollapsed: List<ItemType> = ItemType.getCollapsedList()
    private val isTypeExpanded = MutableList(typesCollapsed.size) { false }

    override fun setItems(items: List<ItemType>) {
        // items are defined already
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = types[position]
        holder.onBind(item)
    }

    override fun performClick(holder: TypeViewHolder) {
        if (!holder.isExpandable) return
//        val index = types.indexOf(holder.boundItem)
        val index = holder.bindingAdapterPosition
        val isExpanded = isTypeExpanded[index]

        holder.toggleExpand(isExpanded)

        if (isExpanded) {
            collapseItem(index)
        } else {
            expandItem(index)
        }
    }

    private fun expandItem(itemIndex: Int) {
        val targetItem = types[itemIndex]
        val subtypes = targetItem.children
        val insertIndex = itemIndex + 1

        types.addAll(insertIndex, subtypes)
        isTypeExpanded[itemIndex] = true
        notifyItemRangeInserted(insertIndex, subtypes.size)
    }

    private fun collapseItem(itemIndex: Int) {
        val targetItem = types[itemIndex]
        val subtypes = targetItem.children
        val removeIndex = itemIndex + 1

        types.removeAll(subtypes)
        isTypeExpanded[itemIndex] = false
        notifyItemRangeRemoved(removeIndex, subtypes.size)
    }

    override fun getItemViewLayout(viewType: Int): Int {
        return R.layout.item_create_type
    }

    override fun createViewHolder(itemView: View): TypeViewHolder {
        return TypeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    class TypeViewHolder(root: View) : BaseViewHolder<ItemType>(root) {

        private val name by viewId<AppCompatTextView>(R.id.item_create_type_name)
        private val dropdown by viewId<ImageView>(R.id.item_create_type_dropdown)

        val isExpandable: Boolean
            get() = boundItem.children.isNotEmpty()

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

        fun toggleExpand(isExpanded: Boolean) {
            dropdown.rotation += 180f
            if (dropdown.rotation == 360f) dropdown.rotation = 0f
        }
    }
}