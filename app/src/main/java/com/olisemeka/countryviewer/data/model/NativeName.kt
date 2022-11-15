package com.olisemeka.countryviewer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NativeName(
    val eng: EngX?
): Parcelable