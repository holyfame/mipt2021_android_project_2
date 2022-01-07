package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1097(
    @SerialName("AT")
    val aT: ATXX?,
    @SerialName("HWS")
    val hWS: HWSXX?,
    @SerialName("PRE")
    val pRE: PREXX?,
    @SerialName("WD")
    val wD: WDXX?
)