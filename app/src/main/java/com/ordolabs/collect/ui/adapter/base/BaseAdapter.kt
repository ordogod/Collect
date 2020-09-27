package com.ordolabs.collect.ui.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * An abstract implementation of [RecyclerView.Adapter].
 */
abstract class BaseAdapter<T : Any, VH : BaseViewHolder<T>>(
    var clicksListener: OnRecyclerItemClicksListener
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val root = createItemView(parent, viewType)
        val holder = createViewHolder(root)
        setViewHolder(holder)
        return holder
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
    }

    /**
     * Should be called to set data into `Adapter`.
     */
    abstract fun setItems(items: List<T>)

    /**
     * Specifies way of creating instances of [VH].
     */
    protected abstract fun createViewHolder(itemView: View): VH

    /**
     * Specifies layout ID res, according to [viewType].
     */
    @LayoutRes
    protected abstract fun getItemViewLayout(viewType: Int): Int

    /**
     * Configurates specified [holder] and its view.
     */
    @CallSuper
    protected open fun setViewHolder(holder: VH) {
        holder.itemView.setOnClickListener { onViewHolderClick(holder) }
        holder.itemView.setOnLongClickListener { onViewHolderLongClick(holder) }
    }

    /**
     * Would be called on [VH]'s view click.
     */
    @CallSuper
    protected open fun onViewHolderClick(holder: VH) {
        holder.performClick()
    }

    /**
     * Would be called on [VH]'s view long click.
     */
    protected open fun onViewHolderLongClick(holder: VH): Boolean {
        holder.performLongClick()
        return false
    }

    /**
     * Inflates [getItemViewLayout] view.
     */
    protected open fun createItemView(parent: ViewGroup, viewType: Int): View {
        val layout = getItemViewLayout(viewType)
        return LayoutInflater.from(parent.context).inflate(layout, parent, false)
    }
}