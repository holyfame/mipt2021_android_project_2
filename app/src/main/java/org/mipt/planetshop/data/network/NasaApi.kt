package org.mipt.planetshop.data.network

import org.mipt.planetshop.data.network.entity.images.NasaResponseItem
import org.mipt.planetshop.data.network.entity.weatherMars.MarsWeatherNW
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
//    https://api.nasa.gov/planetary/apod?api_key=MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9&thumbs=True&start_date=2015-10-1&end_date=2015-10-1
    @GET("/planetary/apod")
    suspend fun getPlanets(
        @Query("api_key") key: String = api_key,
        @Query("thumbs") returnThumbnails: String = "True",
        @Query("start_date") startDate: String = "2020-01-01",
        @Query("end_date") endDate: String = "2020-01-01"
    ) : List<NasaResponseItem>


//    https://api.nasa.gov/insight_weather/?api_key=MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9&feedtype=json&ver=1.0
    @GET("/insight_weather/")
    suspend fun getMarsWeather(
        @Query("api_key") key: String = api_key,
        @Query("feedtype") feedType: String = "json",
        @Query("ver") ver: String = "1.0",
    ) : MarsWeatherNW

    companion object {
        const val api_key = "MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9"
    }
}