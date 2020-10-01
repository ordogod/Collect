package com.ordolabs.collect.model.item

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemType(
    @StringRes val labelId: Int
) : Parcelable