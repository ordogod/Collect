package com.ordolabs.collect.ui.adapter.base

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * An abstract implementation of [ViewHolder]
 * to be used in [BaseAdapter].
 *
 * @param T Type of data class, which this `ViewHolder` represents.
 */
abstract class BaseViewHolder<T : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    /**
     * Returns [ViewHolder.itemView]'s [View.getTag],
     * casted to [T] for more convenience.
     */
    @Suppress("UNCHECKED_CAST")
    protected val boundItem: T; get() = this.itemView.tag as T

    /**
     * Binds specified [item] data with ViewHolder's views.
     * Should be called in [BaseAdapter.onBindViewHolder]
     * to bind data with [ViewHolder.itemView].
     */
    fun onBind(item : T) {
        this.itemView.tag = item
        setViewsOnBind(item)
    }

    /**
     * Configures [itemView]'s views in [onBind].
     */
    protected abstract fun setViewsOnBind(item: T)

    /**
     * Specifies the way, in which [itemView] should react to click.
     */
    @CallSuper
    @Suppress("UNCHECKED_CAST")
    open fun performClick() {
        val adapter = bindingAdapter as? BaseAdapter<T, *> ?: return
        adapter.clicksListener.onRecyclerItemClick(bindingAdapterPosition)
    }

    /**
     * Specifies the way, in which [itemView] should react to long click.
     */
    @CallSuper
    @Suppress("UNCHECKED_CAST")
    open fun performLongClick() {
        val adapter = bindingAdapter as? BaseAdapter<T, *> ?: return
        adapter.clicksListener.onRecyclerItemLongClick(bindingAdapterPosition)
    }

}