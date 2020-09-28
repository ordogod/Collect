package com.ordolabs.collect.viewmodel

import androidx.annotation.StringRes
import com.ordolabs.collect.R
import com.ordolabs.collect.util.TreeNode

class CreateItemViewModel : BaseViewModel() {

    companion object {

        fun getTypes() = listOf(
            TreeNode(ItemType(R.string.create_type_note)),
            TreeNode(ItemType(R.string.create_type_list), listOf(
                ItemType(R.string.create_type_list_ordered),
                ItemType(R.string.create_type_list_unordered)
            )),
            TreeNode(ItemType(R.string.create_type_counter))
        )
    }

    data class ItemType(
        @StringRes val label: Int
    )
}