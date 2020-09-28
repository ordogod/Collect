package com.ordolabs.collect.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
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

    private var selectedHolder: TypeViewHolder? = null
    private var selectedItem: TypeItem? = null

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

        if (!item.isExpandable) {
            select(holder, item)
        } else {
            stretch(holder, item, index)
        }
    }

    private fun select(holder: TypeViewHolder, item: TypeItem) {
        if (holder == selectedHolder) return

        selectedHolder?.toggleSelected(false)
        holder.toggleSelected(true)

        selectedItem = item
        selectedHolder = holder
    }

    private fun stretch(holder: TypeViewHolder, item: TypeItem, index: Int) {
        val expanded = item.isExpanded
        val childSelected = (item.type.children.contains(selectedItem?.type))
        if (childSelected) return

        holder.toggleExpand(!expanded)
        item.isExpanded = !expanded

        if (expanded) {
            collapse(item, index)
        } else {
            expand(item, index)
        }
    }

    private fun expand(item: TypeItem, index: Int) {
        val insertIndex = index + 1
        val subtypes = item.type.children.map { TypeItem(it) }.toList()

        items.addAll(insertIndex, subtypes)
        notifyItemRangeInserted(insertIndex, subtypes.size)
    }

    private fun collapse(item: TypeItem, index: Int) {
        val removeStartIndex = index + 1
        val removeEndIndex = removeStartIndex + item.type.children.size
        val subtypes = items.subList(removeStartIndex, removeEndIndex)
        val removeCount = subtypes.size

        items.removeAll(subtypes)
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

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.selectedItem = null
        this.selectedHolder = null
    }

    class TypeViewHolder(root: View) : BaseViewHolder<TypeItem>(root) {

        private val wrapper by viewId<LinearLayout>(R.id.item_create_type_wrapper)
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

        fun toggleSelected(selected: Boolean) {
            wrapper.isSelected = selected
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