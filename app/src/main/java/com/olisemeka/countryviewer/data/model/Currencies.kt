package com.olisemeka.countryviewer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currencies(
    val name: String?,
    val symbol: String?
): Parcelable