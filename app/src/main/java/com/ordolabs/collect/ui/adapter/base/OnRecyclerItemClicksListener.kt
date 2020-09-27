package com.ordolabs.collect.ui.adapter.base

interface OnRecyclerItemClicksListener<T : Any> : OnRecyclerItemClickListener<T> {

    fun onRecyclerItemLongClick(item: T, position: Int) {
        // default empty implementation
    }
}