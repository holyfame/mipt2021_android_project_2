package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsWeatherNW(
    @SerialName("sol_keys")
    val solKeys: List<String>?,
    @SerialName("validity_checks")
    val validityChecks: ValidityChecks?,
    @SerialName("1098")
    val x1098: X1098X?
)