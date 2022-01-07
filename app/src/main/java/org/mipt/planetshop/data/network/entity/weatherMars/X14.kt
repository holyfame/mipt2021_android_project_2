package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X14(
    @SerialName("compass_degrees")
    val compassDegrees: Double?,
    @SerialName("compass_point")
    val compassPoint: String?,
    @SerialName("compass_right")
    val compassRight: Double?,
    @SerialName("compass_up")
    val compassUp: Double?,
    @SerialName("ct")
    val ct: Int?
)