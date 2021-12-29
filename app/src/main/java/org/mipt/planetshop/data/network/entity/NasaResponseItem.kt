package org.mipt.planetshop.data.network.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NasaResponseItem(
    @SerialName("copyright")
    val copyright: String?,
    @SerialName("date")
    val date: String?,
    @SerialName("explanation")
    val explanation: String?,
    @SerialName("hdurl")
    val hdurl: String?,
    @SerialName("media_type")
    val mediaType: String?,
    @SerialName("service_version")
    val serviceVersion: String?,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?
)