package com.ordolabs.collect.viewmodel

import androidx.annotation.StringRes
import com.ordolabs.collect.R

class ItemBuilderViewModel : BaseViewModel() {

    sealed class ItemType(@StringRes val label: Int) {

        class ItemNote : ItemType(R.string.builder_type_note)
        class ItemList : ItemType(R.string.builder_type_list) {
            class ListOrdered : ItemType(R.string.builder_type_list_ordered)
            class ListUnordered : ItemType(R.string.builder_type_list_unordered)
        }

        companion object {
            const val size: Int = 2

            fun getExpandedList(): List<ItemType> = listOf(
                ItemNote(),
                ItemList.ListOrdered(), ItemList.ListUnordered()
            )
        }
    }
}