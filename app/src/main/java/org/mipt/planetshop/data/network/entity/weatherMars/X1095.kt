package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1095(
    @SerialName("AT")
    val aT: AT?,
    @SerialName("HWS")
    val hWS: HWS?,
    @SerialName("PRE")
    val pRE: PRE?,
    @SerialName("WD")
    val wD: WD?
)