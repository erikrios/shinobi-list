package com.erikriosetiawan.shinobilist.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shinobi(
    var id: Int = -1,
    var name: String? = null,
    var village: String? = null,
    var description: String? = null
) : Parcelable