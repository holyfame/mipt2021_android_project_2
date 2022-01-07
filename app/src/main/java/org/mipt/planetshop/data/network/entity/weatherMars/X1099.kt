package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1099(
    @SerialName("AT")
    val aT: ATXXXX?,
    @SerialName("HWS")
    val hWS: HWSXXXX?,
    @SerialName("PRE")
    val pRE: PREXXXX?,
    @SerialName("WD")
    val wD: WDXXXX?
)