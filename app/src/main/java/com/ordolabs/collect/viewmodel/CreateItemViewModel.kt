package com.ordolabs.collect.viewmodel

import androidx.annotation.StringRes
import com.ordolabs.collect.R
import kotlin.collections.List as KList

class CreateItemViewModel : BaseViewModel() {

    // set of trees
    sealed class ItemType(
        @StringRes val label: Int,
        val children: KList<ItemType> = emptyList()
    ) {

        class Note : ItemType(R.string.create_type_note)
        class List : ItemType(R.string.create_type_list, listOf(Ordered(), Unordered())) {
            class Ordered : ItemType(R.string.create_type_list_ordered)
            class Unordered : ItemType(R.string.create_type_list_unordered)
        }
        class Counter : ItemType(R.string.create_type_counter)

        companion object {
            fun getCollapsedList() = mutableListOf(
                Note(),
                List(),
                Counter()
            )
        }
    }
}