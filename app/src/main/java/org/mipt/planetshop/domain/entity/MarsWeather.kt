package org.mipt.planetshop.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

/*
sol : Solar day on Mars
mnAT - min atmospheric Temperature
mxAT - max atmospheric Temperature
 */
@Parcelize
data class MarsWeather (
    val sol :String,
    val mnAT:String,
    val mxAT:String,
    val date: String
):Parcelable
