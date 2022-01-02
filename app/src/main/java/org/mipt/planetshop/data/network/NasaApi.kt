package org.mipt.planetshop.data.network

import org.mipt.planetshop.data.network.entity.NasaResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {

    @GET("/planetary/apod")
    suspend fun getPlanets(
        @Query("api_key") key: String = "MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9",
        @Query("thumbs") returnThumbnails: String = "True",
        @Query("start_date") startDate: String = "2020-01-01",
        @Query("end_date") endDate: String = "2020-01-01"
    ) : List<NasaResponseItem>

    companion object {
        const val api_key = "MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9"
    }
}