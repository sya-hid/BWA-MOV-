package com.syarifhidayatullah.bwamov.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Checkout(

    var kursi:String?="",
    var harga:String?=""

): Parcelable