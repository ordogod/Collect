package com.ordolabs.collect.ui.adapter.base

interface OnRecyclerItemClicksListener {

    fun onRecyclerItemClick(position: Int)

    fun onRecyclerItemLongClick(position: Int) {
        // default empty implementation
    }
}