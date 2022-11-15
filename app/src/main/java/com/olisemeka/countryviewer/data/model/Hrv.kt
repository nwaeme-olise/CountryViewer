package com.olisemeka.countryviewer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hrv(
    val common: String?,
    val official: String?
): Parcelable