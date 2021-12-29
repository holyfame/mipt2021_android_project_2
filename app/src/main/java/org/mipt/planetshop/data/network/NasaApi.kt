package org.mipt.planetshop.data.network

import org.mipt.planetshop.data.network.entity.NasaResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NasaApi {

    @GET("/planetary/apod")
    suspend fun getPlanets(
        @Query("api_key") key: String = "MOaAtBFFgFILnuc2Y0yb1bMAvdE9PtOUgDA3K2e9",
        @Query("thumbs") returnThumbnails: String = "True",
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ) : NasaResponse
}