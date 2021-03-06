package org.mipt.planetshop.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet (
    val url: String,
    val title: String,
    val explanation: String
) : Parcelable