package org.mipt.planetshop.data

import org.mipt.planetshop.data.network.NasaApi
import org.mipt.planetshop.data.network.entity.weatherMars.MarsWeatherNW
import org.mipt.planetshop.domain.MarsWeatherRepository
import org.mipt.planetshop.domain.entity.MarsWeather
import org.mipt.planetshop.domain.entity.Planet

class MarsWeatherRepositoryImpl(
    private val nasaApi: NasaApi
):MarsWeatherRepository {
    override suspend fun getMarsWeather(): MarsWeather {
        return MarsWeather(
            nasaApi.getMarsWeather().solKeys?.get(0).toString()  ?: "",
            nasaApi.getMarsWeather().x1098?.aT?.mn.toString() ?: "Not valid",
            nasaApi.getMarsWeather().x1098?.aT?.mx.toString() ?: "",
            nasaApi.getMarsWeather().x1098?.firstUTC ?: ""
        )
    }

//        { response ->
//            MarsWeather(
//                weather = response.
//                url = response.thumbnailUrl?: response.url ?: return@mapNotNull null,
//                title = response.title ?: "",
//                explanation = response.explanation ?: ""
//            )
//    }


}
