package com.olisemeka.countryviewer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Maps(
    val googleMaps: String?,
    val openStreetMaps: String?
): Parcelable