package com.ordolabs.collect.ui.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, VH : BaseViewHolder<T>>(
    var clicksListener: OnRecyclerItemClicksListener<T>
) :
    RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val root = createItemView(parent, viewType)
        val holder = createViewHolder(root)
        setViewHolder(holder)
        return holder
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
    }

    protected abstract fun createViewHolder(itemView: View): VH

    @LayoutRes
    protected abstract fun getItemViewLayout(viewType: Int): Int

    protected open fun setViewHolder(holder: VH) {
        holder.itemView.setOnClickListener { onViewHolderClick(holder) }
        holder.itemView.setOnLongClickListener { onViewHolderLongClick(holder) }
    }

    @CallSuper
    protected open fun onViewHolderClick(holder: VH) {
        holder.performClick()
    }

    protected open fun onViewHolderLongClick(holder: VH): Boolean {
        holder.performLongClick()
        return false
    }

    private fun createItemView(parent: ViewGroup, viewType: Int): View {
        val layout = getItemViewLayout(viewType)
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }
}