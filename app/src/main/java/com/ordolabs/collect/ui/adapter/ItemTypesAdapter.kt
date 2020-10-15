package com.ordolabs.collect.ui.adapter

import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ordolabs.collect.R
import com.ordolabs.collect.model.item.ItemType
import com.ordolabs.collect.ui.adapter.base.BaseAdapter
import com.ordolabs.collect.ui.adapter.base.BaseViewHolder
import com.ordolabs.collect.ui.adapter.base.OnRecyclerItemClicksListener
import com.ordolabs.collect.util.struct.TreeNode
import com.ordolabs.collect.util.viewId
import com.ordolabs.collect.viewmodel.CreateItemViewModel

class ItemTypesAdapter(
    clicksListener: OnRecyclerItemClicksListener
) : BaseAdapter<TypeItem, ItemTypesAdapter.TypeViewHolder>(clicksListener) {

    private val items: MutableList<TypeItem>

    private var selectedHolder: TypeViewHolder? = null
    private var selectedItem: TypeItem? = null

    init {
        val types = CreateItemViewModel.getItemTypes()
        items = MutableList(types.size) { i ->
            TypeItem(types[i])
        }
    }

    override fun setItems(items: List<TypeItem>) {
        // items are defined already
    }

    fun getSelectedType(): ItemType? {
        val item = selectedItem ?: return null
        return item.node.value
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
    }

    override fun performClick(holder: TypeViewHolder) {
        val index = holder.bindingAdapterPosition
        ensureIndexInItemsRange(index) ?: return
        val item = items[index]

        select(holder, item)
    }

    private fun select(holder: TypeViewHolder, item: TypeItem) {
        if (holder == selectedHolder) return

        selectedHolder?.toggleSelected(false)
        holder.toggleSelected(true)

        selectedItem = item
        selectedHolder = holder
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

        override fun setViewsOnBind(item: TypeItem) {
            setTypeName(item.node.value.labelId)
        }

        private fun setTypeName(@StringRes itemLabel: Int) {
            val typeName = itemView.context.getString(itemLabel)
            name.text = typeName
        }

        fun toggleSelected(selected: Boolean) {
            wrapper.isSelected = selected
        }
    }
}

data class TypeItem(
    val node: TreeNode<ItemType>
)