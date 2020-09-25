package com.syarifhidayatullah.bwamov.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Plays(
    var nama:String?="",
    var url:String?=""

): Parcelable