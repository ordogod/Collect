package com.ordolabs.collect.ui.adapter.base

interface OnRecyclerItemClickListener<T: Any> {

    fun onRecyclerItemClick(item: T, position: Int)
}