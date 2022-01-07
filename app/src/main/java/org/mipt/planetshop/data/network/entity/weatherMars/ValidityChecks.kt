package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidityChecks(
    @SerialName("sol_hours_required")
    val solHoursRequired: Int?,
    @SerialName("sols_checked")
    val solsChecked: List<String>?,
    @SerialName("1095")
    val x1095: X1095?,
    @SerialName("1096")
    val x1096: X1096?,
    @SerialName("1097")
    val x1097: X1097?,
    @SerialName("1098")
    val x1098: X1098?,
    @SerialName("1099")
    val x1099: X1099?
)