package com.olisemeka.countryviewer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostalCode(
    val format: String?,
    val regex: String?
): Parcelable