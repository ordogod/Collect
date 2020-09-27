package com.ordolabs.collect.viewmodel

import androidx.annotation.StringRes
import com.ordolabs.collect.R

class CreateItemViewModel : BaseViewModel() {

    // set of trees
    sealed class ItemType(@StringRes val label: Int, val size: Int = 0) {

        class Note : ItemType(R.string.builder_type_note)
        class List : ItemType(R.string.builder_type_list, 2) {
            class Ordered : ItemType(R.string.builder_type_list_ordered)
            class Unordered : ItemType(R.string.builder_type_list_unordered)
        }

        companion object {
            val collapsedCount: Int
                get() = collapsedList.size

            val expandedCount: Int
                get() = expandedList.size

            val allCount: Int
                get() = collapsedList.sumBy { item ->
                    item.size.takeUnless { it == 0 } ?: 1
                }


            // avoid any outer modifications, single entity
            val collapsedList = listOf(
                Note(),
                List()
            )

            // avoid any outer modifications, single entity
            val expandedList = listOf(
                Note(),
                List.Ordered(), List.Unordered()
            )
        }
    }
}