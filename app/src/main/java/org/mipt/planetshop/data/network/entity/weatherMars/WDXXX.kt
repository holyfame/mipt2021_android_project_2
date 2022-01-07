package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WDXXX(
    @SerialName("sol_hours_with_data")
    val solHoursWithData: List<Int>?,
    @SerialName("valid")
    val valid: Boolean?
)