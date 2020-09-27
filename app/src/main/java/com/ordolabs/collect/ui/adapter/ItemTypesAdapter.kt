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
import com.ordolabs.collect.util.ValueAnimatorBuilder
import com.ordolabs.collect.util.viewId
import com.ordolabs.collect.viewmodel.CreateItemViewModel.ItemType

class ItemTypesAdapter(
    clicksListener: OnRecyclerItemClicksListener
) : BaseAdapter<TypeItem, ItemTypesAdapter.TypeViewHolder>(clicksListener) {

    private val types: MutableList<ItemType> = ItemType.getCollapsedList()
    private val items: MutableList<TypeItem>

    init {
        items = MutableList(types.size) { i ->
            TypeItem(types[i])
        }
    }

    override fun setItems(items: List<TypeItem>) {
        // items are defined already
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
    }

    override fun performClick(holder: TypeViewHolder) {
        val index = holder.bindingAdapterPosition
        val item = items[index]
        if (!item.isExpandable) return

        holder.toggleExpand(!item.isExpanded)

        if (item.isExpanded) {
            collapse(item, index)
        } else {
            expand(item, index)
        }
    }

    private fun expand(item: TypeItem, index: Int) {
        val insertIndex = index + 1
        val subtypes = item.type.children.map { TypeItem(it) }.toList()

        items.addAll(insertIndex, subtypes)
        item.isExpanded = true
        notifyItemRangeInserted(insertIndex, subtypes.size)
    }

    private fun collapse(item: TypeItem, index: Int) {
        val removeStartIndex = index + 1
        val removeEndIndex = removeStartIndex + item.type.children.size
        val subtypes = items.subList(removeStartIndex, removeEndIndex)
        val removeCount = subtypes.size

        items.removeAll(subtypes)
        item.isExpanded = false
        notifyItemRangeRemoved(removeStartIndex, removeCount)
    }

    override fun getItemViewLayout(viewType: Int): Int {
        return R.layout.item_create_type
    }

    override fun createViewHolder(itemView: View): TypeViewHolder {
        return TypeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TypeViewHolder(root: View) : BaseViewHolder<TypeItem>(root) {

        private val name by viewId<AppCompatTextView>(R.id.item_create_type_name)
        private val dropdown by viewId<ImageView>(R.id.item_create_type_dropdown)

        override fun setViewsOnBind(item: TypeItem) {
            setTypeName(item.type.label)
            setDropdownVisibility(item.type.children.isNotEmpty())
        }

        private fun setTypeName(@StringRes itemLabel: Int) {
            val typeName = itemView.context.getString(itemLabel)
            name.text = typeName
        }

        private fun setDropdownVisibility(hasChildren: Boolean) {
            dropdown.isInvisible = !hasChildren
        }

        fun toggleExpand(expand: Boolean) {
            animateDropdownRotation(expand)
        }

        private fun animateDropdownRotation(forward: Boolean) =
            ValueAnimatorBuilder.of<Float>(forward) {
                values {
                    if (forward) arrayOf(0f, 180f) else arrayOf(360f, 180f)
                }
                updateListener {
                    val value = animatedValue as Float
                    dropdown.rotation = value
                }
            }.start()
    }
}

data class TypeItem(
    val type: ItemType
) {
    val isExpandable = type.children.isNotEmpty()
    var isExpanded = false
}