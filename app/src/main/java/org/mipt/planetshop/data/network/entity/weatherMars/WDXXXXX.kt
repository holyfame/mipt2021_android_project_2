package org.mipt.planetshop.data.network.entity.weatherMars


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WDXXXXX(
    @SerialName("most_common")
    val mostCommon: MostCommon?,
    @SerialName("0")
    val x0: X0?,
    @SerialName("1")
    val x1: X1?,
    @SerialName("10")
    val x10: X10?,
    @SerialName("11")
    val x11: X11?,
    @SerialName("12")
    val x12: X12?,
    @SerialName("13")
    val x13: X13?,
    @SerialName("14")
    val x14: X14?,
    @SerialName("15")
    val x15: X15?,
    @SerialName("2")
    val x2: X2?,
    @SerialName("3")
    val x3: X3?,
    @SerialName("5")
    val x5: X5?,
    @SerialName("6")
    val x6: X6?,
    @SerialName("7")
    val x7: X7?,
    @SerialName("8")
    val x8: X8?,
    @SerialName("9")
    val x9: X9?
)