package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PREXXXXX(
    @SerialName("av")
    val av: Double?,
    @SerialName("ct")
    val ct: Int?,
    @SerialName("mn")
    val mn: Double?,
    @SerialName("mx")
    val mx: Double?
)