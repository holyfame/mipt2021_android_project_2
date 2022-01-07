package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1096(
    @SerialName("AT")
    val aT: ATX?,
    @SerialName("HWS")
    val hWS: HWSX?,
    @SerialName("PRE")
    val pRE: PREX?,
    @SerialName("WD")
    val wD: WDX?
)