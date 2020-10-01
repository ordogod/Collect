package com.ordolabs.collect.viewmodel

import com.ordolabs.collect.R
import com.ordolabs.collect.model.item.ItemType
import com.ordolabs.collect.util.struct.TreeNode

class CreateItemViewModel : BaseViewModel() {

    companion object {

        internal fun getItemTypes() = listOf(
            TreeNode(ItemType(R.string.create_type_note)),
            TreeNode(
                ItemType(R.string.create_type_list), listOf(
                    ItemType(R.string.create_type_list_ordered),
                    ItemType(R.string.create_type_list_unordered)
                )
            ),
            TreeNode(ItemType(R.string.create_type_counter))
        )
    }
}