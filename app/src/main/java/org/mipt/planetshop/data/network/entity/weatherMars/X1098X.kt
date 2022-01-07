package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X1098X(
    @SerialName("AT")
    val aT: ATXXXXX?,
    @SerialName("First_UTC")
    val firstUTC: String?,
    @SerialName("HWS")
    val hWS: HWSXXXXX?,
    @SerialName("Last_UTC")
    val lastUTC: String?,
    @SerialName("Month_ordinal")
    val monthOrdinal: Int?,
    @SerialName("Northern_season")
    val northernSeason: String?,
    @SerialName("PRE")
    val pRE: PREXXXXX?,
    @SerialName("Season")
    val season: String?,
    @SerialName("Southern_season")
    val southernSeason: String?,
    @SerialName("WD")
    val wD: WDXXXXX?
)