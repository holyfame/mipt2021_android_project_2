package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1098(
    @SerialName("AT")
    val aT: ATXXX?,
    @SerialName("HWS")
    val hWS: HWSXXX?,
    @SerialName("PRE")
    val pRE: PREXXX?,
    @SerialName("WD")
    val wD: WDXXX?
)