package com.erikriosetiawan.shinobilist.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shinobi_table")
data class Shinobi(
    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "village")
    var village: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
) : Parcelable