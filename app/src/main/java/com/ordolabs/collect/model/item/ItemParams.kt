package com.ordolabs.collect.model.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemParams(
    val type: ItemType
) : Parcelable