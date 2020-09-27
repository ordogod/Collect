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

        class Note : ItemType(R.string.builder_type_note)
        class List : ItemType(R.string.builder_type_list, listOf(Ordered(), Unordered())) {
            class Ordered : ItemType(R.string.builder_type_list_ordered)
            class Unordered : ItemType(R.string.builder_type_list_unordered)
        }

        companion object {
            val collapsedCount: Int
                get() = collapsedList.size

            val expandedCount: Int
                get() = expandedList.size


            // avoid any outer modifications, single entity
            val collapsedList = listOf(
                Note(),
                List()
            )

            // avoid any outer modifications, single entity
            val expandedList = listOf(
                Note(),
                List(), List.Ordered(), List.Unordered()
            )
        }
    }
}