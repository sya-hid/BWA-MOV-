package com.syarifhidayatullah.bwamov.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Film(
    var desc: String? = "",
    var director: String? = "",
    var genre: String? = "",
    var judul: String? = "",
    var poster: String? = "",
    var rating: String? = ""
) : Parcelable